package com.example.represencas_mobile.estudante_activities.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.fingerprint.FingerPrintAuthenticator;

import java.util.GregorianCalendar;
import java.util.Objects;

public class EstPresencasMarcacaoFragment extends Fragment {

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private Switch swIsMarcado;
    private ImageView imgFinger;
    private static String idAula;
    private static String generatedCode;
    private static String idInscricao;
    private Intent intent;
    private TextView tvCadeira, tvPeriodo, tvNome;

    public EstPresencasMarcacaoFragment() {
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_est_presencas_marcacao, container, false);

        fingerprintManager = (FingerprintManager) Objects.requireNonNull(getActivity()).getSystemService(Context.FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.KEYGUARD_SERVICE);
        intent = Objects.requireNonNull(getActivity()).getIntent();

        swIsMarcado = view.findViewById(R.id.sw_is_marcado);
        imgFinger = view.findViewById(R.id.img_finger);
        tvCadeira = view.findViewById(R.id.tv_cadeira_est_marc);
        tvPeriodo = view.findViewById(R.id.tv_periodo_est_marc);
        tvNome = view.findViewById(R.id.tv_nome_est_marc);

        this.startFingerPrint();

        return view;
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void savePresenca() {

        //Save on DataBase

        Vibrator vibrator = (Vibrator) Objects.requireNonNull(getActivity()).getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        swIsMarcado.setChecked(true);
        swIsMarcado.setText("Marcado");
        swIsMarcado.setHighlightColor(R.color.colorAccent);
        Toast.makeText(getContext(), "Presença marcada com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private static String getDataActual() {
        GregorianCalendar c = (GregorianCalendar) GregorianCalendar.getInstance();
        int dia = c.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = c.get(GregorianCalendar.MONTH) + 1;
        int ano = c.get(GregorianCalendar.YEAR);

        return dia + "-" + mes + "-" + ano;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void startFingerPrint() {
        if (checkFingerPrintSettings()) {
            FingerPrintAuthenticator authenticator = FingerPrintAuthenticator.getInstanece();

            if (authenticator.cipherInit()) {
                FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(authenticator.getCipher());

                FingerPrintHandler fingerPrintHandler = new FingerPrintHandler();
                fingerPrintHandler.startAuthentication(cryptoObject);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {

        CancellationSignal signal;

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Toast.makeText(getContext(), "Erro de autenticação.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Toast.makeText(getContext(), "Falha no processo de autenticação.", Toast.LENGTH_SHORT).show();
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);

            View viewCode = View.inflate(getContext(), R.layout.layout_code, null);
            final EditText edtCode = viewCode.findViewById(R.id.edt_code_dialog);

            final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
            builder.setView(viewCode);

            builder.setNegativeButton("Cancelar", null);
            builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (generatedCode.equals(edtCode.getText().toString().trim())) {
                        savePresenca();
                    }else{
                        Toast.makeText(getContext(), "O código introduzido não corresponde ao código da sessão.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        void startAuthentication(FingerprintManager.CryptoObject cryptoObject) {
            signal = new CancellationSignal();

            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.USE_FINGERPRINT)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fingerprintManager.authenticate(cryptoObject, signal, 0, this, null);
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkFingerPrintSettings() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.USE_FINGERPRINT)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (fingerprintManager.isHardwareDetected()) {
            if (fingerprintManager.hasEnrolledFingerprints()) {
                if (keyguardManager.isKeyguardSecure()) {
                    return true;
                }
            } else {
                Toast.makeText(getContext(), "Habilite o serviço de Impressão Digital primeiro!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
            }
        }
        return false;
    }
}

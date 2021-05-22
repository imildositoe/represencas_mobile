package com.example.represencas_mobile.login;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.storage.LocalStorage;
import com.example.represencas_mobile.home.Home;
import com.example.represencas_mobile.model.fake_model.Estudantes;
import com.example.represencas_mobile.model.list_model.EstudantesList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Imildo Sitoe
 */
public class LoginActivity extends AppCompatActivity {

    private FrameLayout btnSignIn;
    private TextView btnLoginHelp;
    private EditText edtUsuario;
    private EditText edtSenha;
    private ProgressDialog progressDialog;
    private LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Autenticação");

        btnSignIn = findViewById(R.id.btn_sign_in);
        btnLoginHelp = findViewById(R.id.btn_login_help);
        edtSenha = findViewById(R.id.edt_senha);
        edtUsuario = findViewById(R.id.edt_usuario);
        localStorage = new LocalStorage(getApplicationContext());

        this.loginHelp();

        this.auth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.dismiss();
    }

    private void auth() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }

    private void loginHelp() {
        btnLoginHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Use o email institucional como usuário e use o código correspondente como senha." +
                        "\nAs credenciais são as mesmas usadas no SIGA.");
                builder.setPositiveButton("Ok ", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private boolean isInternetAvailable() {
        try {

            InetAddress ipAddress = InetAddress.getByName("www.google.com");
            return !ipAddress.equals("");
        } catch (UnknownHostException e) {
            Log.e("Conexao", e.toString());
        }
        return false;
    }

    /**
     * Class will execute jobs during authentication
     */
    @SuppressLint("StaticFieldLeak")
    private class MyTask extends AsyncTask<Void, Void, Void> {
        final String email = edtUsuario.getText().toString();
        final String senha = edtSenha.getText().toString();

        MyTask() {
            progressDialog = new ProgressDialog(LoginActivity.this);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, "", "Aguarde...");
        }

        @Override
        protected Void doInBackground(Void... integers) {

            // Auth in table User (API)
            //if (isNetworkConnected() && isInternetAvailable()) {
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(senha)) {

                AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
                Call<EstudantesList> call = attendanceService.authUser();
                Log.e("Call", String.valueOf(call));
                call.enqueue(new Callback<EstudantesList>() {
                    @Override
                    public void onResponse(@NonNull Call<EstudantesList> call, @NonNull Response<EstudantesList> response) {

                        boolean flag = false;

                        assert response.body() != null;
                        Log.e("Estudantes", String.valueOf(response.body().getEstudantes()));
                        for (int i = 0; i < response.body().getEstudantes().size(); i++) {
                            Estudantes estudante = response.body().getEstudantes().get(i);
                            Log.e("Estudante", String.valueOf(estudante));

                            if (email.equals(estudante.getEmail()) && senha.equals(estudante.getSenha())) {
                                flag = true;
                                localStorage.setIdUser(estudante.getId_estudante());
                                localStorage.setNomeUser(estudante.getNome());
                                localStorage.setNrEstudanteUser(estudante.getNr_estudante());
                                localStorage.setApelidoUser(estudante.getApelido());
                                break;
                            }
                        }

                        if (flag) {
                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciais Invalidas. Tente novamente!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<EstudantesList> call, @NonNull Throwable t) {

                    }
                });
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        edtUsuario.setError("Campo obrigatório");
                        edtSenha.setError("Campo obrigatório");
                    }
                });
            }
            //} else {
            //    new Handler(Looper.getMainLooper()).post(new Runnable() {
            //        @Override
            //        public void run() {
            //            Toast.makeText(LoginActivity.this, "Active os dados móveis ou conecte-se a uma rede WI-FI", Toast.LENGTH_LONG).show();
            //        }
            //    });
            //}

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            progressDialog.dismiss();
        }
    }
}

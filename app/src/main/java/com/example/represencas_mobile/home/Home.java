package com.example.represencas_mobile.home;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.storage.LocalStorage;
import com.example.represencas_mobile.estudante_activities.activities.EstPresenca;
import com.example.represencas_mobile.model.list_model.EstudantesList;
import com.example.represencas_mobile.model.list_model.FaltasList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class Home extends AppCompatActivity {

    private Button btnGo;
    private ImageView imgPerfilEst;
    private TextView tvNomeEstudantePerfil, tvNrEstudantePerfil, tvUpload;
    private Intent intent;
    private static final int CHOOSE_IMAGE = 1;
    private Uri imgUri;
    private LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Início");
        intent = getIntent();
        imgPerfilEst = findViewById(R.id.img_foto_perfil_estudante);
        tvNomeEstudantePerfil = findViewById(R.id.tv_nome_estudante_perfil);
        tvNrEstudantePerfil = findViewById(R.id.tv_nr_estudante_perfil);
        tvUpload = findViewById(R.id.tv_upload);
        localStorage = new LocalStorage(getApplicationContext());

        chooseImage();
        uploadClick();
        setLabels();
        start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.renderPhoto();
    }

    private void setLabels() {
        String nome = localStorage.getNomeUser() + " " + localStorage.getApelidoUser();
        tvNomeEstudantePerfil.setText(nome);
        tvNrEstudantePerfil.setText(localStorage.getNrEstudanteUser());
    }

    private void renderPhoto() {
        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
//        Call<FotoList> call = attendanceService.getFoto(idturmaClicada, turma.getId_inscricao());
//        call.enqueue

//        Picasso.with(Home.this)
//                .load(foto.getUrl())
//                .placeholder(R.drawable.ic_launcher_background)
//                .fit()
//                .centerCrop()
//                .into(imgPerfilEst);
    }

    private void chooseImage() {
        imgPerfilEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    private void uploadClick() {
        tvUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (mUploadTask != null && mUploadTask.isInProgress()) {
                Toast.makeText(Home.this, "Upload esta em progresso...", Toast.LENGTH_LONG).show();
                //} else {
                uploadImage();
                //}
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            Picasso.with(this).load(imgUri).into(imgPerfilEst);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        if (imgUri != null) {
            //TODO send photo to api
        } else {
            Toast.makeText(this, "Nenhum ficheiro foi selecionado", Toast.LENGTH_SHORT).show();
        }
    }

    private void start() {
        btnGo = findViewById(R.id.btn_go_estudante);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Home.this, EstPresenca.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_ajuda) {
            return true;
        }
        if (id == R.id.action_sair) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

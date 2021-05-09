package com.example.represencas_mobile;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.represencas_mobile.model.list_model.EstudantesList;
import com.example.represencas_mobile.remote.AttendanceService;
import com.example.represencas_mobile.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AttendanceService attendanceService = RetrofitClient.getClient().create(AttendanceService.class);
        Call<EstudantesList> call = attendanceService.getEstudantes();
        call.enqueue(new Callback<EstudantesList>() {
            @Override
            public void onResponse(@NonNull Call<EstudantesList> call, @NonNull Response<EstudantesList> response) {
                assert response.body() != null;
                Toast.makeText(MainActivity.this, "Estudantes: " + response.body(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Log.e("Estudantes ", String.valueOf(response.body()));
                }else {
                    Log.e("Insuccessfull", String.valueOf(response.code()));
                    Log.e("Insuccessfull", String.valueOf(call.request().url()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<EstudantesList> call, @NonNull Throwable t) {
                Log.e("Erro", t.toString());
                Log.e("Insuccessfull", String.valueOf(call.clone()));
                Log.e("Insuccessfull 2", String.valueOf(call.request().url()));
            }
        });


//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://192.168.43.6:8080/api/get_all_estudantes")
//                .get()
//                .build();
//
//        try {
//            Call<List<Estudantes>> response = (Call<List<Estudantes>>) client.newCall(request);
//
//
//            assert response != null;
//            response.enqueue(new Callback<List<Estudantes>>() {
//                @Override
//                public void onResponse(@NonNull Call<List<Estudantes>> call, @NonNull retrofit2.Response<List<Estudantes>> response) {
//                    Log.e("Estudantes", "" + response.body());
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<List<Estudantes>> call, @NonNull Throwable t) {
//                    Log.e("Erro", t.toString());
//                }
//            });
//
//        }catch (ClassCastException e) {
//            Log.e("ClassCast", e.toString());
//        }


//        AttendanceService attendanceService = APIUtils.getUserService();
//        Call<List<Estudantes>> call = attendanceService.getEstudantes();
//
//        call.enqueue(new Callback<List<Estudantes>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<Estudantes>> call, @NonNull Response<List<Estudantes>> response) {
//                assert response.body() != null;
//                Toast.makeText(MainActivity.this, "Users: " + response.body().toString(), Toast.LENGTH_SHORT).show();
//                Log.e("Estudantes ", String.valueOf(response.body()));
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<Estudantes>> call, @NonNull Throwable t) {
//
//                if (t instanceof SocketTimeoutException) {
//                    Log.e("TimeOut", t.toString());
//                } else if (t instanceof IOException) {
//                    Log.e("IOException", t.toString());
//                } else {
//                    if (call.isCanceled()) {
//                        System.out.println("Call has been cancelled by user");
//                    } else {
//                        System.out.println("Network Error::" + t.toString());
//                    }
//                }
//            }
//        });
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
        if (id == R.id.action_sair) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}

//    private int port = 8000;
//    private SchemeRegistry schemeRegistry = new SchemeRegistry();
//
//
//    try {
//            SSLContext.getInstance("TLSv1.2");
//            } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            }
//
//            try {
//            schemeRegistry.register(new Scheme("https", (SocketFactory) new TLSSocketFactory(), port));
//            } catch (KeyManagementException | NoSuchAlgorithmException | ClassCastException e) {
//            e.printStackTrace();
//            }
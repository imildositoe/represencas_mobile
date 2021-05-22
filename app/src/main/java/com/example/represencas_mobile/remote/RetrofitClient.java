package com.example.represencas_mobile.remote;

import com.example.represencas_mobile.all.security.TLSSocketFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://represencas-api.herokuapp.com/";


    public static Retrofit getClient() {

//        OkHttpClient client = new OkHttpClient();
//
//        try {
//            client = new OkHttpClient.Builder()
//                    .callTimeout(2, TimeUnit.MINUTES)
//                    .connectTimeout(20, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .writeTimeout(30, TimeUnit.SECONDS)
//                    .sslSocketFactory(new TLSSocketFactory())
//                    .build();
//        } catch (KeyManagementException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

//        .client(client)

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

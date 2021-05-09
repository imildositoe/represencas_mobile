package com.example.represencas_mobile.all.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    private SharedPreferences.Editor editor;
    private Context context;
    public SharedPreferences pref;
    private int PRIVATE_MODE = 0;
    private final String PREF_NAME = "_store";

    @SuppressLint("CommitPrefEdits")
    public LocalStorage(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public int getIdUser() {
        return pref.getInt("user_id", 0);
    }

    public void setIdUser(int idUser) {
        editor.putInt("user_id", idUser);
        editor.commit();
    }

    public String getNomeUser() {
        return pref.getString("user_nome", "");
    }

    public void setNomeUser(String idNomeUser) {
        editor.putString("user_nome", idNomeUser);
        editor.commit();
    }

    public String getApelidoUser() {
        return pref.getString("user_apelido", "");
    }

    public void setApelidoUser(String idApelidoUser) {
        editor.putString("user_apelido", idApelidoUser);
        editor.commit();
    }

    public String getNrEstudanteUser() {
        return pref.getString("user_nr_estudante", "");
    }

    public void setNrEstudanteUser(String idNrUser) {
        editor.putString("user_nr_estudante", idNrUser);
        editor.commit();
    }
}

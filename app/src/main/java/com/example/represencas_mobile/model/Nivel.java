package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nivel {

    @SerializedName("id_nivel")
    @Expose
    private int id_nivel;

    @SerializedName("designacao")
    @Expose
    private String designacao;

    public Nivel() {
    }

    public Nivel(int id_nivel, String designacao) {
        this.id_nivel = id_nivel;
        this.designacao = designacao;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}

package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sala {

    @SerializedName("id_sala")
    @Expose
    private int id_sala;

    @SerializedName("designacao")
    @Expose
    private String designacao;

    public Sala() {
    }

    public Sala(int id_sala, String designacao) {
        this.id_sala = id_sala;
        this.designacao = designacao;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id_sala=" + id_sala +
                ", designacao='" + designacao + '\'' +
                '}';
    }
}

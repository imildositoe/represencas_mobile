package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regime {

    @SerializedName("id_regime")
    @Expose
    private int id_regime;

    @SerializedName("designacao")
    @Expose
    private String designacao;

    public Regime() {
    }

    public Regime(int id_regime, String designacao) {
        this.id_regime = id_regime;
        this.designacao = designacao;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public String toString() {
        return "Regime{" +
                "id_regime=" + id_regime +
                ", designacao=" + designacao +
                '}';
    }
}

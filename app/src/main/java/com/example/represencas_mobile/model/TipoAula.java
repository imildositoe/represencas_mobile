package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoAula {

    @SerializedName("id_tipo_aula")
    @Expose
    private int id_tipo_aula;

    @SerializedName("designacao")
    @Expose
    private String designacao;

    public TipoAula() {
    }

    public TipoAula(int id_tipo_aula, String designacao) {
        this.id_tipo_aula = id_tipo_aula;
        this.designacao = designacao;
    }

    public int getId_tipo_aula() {
        return id_tipo_aula;
    }

    public void setId_tipo_aula(int id_tipo_aula) {
        this.id_tipo_aula = id_tipo_aula;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public String toString() {
        return "TipoAula{" +
                "id_tipo_aula=" + id_tipo_aula +
                ", designacao='" + designacao + '\'' +
                '}';
    }
}

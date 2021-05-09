package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Disciplina {

    @SerializedName("id_disciplina")
    @Expose
    private int id_disciplina;

    @SerializedName("designacao")
    @Expose
    private String designacao;

    public Disciplina() {
    }

    public Disciplina(int id_disciplina, String designacao) {
        this.id_disciplina = id_disciplina;
        this.designacao = designacao;
    }

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id_disciplina=" + id_disciplina +
                ", designacao='" + designacao + '\'' +
                '}';
    }
}

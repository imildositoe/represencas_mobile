package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Curso {

    @SerializedName("id_curso")
    @Expose
    private int id_curso;

    @SerializedName("designacao")
    @Expose
    private String designacao;

    @SerializedName("duracao")
    @Expose
    private int duracao;

    public Curso() {
    }

    public Curso(int id_curso, String designacao, int duracao) {
        this.id_curso = id_curso;
        this.designacao = designacao;
        this.duracao = duracao;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id_curso=" + id_curso +
                ", designacao=" + designacao +
                ", duracao=" + duracao +
                '}';
    }
}

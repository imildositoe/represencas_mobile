package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inscricao {

    @SerializedName("id_inscricao")
    @Expose
    private int id_inscricao;

    @SerializedName("ano")
    @Expose
    private int ano;

    @SerializedName("id_estudante")
    @Expose
    private int id_estudante;

    @SerializedName("id_regime")
    @Expose
    private int id_regime;

    @SerializedName("id_disciplina_curso")
    @Expose
    private int id_disciplina_curso;

    public Inscricao() {
    }

    public Inscricao(int id_inscricao, int ano, int id_estudante, int id_regime, int id_disciplina_curso) {
        this.id_inscricao = id_inscricao;
        this.ano = ano;
        this.id_estudante = id_estudante;
        this.id_regime = id_regime;
        this.id_disciplina_curso = id_disciplina_curso;
    }

    public int getId_inscricao() {
        return id_inscricao;
    }

    public void setId_inscricao(int id_inscricao) {
        this.id_inscricao = id_inscricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getId_estudante() {
        return id_estudante;
    }

    public void setId_estudante(int id_estudante) {
        this.id_estudante = id_estudante;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public int getId_disciplina_curso() {
        return id_disciplina_curso;
    }

    public void setId_disciplina_curso(int id_disciplina_curso) {
        this.id_disciplina_curso = id_disciplina_curso;
    }

    @Override
    public String toString() {
        return "Inscricao{" +
                "id_inscricao=" + id_inscricao +
                ", ano=" + ano +
                ", id_estudante=" + id_estudante +
                ", id_regime=" + id_regime +
                ", id_disciplina_curso=" + id_disciplina_curso +
                '}';
    }
}

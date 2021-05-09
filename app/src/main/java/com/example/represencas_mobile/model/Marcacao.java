package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marcacao {

    @SerializedName("id_marcacao")
    @Expose
    private int id_marcacao;

    @SerializedName("is_presente")
    @Expose
    private boolean is_presente;

    @SerializedName("id_inscricao")
    @Expose
    private int id_inscricao;

    @SerializedName("id_aula")
    @Expose
    private int id_aula;

    public Marcacao() {
    }

    public Marcacao(int id_marcacao, boolean is_presente, int id_inscricao, int id_aula) {
        this.id_marcacao = id_marcacao;
        this.is_presente = is_presente;
        this.id_inscricao = id_inscricao;
        this.id_aula = id_aula;
    }

    public int getId_marcacao() {
        return id_marcacao;
    }

    public void setId_marcacao(int id_marcacao) {
        this.id_marcacao = id_marcacao;
    }

    public boolean isIs_presente() {
        return is_presente;
    }

    public void setIs_presente(boolean is_presente) {
        this.is_presente = is_presente;
    }

    public int getId_inscricao() {
        return id_inscricao;
    }

    public void setId_inscricao(int id_inscricao) {
        this.id_inscricao = id_inscricao;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    @Override
    public String toString() {
        return "Marcacao{" +
                "id_marcacao=" + id_marcacao +
                ", is_presente=" + is_presente +
                ", id_inscricao=" + id_inscricao +
                ", id_aula=" + id_aula +
                '}';
    }
}

package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Aula {

    @SerializedName("id_aula")
    @Expose
    private int id_aula;

    @SerializedName("data")
    @Expose
    private Date data;

    @SerializedName("id_alocacao")
    @Expose
    private int id_alocacao;

    @SerializedName("id_tipo_aula")
    @Expose
    private int id_tipo_aula;

    @SerializedName("id_sala")
    @Expose
    private int id_sala;

    public Aula() {
    }

    public Aula(int id_aula, Date data, int id_alocacao, int id_tipo_aula, int id_sala) {
        this.id_aula = id_aula;
        this.data = data;
        this.id_alocacao = id_alocacao;
        this.id_tipo_aula = id_tipo_aula;
        this.id_sala = id_sala;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId_alocacao() {
        return id_alocacao;
    }

    public void setId_alocacao(int id_alocacao) {
        this.id_alocacao = id_alocacao;
    }

    public int getId_tipo_aula() {
        return id_tipo_aula;
    }

    public void setId_tipo_aula(int id_tipo_aula) {
        this.id_tipo_aula = id_tipo_aula;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id_aula=" + id_aula +
                ", data=" + data +
                ", id_alocacao=" + id_alocacao +
                ", id_tipo_aula=" + id_tipo_aula +
                ", id_sala=" + id_sala +
                '}';
    }
}

package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Docente {

    @SerializedName("id_docente")
    @Expose
    private int id_docente;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("url_foto")
    @Expose
    private String url_foto;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("senha")
    @Expose
    private String senha;

    public Docente() {
    }

    public Docente(int id_docente, String nome, String url_foto, String email, String senha) {
        this.id_docente = id_docente;
        this.nome = nome;
        this.url_foto = url_foto;
        this.email = email;
        this.senha = senha;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "id_docente=" + id_docente +
                ", nome=" + nome +
                ", url_foto=" + url_foto +
                ", email=" + email +
                ", senha=" + senha +
                '}';
    }
}

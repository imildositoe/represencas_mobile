package com.example.represencas_mobile.model;

import java.util.Date;

public class Notificacao {

    private int id_notificacao;
    private String titulo;
    private String mensagem;
    private String data;
    private int is_vista;
    private int ano;
    private String regime;
    private String disciplina;

    public Notificacao() {
    }

    public Notificacao(int id_notificacao, String titulo, String mensagem, String data, int is_vista, int ano, String regime, String disciplina) {
        this.id_notificacao = id_notificacao;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.data = data;
        this.is_vista = is_vista;
        this.ano = ano;
        this.regime = regime;
        this.disciplina = disciplina;
    }

    public int getId_notificacao() {
        return id_notificacao;
    }

    public void setId_notificacao(int id_notificacao) {
        this.id_notificacao = id_notificacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIs_vista() {
        return is_vista;
    }

    public void setIs_vista(int is_vista) {
        this.is_vista = is_vista;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Notificacao{" +
                "id_notificacao=" + id_notificacao +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", data='" + data + '\'' +
                ", is_vista=" + is_vista +
                ", ano=" + ano +
                ", regime='" + regime + '\'' +
                ", disciplina='" + disciplina + '\'' +
                '}';
    }
}

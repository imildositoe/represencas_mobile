package com.example.represencas_mobile.model.fake_model;

public class Turmas {

    private int id_inscricao;
    private String disciplina;
    private String disciplina_sigla;
    private int ano;
    private String regime;
    private String regime_sigla;
    private int is_excluido;
    private double carga_horaria;
    private int id_alocacao;

    public Turmas() {
    }

    public Turmas(int id_inscricao, String disciplina, String disciplina_sigla, int ano, String regime, String regime_sigla, int is_excluido, double carga_horaria, int id_alocacao) {
        this.id_inscricao = id_inscricao;
        this.disciplina = disciplina;
        this.disciplina_sigla = disciplina_sigla;
        this.ano = ano;
        this.regime = regime;
        this.regime = regime_sigla;
        this.is_excluido = is_excluido;
        this.carga_horaria = carga_horaria;
        this.id_alocacao = id_alocacao;
    }

    public int getId_inscricao() {
        return id_inscricao;
    }

    public void setId_inscricao(int id_inscricao) {
        this.id_inscricao = id_inscricao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina_sigla() {
        return disciplina_sigla;
    }

    public void setDisciplina_sigla(String disciplina_sigla) {
        this.disciplina_sigla = disciplina_sigla;
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

    public String getRegime_sigla() {
        return regime_sigla;
    }

    public void setRegime_sigla(String regime_sigla) {
        this.regime_sigla = regime_sigla;
    }

    public int getIs_excluido() {
        return is_excluido;
    }

    public void setIs_excluido(int is_excluido) {
        this.is_excluido = is_excluido;
    }

    public double getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(double carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public int getId_alocacao() {
        return id_alocacao;
    }

    public void setId_alocacao(int id_alocacao) {
        this.id_alocacao = id_alocacao;
    }

    @Override
    public String toString() {
        return "Turmas{" +
                "id_inscricao=" + id_inscricao +
                ", disciplina='" + disciplina + '\'' +
                ", disciplina_sigla='" + disciplina_sigla + '\'' +
                ", ano=" + ano +
                ", regime='" + regime + '\'' +
                ", regime_sigla='" + regime_sigla + '\'' +
                ", is_excluido=" + is_excluido +
                ", carga_horaria=" + carga_horaria +
                ", id_alocacao=" + id_alocacao +
                '}';
    }
}

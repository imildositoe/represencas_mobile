package com.example.represencas_mobile.model.fake_model;

public class DoughnutChartFaltas {
     private int carga_horaria;
     private int faltas;

    public DoughnutChartFaltas() {
    }

    public DoughnutChartFaltas(int carga_horaria, int faltas) {
        this.carga_horaria = carga_horaria;
        this.faltas = faltas;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return "DoughnutChartFaltas{" +
                "carga_horaria=" + carga_horaria +
                ", faltas=" + faltas +
                '}';
    }
}

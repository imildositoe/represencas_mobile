package com.example.represencas_mobile.model.list_model;

import com.example.represencas_mobile.model.fake_model.DoughnutChartFaltas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaltasList {

    @SerializedName("doughnut_chart_faltas")
    private List<DoughnutChartFaltas> faltas;

    public FaltasList() {
    }

    public FaltasList(List<DoughnutChartFaltas> faltas) {
        this.faltas = faltas;
    }

    public List<DoughnutChartFaltas> getFaltas() {
        return faltas;
    }

    public void setFaltas(List<DoughnutChartFaltas> faltas) {
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return "FaltasList{" +
                "faltas=" + faltas +
                '}';
    }
}

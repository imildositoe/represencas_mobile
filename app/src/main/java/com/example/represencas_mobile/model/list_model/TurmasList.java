package com.example.represencas_mobile.model.list_model;

import com.example.represencas_mobile.model.fake_model.Turmas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TurmasList {

    @SerializedName("turmas")
    private List<Turmas> turmas;

    public TurmasList() {
    }

    public TurmasList(List<Turmas> turmas) {
        this.turmas = turmas;
    }

    public List<Turmas> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turmas> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return "TurmasList{" +
                "turmas=" + turmas +
                '}';
    }
}

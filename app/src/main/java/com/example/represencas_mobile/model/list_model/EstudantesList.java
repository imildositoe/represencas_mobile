package com.example.represencas_mobile.model.list_model;

import com.example.represencas_mobile.model.fake_model.Estudantes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EstudantesList {

    @SerializedName("estudantes")
    private List<Estudantes> estudantes;

    public EstudantesList() {
    }

    public EstudantesList(List<Estudantes> estudantes) {
        this.estudantes = estudantes;
    }

    public List<Estudantes> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudantes> estudantes) {
        this.estudantes = estudantes;
    }

    @Override
    public String toString() {
        return "EstudantesList{" +
                "estudantes=" + estudantes +
                '}';
    }
}

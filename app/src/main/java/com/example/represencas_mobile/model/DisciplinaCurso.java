package com.example.represencas_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisciplinaCurso {

    @SerializedName("id_disciplina_curso")
    @Expose
    private int id_disciplina_curso;

    @SerializedName("id_curso")
    @Expose
    private int id_curso;

    @SerializedName("id_disciplina")
    @Expose
    private int id_disciplina;

    @SerializedName("id_nivel")
    @Expose
    private int id_nivel;

    public DisciplinaCurso() {
    }

    public DisciplinaCurso(int id_disciplina_curso, int id_curso, int id_disciplina, int id_nivel) {
        this.id_disciplina_curso = id_disciplina_curso;
        this.id_curso = id_curso;
        this.id_disciplina = id_disciplina;
        this.id_nivel = id_nivel;
    }

    public int getId_disciplina_curso() {
        return id_disciplina_curso;
    }

    public void setId_disciplina_curso(int id_disciplina_curso) {
        this.id_disciplina_curso = id_disciplina_curso;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    @Override
    public String toString() {
        return "DisciplinaCurso{" +
                "id_disciplina_curso=" + id_disciplina_curso +
                ", id_curso=" + id_curso +
                ", id_disciplina=" + id_disciplina +
                ", id_nivel=" + id_nivel +
                '}';
    }
}

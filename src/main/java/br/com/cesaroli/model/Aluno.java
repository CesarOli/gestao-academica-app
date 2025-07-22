package br.com.cesaroli.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aluno extends Pessoa{

    private String matricula;
    private Curso curso;
    private Map<Disciplina, List<Double>> notas;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Map<Disciplina, List<Double>> getNotas() {
        return notas;
    }

    public void setNotas(Map<Disciplina, List<Double>> notas) {
        this.notas = notas;
    }

    public Aluno() {
        this.notas = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + getNome() + '\'' +
                ", matrícula='" + matricula + '\'' +
                ", curso=" + curso +
                '}';
    }
}
package br.com.cesaroli.model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa {

    private String departamento;
    private List<Disciplina> disciplinasLecionadas;
    private double salario;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void setDisciplinasLecionadas(List<Disciplina> disciplinasLecionadas) {
        this.disciplinasLecionadas = disciplinasLecionadas;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Professor() {
        this.disciplinasLecionadas = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + getNome() + '\'' +
                ", departamento=" + getDepartamento() +
                '}';
    }
}

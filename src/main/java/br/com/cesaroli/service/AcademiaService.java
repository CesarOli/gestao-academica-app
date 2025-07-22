package br.com.cesaroli.service;

import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Curso;
import br.com.cesaroli.model.Disciplina;
import br.com.cesaroli.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class AcademiaService {

    private List<Aluno> alunos = new ArrayList<>();
    private List<Professor> professores = new ArrayList<>();
    private List<Curso> cursos = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Aluno cadastrarAluno(String nome, String email, String matricula, Curso curso) {

        Aluno novoAluno = new Aluno();

        novoAluno.setNome(nome);
        novoAluno.setEmail(email);
        novoAluno.setMatricula(matricula);
        novoAluno.setCurso(curso);

        this.alunos.add(novoAluno);

        System.out.println("Aluno " + nome + " cadastrado!");

        return novoAluno;
    }
}

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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

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

    public Professor cadastrarProfessor(String nome, String email, String departamento, double salario ) {
        Professor novoProfessor = new Professor();
        novoProfessor.setNome(nome);
        novoProfessor.setEmail(email);
        novoProfessor.setDepartamento(departamento);
        novoProfessor.setSalario(salario);

        this.professores.add(novoProfessor);

        System.out.println("Professor " + nome + " cadastrado com sucesso!");

        return novoProfessor;
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    public List<Professor> getProfessores() {
        return this.professores;
    }

    public void matricularAlunoEmDisciplina(Aluno aluno, Disciplina disciplina) {

        if (aluno.getNotas().containsKey(disciplina)) {
            System.out.println("ERRO: Aluno' " + aluno.getNome() + "' já está matrículado(a) na disciplina '" + disciplina.getNome() + "'.");
            return;
        }
        aluno.getNotas().put(disciplina, new ArrayList<>());
        System.out.println("Matrícula do aluno " + aluno.getNome() + " na disciplina " + disciplina.getNome() + " realizada com sucesso!");
    }
}

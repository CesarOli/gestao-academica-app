package br.com.cesaroli.service;

import br.com.cesaroli.dao.AlunoDAO;
import br.com.cesaroli.dao.ProfessorDAO;
import br.com.cesaroli.dao.DisciplinaDAO;
import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Professor;
import br.com.cesaroli.model.Curso;
import br.com.cesaroli.model.Disciplina;


import java.util.ArrayList;
import java.util.List;


public class AcademiaService {

    private AlunoDAO alunoDAO;
    private ProfessorDAO professorDAO;
    private DisciplinaDAO disciplinaDAO;

    public AcademiaService(AlunoDAO alunoDAO, ProfessorDAO professorDAO, DisciplinaDAO disciplinaDAO) {
        this.alunoDAO = alunoDAO;
        this.professorDAO = professorDAO;
        this.disciplinaDAO = disciplinaDAO;
    }

    public Aluno cadastrarAluno(String nome, String email, String matricula, Curso curso) {
        Aluno novoAluno = new Aluno(nome, email, matricula);
        novoAluno.setCurso(curso);
        this.alunoDAO.salvar(novoAluno);
        return novoAluno;
    }

    public List<Aluno> getAlunos() {
        return this.alunoDAO.buscarTodos();
    }

    public Professor cadastrarProfessor(String nome, String email, String departamento, double salario) {
        Professor novoProfessor = new Professor(nome, email, departamento);
        novoProfessor.setSalario(salario);
        this.professorDAO.salvar(novoProfessor);
        return novoProfessor;
    }

    public List<Professor> getProfessores() {
        return this.professorDAO.buscarTodos();
    }

    public Disciplina cadastrarDisciplina(String nome) {
        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(nome);
        this.disciplinaDAO.salvar(novaDisciplina);
        return novaDisciplina;
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinaDAO.buscarTodos();
    }

    public void matricularAlunoEmDisciplina(Aluno aluno, Disciplina disciplina) {
        if (aluno.getNotas().containsKey(disciplina)) {
            System.out.println("ERRO: Aluno' " + aluno.getNome() + "' já está matriculado(a) na disciplina '" + disciplina.getNome() + "'.");
            return;
        }
        aluno.getNotas().put(disciplina, new ArrayList<>());
        System.out.println("Matrícula do aluno " + aluno.getNome() + " na disciplina " + disciplina.getNome() + " realizada com sucesso!");
    }

    public void lancarNota(Aluno aluno, Disciplina disciplina, double nota) {
        if (!aluno.getNotas().containsKey(disciplina)) {
            System.out.println("ERRO: O aluno " + aluno.getNome() + " não está matriculado nesta disciplina " + disciplina.getNome() + ".");
            return;
        }
        if (nota < 0 || nota > 10) {
            System.out.println("ERRO: Nota inválida. A nota deve estar no intervalo entre 0 e 10.");
            return;
        }
        aluno.getNotas().get(disciplina).add(nota);
        System.out.println("Nota " + nota + " lançada para o aluno " + aluno.getNome() + " na disciplina solicitada, '" + disciplina.getNome() + "'.");
    }

    public double calcularMediaDoAlunoNaDisciplina(Aluno aluno, Disciplina disciplina) {
        if (!aluno.getNotas().containsKey(disciplina)) {
            return 0.0;
        }
        List<Double>notas = aluno.getNotas().get(disciplina);
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }
}

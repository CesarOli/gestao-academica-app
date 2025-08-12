package br.com.cesaroli.service;

import br.com.cesaroli.dao.AlunoDAO;
import br.com.cesaroli.dao.DisciplinaDAO;
import br.com.cesaroli.dao.MatriculaDAO;
import br.com.cesaroli.dao.ProfessorDAO;
import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Curso;
import br.com.cesaroli.model.Disciplina;
import br.com.cesaroli.model.Professor;
import br.com.cesaroli.repository.AlunoRepository;
import br.com.cesaroli.repository.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;


public class AcademiaService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final DisciplinaDAO disciplinaDAO;
    private final MatriculaDAO matriculaDAO;

    public AcademiaService(AlunoRepository alunoRepository, ProfessorRepository professorRepository, DisciplinaDAO disciplinaDAO, MatriculaDAO matriculaDAO) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.disciplinaDAO = disciplinaDAO;
        this.matriculaDAO = matriculaDAO;
    }

    public Aluno cadastrarAluno(String nome, String email, String matricula, Curso curso) {
        Aluno novoAluno = new Aluno(nome, email, matricula);
        novoAluno.setCurso(curso);
        this.alunoRepository.salvar(novoAluno);
        return novoAluno;
    }

    public List<Aluno> getAlunos() {
        return this.alunoRepository.buscarTodos();
    }

    public Professor cadastrarProfessor(String nome, String email, String departamento, double salario) {
        Professor novoProfessor = new Professor();
        novoProfessor.setNome(nome);
        novoProfessor.setEmail(email);
        novoProfessor.setDepartamento(departamento);
        novoProfessor.setSalario(salario);
        this.professorRepository.salvar(novoProfessor);
        return novoProfessor;
    }

    public List<Professor> getProfessores() {
        return this.professorRepository.buscarTodos();
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
        this.matriculaDAO.salvar(aluno, disciplina);
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
        List<Double> notas = aluno.getNotas().get(disciplina);
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

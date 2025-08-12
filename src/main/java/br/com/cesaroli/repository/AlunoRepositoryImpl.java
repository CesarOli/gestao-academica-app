package br.com.cesaroli.repository;

import br.com.cesaroli.dao.AlunoDAO;
import br.com.cesaroli.model.Aluno;
import java.util.List;
import java.util.Optional;

public class AlunoRepositoryImpl implements AlunoRepository {

    private final AlunoDAO alunoDAO;

    public AlunoRepositoryImpl(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    @Override
    public void salvar(Aluno aluno) {
        alunoDAO.salvar(aluno);
    }

    @Override
    public List<Aluno> buscarTodos() {
        return alunoDAO.buscarTodos();
    }

    @Override
    public Optional<Aluno> buscarPorMatricula(String matricula) {
        System.out.println("Funcionalidade 'buscarPorMatricula' ainda não implementada no DAO.");
        return Optional.empty();
    }

    @Override
    public Optional<Aluno> buscarPorId(Long id) {
        return Optional.ofNullable(alunoDAO.buscarPorId(id));
    }
}
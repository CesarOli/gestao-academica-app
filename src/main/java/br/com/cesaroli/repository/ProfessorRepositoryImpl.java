package br.com.cesaroli.repository;

import br.com.cesaroli.dao.ProfessorDAO;
import br.com.cesaroli.model.Professor;

import java.util.List;
import java.util.Optional;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    private final ProfessorDAO professorDAO;

    public ProfessorRepositoryImpl(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    @Override
    public void salvar(Professor professor) {
        professorDAO.salvar(professor);
    }

    @Override
    public List<Professor> buscarTodos() {
        return professorDAO.buscarTodos();
    }

    @Override
    public Optional<Professor> buscarPorId(Long id) {
        return Optional.empty();
    }
}

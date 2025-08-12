package br.com.cesaroli.repository;

import br.com.cesaroli.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {

    void salvar(Professor professor);

    List<Professor> buscarTodos();

    Optional<Professor> buscarPorId(Long id);
}

package br.com.cesaroli.repository;

import br.com.cesaroli.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository {

    void salvar(Aluno aluno);

    List<Aluno> buscarTodos();
    Optional<Aluno> buscarPorMatricula(String matricula);
    Optional<Aluno> buscarPorId(Long id);
}

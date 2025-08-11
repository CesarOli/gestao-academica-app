package br.com.cesaroli.dao;

import br.com.cesaroli.factory.ConnectionFactory;
import br.com.cesaroli.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class AlunoDAO {

    public void salvar(Aluno aluno) {

        String sql = "INSERT INTO aluno (nome, email, matricula) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2,aluno.getEmail());
            pstmt.setString(3, aluno.getMatricula());

            pstmt.executeUpdate();

            System.out.println("Aluno'" + aluno.getNome() + "' salvo no banco de dados com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno no banco de dados.", e);
        }
    }

    public List<Aluno>buscarTodos() {

        String sql = "SELECT id, nome, email, matricula FROM aluno";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getString("nome"), rs.getString("email"), rs.getString("matricula"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alunos no banco de dados.", e);
        }
        return alunos;
    }
}
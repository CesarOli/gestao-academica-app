package br.com.cesaroli.dao;

import br.com.cesaroli.factory.ConnectionFactory;
import br.com.cesaroli.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, email, matricula) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setString(3, aluno.getMatricula());
            ps.execute();
            System.out.println("Aluno '" + aluno.getNome() + "' salvo no banco de dados com sucesso!");
        } catch (SQLException e) {
            System.out.println("ERRO ao salvar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> buscarTodos() {
        String sql = "SELECT id, nome, email, matricula FROM aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new  Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setMatricula(rs.getString("matricula"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao buscar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula")
                );
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    public Aluno buscarPorId(Long id) {
        String sql = "SELECT id, nome, email, matricula FROM aluno WHERE id = ?";
        Aluno aluno = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setNome(rs.getString("nome"));
                    aluno.setEmail(rs.getString("email"));
                    aluno.setMatricula(rs.getString("matricula"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por ID", e);
        }
        return aluno;
    }
}

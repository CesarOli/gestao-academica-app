package br.com.cesaroli.dao;

import br.com.cesaroli.factory.ConnectionFactory;
import br.com.cesaroli.model.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void salvar(Professor professor) {

        String sql = "INSERT INTO professor (nome, email, departamento, salario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getEmail());
            pstmt.setString(3, professor.getDepartamento());
            pstmt.setDouble(4, professor.getSalario());
            pstmt.executeUpdate();
            System.out.println("Professor'" + professor.getNome() + "' salvo no banco de dados com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o professor no banco de dados.", e);
        }
    }

    public List<Professor> buscarTodos() {

        String sql = "SELECT id, nome, email, departamento, salario FROM professor";
        List<Professor> professores = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setDepartamento(rs.getString("departamento"));
                professor.setSalario(rs.getDouble("salario"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alunos no banco de dados.", e);
        }
        return professores;
    }

    public Professor buscarPorId(Integer id) {
        String sql = "SELECT * FROM professor WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Professor(
                );
            }
        } catch (SQLException e) {
            System.out.println("ERRO ao buscar professor: " + e.getMessage());
        }
        return null;
    }
}

package br.com.cesaroli.dao;

import br.com.cesaroli.factory.ConnectionFactory;
import br.com.cesaroli.model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void salvar(Disciplina disciplina) {

        String sql = "INSERT INTO disciplina (nome) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, disciplina.getNome());

            pstmt.executeUpdate();
    } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar disciplina no banco de dados.", e);
        }
    }

    public List<Disciplina> buscarTodos() {

        String sql = "SELECT id, nome FROM disciplina";
        List<Disciplina> disciplinas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar disciplina", e);
        }
        return disciplinas;
    }
}

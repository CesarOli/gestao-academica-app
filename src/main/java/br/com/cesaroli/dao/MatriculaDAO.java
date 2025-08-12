package br.com.cesaroli.dao;

import br.com.cesaroli.factory.ConnectionFactory;
import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatriculaDAO {

    public void salvar(Aluno aluno, Disciplina disciplina) {
        // Verifica se os IDs necessários existem antes de tentar salvar
        if (aluno.getId() == null || disciplina.getId() == null) {
            System.err.println("ERRO: Aluno ou Disciplina sem ID. Não é possível matricular.");
            return;
        }

        String sql = "INSERT INTO matricula (aluno_id, disciplina_id) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, aluno.getId());
            pstmt.setLong(2, disciplina.getId());
            pstmt.executeUpdate();

            System.out.println("Matrícula do aluno '" + aluno.getNome() + "' na disciplina '" + disciplina.getNome() + "' salva no banco de dados!");

        } catch (SQLException e) {
            // Trata o erro de matrícula duplicada que definimos no banco (UNIQUE constraint)
            if (e.getSQLState().equals("23505")) { // Código de erro para violação de constraint única
                System.err.println("ERRO: O aluno já está matriculado nesta disciplina.");
            } else {
                System.err.println("ERRO ao salvar matrícula: " + e.getMessage());
            }
        }
    }
}
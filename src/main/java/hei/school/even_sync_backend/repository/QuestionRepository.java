package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.datasource.DataSource;
import hei.school.even_sync_backend.entity.Question;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {

    public void createQuestion(String idSession, String questionContainer, String userName) throws SQLException {

        String sql =
                "INSERT INTO questions (contenu, nom, session_id) VALUES (?, ?, ?)";

        try (
                Connection connection = DataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setString(1, questionContainer);
            stmt.setString(2, userName);
            stmt.setString(3, idSession);

            stmt.executeUpdate();
        }
    }

    public List<Question> getBySession(String sessionId) throws SQLException {

        List<Question> questions = new ArrayList<>();

        String sql =
                "SELECT * FROM questions WHERE session_id = ? ORDER BY upvotes DESC, created_at DESC";

        try (
                Connection connection = DataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setString(1, sessionId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Question q = new Question();

                q.setId(rs.getString("id"));
                q.setContenu(rs.getString("contenu"));
                q.setNom(rs.getString("nom"));
                q.setUpvotes(rs.getInt("upvotes"));
                q.setSessionId(rs.getString("session_id"));
                q.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                questions.add(q);
            }
        }

        return questions;
    }

    public void upvote(String questionId) throws SQLException {

        String sql =
                "UPDATE questions SET upvotes = upvotes + 1 WHERE id = ?";

        try (
                Connection connection = DataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setString(1, questionId);

            stmt.executeUpdate();
        }
    }

    public void deleteQuestion(String questionId) throws SQLException {

        String sql = "DELETE FROM questions WHERE id = ?";

        try (
                Connection connection = DataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setString(1, questionId);

            stmt.executeUpdate();
        }
    }
}
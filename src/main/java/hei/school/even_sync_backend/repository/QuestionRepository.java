package hei.school.even_sync_backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import hei.school.even_sync_backend.entity.Question;
import hei.school.even_sync_backend.entity.User;

@Repository
public class QuestionRepository {

    private Connection connection;

    public QuestionRepository(Connection connection) {
        this.connection = connection;
    }

    public void createQuestion(String idSession, String questionContainer, String userName) throws SQLException {
        String sql = "INSERT INTO questions (contenu, nom, session_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, questionContainer);
            stmt.setString(2, userName);
            stmt.setString(3, idSession);
            stmt.executeUpdate();
        }
    }

    public List<User> getUserInVoteQuestion (String questionId) throws SQLException  {
        List<User> listOfUser = new ArrayList<>();
        String sql = "SELECT id, name FROM user JOIN vote ON vote.user_id=user.id WHERE vote.question_id=?";
        try (PreparedStatement stmt =  connection.prepareStatement(sql)) {
            stmt.setString(1, questionId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                listOfUser.add(user);
            }
        }
        return listOfUser;
    }

    public List<Question> getBySession(String sessionId) throws SQLException {

        List<Question> questions = new ArrayList<>();
        String sql = "SELECT id,contenu,nom,upvotes,session_id,created_at FROM questions WHERE session_id = ? ORDER BY upvotes DESC, created_at DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sessionId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getString("id"));
                q.setContenu(rs.getString("contenu"));
                q.setNom(rs.getString("nom"));
                q.setUpvotes(getUserInVoteQuestion(q.getId()));
                q.setSessionId(rs.getString("session_id"));
                q.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                questions.add(q);
            }
        }
        return questions;
    }

    public void upvote(String userId,String questionId) throws SQLException {
        String sql = "INSERT INTO vote (user_id,question_id) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, questionId);
            stmt.executeUpdate();
        }
    }

    public void downvote(String userId,String questionId) throws SQLException {
        String sql = "DELETE FROM vote WHERE user_id=? AND question_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, questionId);
            stmt.executeUpdate();
        }
    }

    public void delete(String sessionId,String questionId) throws SQLException {
        String sql = "DELETE FROM questions WHERE session_id=? AND id=";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, questionId);
            stmt.executeUpdate();
        }
    }
}

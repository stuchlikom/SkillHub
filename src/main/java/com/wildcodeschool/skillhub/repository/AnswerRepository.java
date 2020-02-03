package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Answer;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class AnswerRepository implements CrudDao<Answer> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

    @Override
    public Answer save(Answer answer) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "INSERT INTO answer (question, expert, date, text) VALUES (?, ?, NOW(), ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setLong(1, answer.getQuestion());
            statement.setLong(2, answer.getExpert());
            statement.setString(3, answer.getAnswerText());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long answerId = generatedKeys.getLong(1);
                answer.setAnswerId(answerId);
                return answer;
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(generatedKeys);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Answer findById(Long answerId) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM answer  WHERE answerid = ?;"
            );
            statement.setLong(1, answerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long question = resultSet.getLong("question");
                Long expert = resultSet.getLong("expert");
                Date date = resultSet.getDate("date");
                String answerText = resultSet.getString("text");
                return new Answer(answerId, question, expert, date, answerText, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Answer> findAll(Long filter) {   // findAll(Long filter)
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM answer;"
            );
            ResultSet resultSet = statement.executeQuery();

            List<Answer> answers = new ArrayList<>();

            while (resultSet.next()) {
                Long answerId = resultSet.getLong("answerid");
                Long question = resultSet.getLong("question");
                Long expert = resultSet.getLong("expert");
                Date date = resultSet.getDate("date");
                String answerText = resultSet.getString("text");
                answers.add(new Answer(answerId, question, expert, date, answerText, null));
            }
            return answers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Answer update(Answer answer) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE answer SET question=?, expert=?, date=?, text=? WHERE answerid=?"
            );
            statement.setLong(1, answer.getQuestion());
            statement.setLong(2, answer.getExpert());
            statement.setDate(3, answer.getDate());
            statement.setString(4, answer.getAnswerText());
            
            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return answer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long answerId) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM answer WHERE answerid=?"
            );
            statement.setLong(1, answerId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

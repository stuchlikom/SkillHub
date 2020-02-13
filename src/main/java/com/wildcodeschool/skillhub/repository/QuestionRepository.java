package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class QuestionRepository implements CrudDao<Question> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

    @Override
    public Question save(Question question) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "INSERT INTO question (questioner, date, text, category) VALUES (?, NOW(), ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setLong(1, question.getQuestioner());
            statement.setString(2, question.getQuestionText());
            statement.setLong(3, question.getCategory());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long questionId = generatedKeys.getLong(1);
                question.setQuestionId(questionId);
                return question;
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
    public Question findById(Long questionId) {
/*
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;         
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT question.*, category.categoryname FROM question JOIN category ON question.category=category.categoryid WHERE question.questionid = ?;"
            );
            statement.setLong(1, questionId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long questioner = resultSet.getLong("questioner");
                Date questionDate = resultSet.getDate("questio.date");
                String questionText = resultSet.getString("question.text");
                Long category = resultSet.getLong("category");
                String categoryName = resultSet.getString("categoryname");
                return new Question(questionId, questioner, questionDate, null, questionText, category, categoryName, null, null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
*/
        return null;

    }

    @Override
    public List<Question> findAll(Long filter) {    // findAll(Long filter)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;           
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD );
            String catQuery;
            if (filter != 0) {
                catQuery = " WHERE question.category=" + filter +" ";
            } else {
                catQuery = " ";
            }
            statement = connection.prepareStatement(
                    "SELECT " +
                        "question.questionid, " +
                        "question.questioner, " +
                        "question.date, " +
                        "question.text, " +
                        "question.category, " +
                        "category.categoryname, " +
                        "answer.text, " +
                        "answer.date, " +
                        "answer.expert, " +
                        "uq.userid, " +
                        "ua.userid, " +
                        "uq.nickname, " +
                        "ua.nickname, " +
                        "aq.avatarid, " +
                        "aa.avatarid, " +
                        "aq.avatar, " +
                        "aa.avatar " +
                            "FROM question " +
                    "LEFT OUTER JOIN answer ON answer.question=questionid " +
                    "LEFT OUTER JOIN user uq ON question.questioner=uq.userid " +
                    "LEFT OUTER JOIN user ua ON answer.expert=ua.userid " +
                    "LEFT OUTER JOIN avatar aq ON aq.avatarid=uq.userid " +
                    "LEFT OUTER JOIN avatar aa ON aa.avatarid=ua.userid " +
                    "INNER JOIN category ON question.category=category.categoryid " + catQuery +
                    "ORDER BY question.questionid;"
            );
            resultSet = statement.executeQuery();

            List<Question> questions = new ArrayList<>();

            while (resultSet.next()) {
                Long questionId = resultSet.getLong("questionid");
                Long questioner = resultSet.getLong("questioner");
                Date questionDate = resultSet.getDate("question.date");
                Date answerDate = resultSet.getDate("answer.date");
                String questionText = resultSet.getString("question.text");
                Long category = resultSet.getLong("question.category");
                String categoryName = resultSet.getString("categoryname");
                String answerText = resultSet.getString("answer.text");
                String questionNick = resultSet.getString("uq.nickname");
                String answerNick = resultSet.getString("ua.nickname");
                Long expert = resultSet.getLong("answer.expert");
                //System.out.println("qN: " + questionNick + " aN: " + answerNick + " E: " + expert);
                questions.add(new Question(questionId, questioner, questionDate, answerDate, questionText, category, categoryName, answerText, questionNick, answerNick, expert));
            }
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Question update(Question question) {
        Connection connection = null;
        PreparedStatement statement = null;        
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "UPDATE question SET text=?, category=? WHERE questionid=?"
            );
            statement.setString(1, question.getQuestionText());
            statement.setLong(2, question.getCategory());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return question;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void deleteById(Long questionId) {
        Connection connection = null;
        PreparedStatement statement = null;        
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "DELETE FROM question WHERE questionid=?"
            );
            statement.setLong(1, questionId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
    }

}

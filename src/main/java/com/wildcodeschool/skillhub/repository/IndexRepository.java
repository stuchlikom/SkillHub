package com.wildcodeschool.skillhub.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.util.JdbcUtils;


public class IndexRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin@localhost";
    private final static String DB_PASSWORD = "sPfdA-1234";

    public Question findByCategory(Long filter) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;         
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT question.*, category.categoryname FROM question JOIN category ON question.category=category.categoryid WHERE category.categoryid = ?;"
            );
            statement.setLong(1, filter);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long questioner = resultSet.getLong("questioner");
                Date questionDate = resultSet.getDate("question.date");
                String questionText = resultSet.getString("question.text");
                Long category = resultSet.getLong("category");
                String categoryName = resultSet.getString("categoryname");
                return new Question(filter, questioner, questionDate, null, questionText, category, categoryName, null, null, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }
}
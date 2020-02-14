package com.wildcodeschool.skillhub.repository;

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
public class LoggedInUserRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

    public static Long findId() {    // findAll(Long filter)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;           
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD );

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            statement = connection.prepareStatement(
                    "SELECT user.userid FROM user WHERE nickname = ?;"
            );
            statement.setString(1, userDetails.getUsername());
            resultSet = statement.executeQuery();

            Long loggedInUserId = 0l;
            while (resultSet.next()) {
                loggedInUserId = resultSet.getLong("user.userid");
            }

            //System.out.println("lU: " + loggedInUserId);

            return loggedInUserId;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

}

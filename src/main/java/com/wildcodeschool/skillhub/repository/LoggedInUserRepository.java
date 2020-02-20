package com.wildcodeschool.skillhub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.*;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class LoggedInUserRepository {

    private final static String DB_URL = "jdbc:mariadb://db02eylw.mariadb.hosting.zone";
    private final static String DB_USER = "db02eylw_aevsybn";
    private final static String DB_PASSWORD = "3GQMpC*X";

    public static Long findId() {    // findAll(Long filter)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;           
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD );

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            statement = connection.prepareStatement(
                    "SELECT userid FROM db02eylw.user WHERE nickname = ?;"
            );
            statement.setString(1, userDetails.getUsername());
            resultSet = statement.executeQuery();

            Long loggedInUserId = 0l;
            while (resultSet.next()) {
                loggedInUserId = resultSet.getLong("userid");
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

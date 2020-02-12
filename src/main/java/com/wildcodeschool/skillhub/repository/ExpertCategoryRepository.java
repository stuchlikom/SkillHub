package com.wildcodeschool.skillhub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class ExpertCategoryRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

    public static List<Long> findAll() {    // findAll(Long filter)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;           
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD );

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //System.out.println("User name: " + userDetails.getUsername());

            statement = connection.prepareStatement(
                    "SELECT usercategory.categoryid FROM usercategory JOIN user ON usercategory.userid = user.userid WHERE nickname = ?;"
            );
            statement.setString(1, userDetails.getUsername());
            resultSet = statement.executeQuery();

            List<Long> expertCategories = new ArrayList<>();

            while (resultSet.next()) {
                Long categoryId = resultSet.getLong("usercategory.categoryid");
                expertCategories.add(new Long(categoryId));
            }

            //for (Long catid : expertCategories) {
            //    System.out.println("Cats: " + catid);
            //}

            return expertCategories;
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

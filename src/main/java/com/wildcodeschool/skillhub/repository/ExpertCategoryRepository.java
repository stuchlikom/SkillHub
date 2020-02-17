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
public class ExpertCategoryRepository {

    private final static String DB_URL = "jdbc:mariadb://db02eylw.mariadb.hosting.zone";
    private final static String DB_USER = "db02eylw_aevsybn";
    private final static String DB_PASSWORD = "3GQMpC*X";

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
                    "SELECT uc.categoryid FROM db02eylw.usercategory AS uc JOIN db02eylw.user AS u ON uc.userid = u.userid WHERE u.nickname = ?;"
            );
            statement.setString(1, userDetails.getUsername());
            resultSet = statement.executeQuery();

            List<Long> expertCategories = new ArrayList<>();

            while (resultSet.next()) {
                Long categoryId = resultSet.getLong("uc.categoryid");
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

package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class CategoryRepository implements CrudDao<Category> {

    private final static String DB_URL = "jdbc:mariadb://db02eylw.mariadb.hosting.zone";
    private final static String DB_USER = "db02eylw_aevsybn";
    private final static String DB_PASSWORD = "3GQMpC*X";

    @Override
    public List<Category> findAll(Long filter) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.category ORDER by categoryname;"
            );
            resultSet = statement.executeQuery();

            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                Long categoryId = resultSet.getLong("categoryid");
                String categoryName = resultSet.getString("categoryname");
                categories.add(new Category(categoryId, categoryName));
            }
            // **************************** sortieren der Ausgabe nach Id mit 'implements Comparable<Category>' in entity 
            Collections.sort(categories);

            return categories;

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
    public Category save(Category category) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "INSERT INTO db02eylw.category (categoryname) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, category.getCategoryName());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long categoryId = generatedKeys.getLong(1);
                category.setCategoryId(categoryId);
                return category;
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
    public Category findById(Long categoryId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.category WHERE categoryid = ?;"
            );
            statement.setLong(1, categoryId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String categoryName = resultSet.getString("categoryname");
                return new Category(categoryId, categoryName);
            }
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
    public Category update(Category category) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.prepareStatement("UPDATE db02eylw.category SET categoryname=? WHERE categoryid=?");
            statement.setString(1, category.getCategoryName());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void deleteById(Long categoryId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "DELETE FROM db02eylw.category WHERE categoryid=?"
            );
            statement.setLong(1, categoryId);

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

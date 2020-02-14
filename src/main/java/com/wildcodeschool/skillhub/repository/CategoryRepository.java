package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Category;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository implements CrudDao<Category> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

    @Override
    public Category save(Category category) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO category (categoryname) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, category.getCategoryName());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long categoryId = generatedKeys.getLong(1);
                category.setCategoryId(categoryId);
                return category;
            } else {
                throw new SQLException("failed to get inserted id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category findById(Long categoryId) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM category WHERE categoryid = ?;"
            );
            statement.setLong(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String categoryName = resultSet.getString("categoryname");
                return new Category(categoryId, categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findAll(Long filter) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM category ORDER by categoryname;"
            );
            ResultSet resultSet = statement.executeQuery();

            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                Long categoryId = resultSet.getLong("categoryid");
                String categoryName = resultSet.getString("categoryname");
                categories.add(new Category(categoryId, categoryName));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category update(Category category) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE category SET categoryname=? WHERE categoryid=?"
            );
            statement.setString(1, category.getCategoryName());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long categoryId) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM category WHERE categoryid=?"
            );
            statement.setLong(1, categoryId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

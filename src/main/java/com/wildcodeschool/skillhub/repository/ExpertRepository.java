package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Expert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpertRepository implements CrudDao<Expert> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";
   
    
        @Override
        public List<Expert> findAll(Long filter) {

            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;  
    
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                statement = connection.prepareStatement("SELECT * FROM SkillHubDB.user where role = 'ROLE_EXPERT' or role = 'ROLE_ADMIN'");
                resultSet = statement.executeQuery();
    
                List<Expert> experts = new ArrayList<>();

                while (resultSet.next()) {
                    Long userid = resultSet.getLong("userid");  
                    Long categoryid = resultSet.getLong("categoryid");         
                    String name = resultSet.getString("name");
                    String firstname = resultSet.getString("firstname");                    
                    String nickname = resultSet.getString("nickname");
                    String categoryname = resultSet.getString("categoryname");

                    experts.add(new Expert(userid, categoryid, name, firstname, nickname, categoryname));

               }

               return experts;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
   

 /*       
    @Override
    public User save(User user) 
    {
        try 
        {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO "
                + "SkillHubDB.user (name, firstname, nickname, role, mailadress, password)"
                + "VALUES (?,?,?,?,?,?)"

            );

            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getMailAdress());
            statement.setString(6, user.getPassWord());



            if (statement.executeUpdate() != 1) 
            {
                throw new SQLException("failed to insert data");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) 
            {
                Long userid = generatedKeys.getLong(1);
                user.setUserId(userid);
                return user;
            } else 
            {
                throw new SQLException("failed to get inserted userid");
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(Long userid) {

        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM SkillHubDB.user WHERE userid = ?"
            );
            statement.setLong(1, userid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String firstname = resultSet.getString("firstname");
                String nickname = resultSet.getString("nickname");
                String role = resultSet.getString("role");
                String mailadress = resultSet.getString("mailadress");
                String password = resultSet.getString("password");

                return new User(userid, name, firstname, nickname, role, mailadress, password);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    @Override
    public User update(User user) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(

                    "UPDATE SkillHubDB.user SET name=?, firstname=?, nickname=?, role=?, mailadress=?, password=? WHERE userid=?"

            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getMailAdress());
            statement.setString(6, user.getPassWord());
            statement.setLong(7, user.getUserId());


            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long userid) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM SkillHubDB.user WHERE userid=?"
            );
            statement.setLong(1, userid);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 */

}

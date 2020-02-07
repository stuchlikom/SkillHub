package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.User;


import java.sql.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements CrudDao<User> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";
    String role;

        @Override
        public List<User> findAll(Long filter) {

            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;  
    
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                statement = connection.prepareStatement("SELECT * FROM SkillHubDB.user");
                resultSet = statement.executeQuery();
    
                List<User> users = new ArrayList<>();

                while (resultSet.next()) {
                    Long userid = resultSet.getLong("userid");           
                    String name = resultSet.getString("name");                    
                    String firstname = resultSet.getString("firstname");                    
                    String nickname = resultSet.getString("nickname");                    
<<<<<<< HEAD
                    Blob avatar = resultSet.getBlob("avatar");
                    String role = resultSet.getString("role");                    
                    String mailadress = resultSet.getString("mailadress");                    
                    String password = resultSet.getString("password");                    

                    users.add(new User(userid, name, firstname, nickname, avatar, role, mailadress, password));

=======
                    String avatar = resultSet.getString("avatar");                    
                    boolean expert = resultSet.getBoolean("expert");                    
                    String role = resultSet.getString("role");                    
                    String mailadress = resultSet.getString("mailadress");                    
                    String password = resultSet.getString("password");                    
                    int category = resultSet.getInt("category");

                    users.add(new User(userid, name, firstname, nickname, avatar, expert, role, mailadress, password, category));
/*
>>>>>>> dev
                    System.out.print(">|" + resultSet.getLong("userid") + "|" + userid + "|");
                    System.out.print(">|" + resultSet.getString("name") + "|" + name + "|");
                    System.out.print(">|" + resultSet.getString("firstname") + "|" + firstname + "|");
                    System.out.print(">|" + resultSet.getString("nickname") + "|" + nickname + "|");
                    System.out.print(">|" + resultSet.getBlob("avatar") + "|" + avatar + "|");
                    System.out.print(">|" + resultSet.getString("role") + "|" + role + "|");
                    System.out.print(">|" + resultSet.getString("mailadress") + "|" + mailadress + "|");
                    System.out.print(">|" + resultSet.getString("password") + "|" + password + "|");
                    System.out.println("end while>|"+userid+"|");
                   
                }
                
                return users;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        
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
<<<<<<< HEAD
                + "SkillHubDB.user (name, firstname, nickname, avatar, role, mailadress, password)"
                + "VALUES (?,?,?,?,?,?,?,?)"
=======
                + "SkillHubDB.user (name, firstname, nickname, avatar, expert, role, mailadress, password, category)"
                + "VALUES (?,?,?,?,?,?,?,?,?)"
>>>>>>> dev
            );

            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
<<<<<<< HEAD
            statement.setBlob(4, user.getAvatar());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getMailAdress());
            statement.setString(7, user.getPassWord());

=======
            statement.setString(4, user.getAvatar());
            statement.setBoolean(5, user.isExpert());
            statement.setString(6, user.getRole());
            statement.setString(7, user.getMailAdress());
            statement.setString(8, user.getPassWord());
            statement.setInt(9, user.getCategory());
>>>>>>> dev

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
<<<<<<< HEAD
                Blob avatar = resultSet.getBlob("avatar");
=======
                String avatar = resultSet.getString("avatar");
                boolean expert = resultSet.getBoolean("expert");
>>>>>>> dev
                String role = resultSet.getString("role");
                String mailadress = resultSet.getString("mailadress");
                String password = resultSet.getString("password");

<<<<<<< HEAD
                return new User(userid, name, firstname, nickname, avatar, role, mailadress, password);
=======
                return new User(userid, name, firstname, nickname, avatar, expert, role, mailadress, password, category);
>>>>>>> dev
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
<<<<<<< HEAD
                    "UPDATE SkillHubDB.user SET name=?, firstname=?, nickname=?, avatar=?, role=?, mailadress=?, password=? WHERE userid=?"
=======
                    "UPDATE SkillHubDB.user SET name=?, firstname=?, nickname=?, avatar=?, expert=?, role=?, mailadress=?, password=?, category=? WHERE userid=?"
>>>>>>> dev
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
<<<<<<< HEAD
            statement.setBlob(4, user.getAvatar());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getMailAdress());
            statement.setString(7, user.getPassWord());
            statement.setLong(9, user.getUserId());
=======
            statement.setString(4, user.getAvatar());
            statement.setBoolean(5, user.isExpert());
            statement.setString(6, user.getRole());
            statement.setString(7, user.getMailAdress());
            statement.setString(8, user.getPassWord());
            statement.setInt(9, user.getCategory());
            statement.setLong(10, user.getUserId());
>>>>>>> dev

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
}

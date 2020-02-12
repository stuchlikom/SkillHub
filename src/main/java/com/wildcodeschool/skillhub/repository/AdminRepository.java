package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements CrudDao<User> {

    private final static String DB_URL = "jdbc:mariadb://db02eylw.mariadb.hosting.zone";
    private final static String DB_USER = "db02eylw_aevsybn";
    private final static String DB_PASSWORD = "3GQMpC*X";
  
    
        @Override
        public List<User> findAll(Long filter) {

            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;  
    
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                statement = connection.prepareStatement("SELECT * FROM db02eylw.user");
                resultSet = statement.executeQuery();
    
                List<User> users = new ArrayList<>();

                while (resultSet.next()) {
                    Long userid = resultSet.getLong("userid");           
                    String name = resultSet.getString("name");                    
                    String firstname = resultSet.getString("firstname");                    
                    String nickname = resultSet.getString("nickname");
                    String role = resultSet.getString("role");                    
                    String mailadress = resultSet.getString("mailadress");                    
                    String password = resultSet.getString("password");                    

                    users.add(new User(userid, name, firstname, nickname, role, mailadress, password));

/*
                    System.out.print(">|" + resultSet.getLong("userid") + "|" + userid + "|");
                    System.out.print(">|" + resultSet.getString("name") + "|" + name + "|");
                    System.out.print(">|" + resultSet.getString("firstname") + "|" + firstname + "|");
                    System.out.print(">|" + resultSet.getString("nickname") + "|" + nickname + "|");
                    System.out.print(">|" + resultSet.getString("role") + "|" + role + "|");
                    System.out.print(">|" + resultSet.getString("mailadress") + "|" + mailadress + "|");
                    System.out.print(">|" + resultSet.getString("password") + "|" + password + "|");
                    System.out.println("end while mit user "+userid+"|");
*/          
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
                + "db02eylw.user (name, firstname, nickname, role, mailadress, password)"
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
                    "SELECT * FROM db02eylw.user WHERE userid = ?"
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

                    "UPDATE db02eylw.user SET name=?, firstname=?, nickname=?, role=?, mailadress=?, password=? WHERE userid=?"

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
                    "DELETE FROM db02eylw.user WHERE userid=?"
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
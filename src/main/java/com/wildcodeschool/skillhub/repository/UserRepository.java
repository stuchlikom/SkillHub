package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CrudDao<User> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

    /*  DB-Dump vom 30.01.2020
    CREATE TABLE `user` (
    `userid` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) DEFAULT NULL,
    `firstname` varchar(45) DEFAULT NULL,
    `nickname` varchar(45) DEFAU
        System.out.print(">|" + resultSet.getLong("userid") + "|" + userid + "|");
        System.out.print(">|" + resultSet.getString("name") + "|" + name + "|");
        System.out.print(">|" + resultSet.getString("firstname") + "|" + firstname + "|");
        System.out.print(">|" + resultSet.getString("nickname") + "|" + nickname + "|");
        System.out.print(">|" + resultSet.getString("avatar") + "|" + avatar + "|");
        System.out.print(">|" + resultSet.getBoolean("expert") + "|" + expert + "|");
        System.out.print(">|" + resultSet.getString("mailadress") + "|" + mailadress + "|");
        System.out.print(">|" + resultSet.getString("password") + "|" + password + "|");
        System.out.print(">|" + resultSet.getInt("category") + "|" + category + "|");

NULL,
    `mailadress` varchar(45) DEFAULT NULL,
    `password` varchar(45) DEFAULT NULL,
    `category` int(11) NOT NULL,
    PRIMARY KEY (`userid`),
    KEY `fk_user_1_idx` (`category`),
    CONSTRAINT `fk_user_category-id` FOREIGN KEY (`category`) REFERENCES `category` (`categoryid`) ON DELETE NO ACTION ON UPDATE NO ACTION
    )   

INSERT INTO user VALUES
 (12,'test12','test12','test12','',1,1,'','test12',1)
,(13,'test13','test13','test13','',1,1,'','test13',2)
,(14,'test14','test14','test14','',1,1,'','test14',3)
,(15,'test15','test15','test15','',1,1,'','test15',4)
,(16,'test16','test16','test16','',1,1,'','test16',5)
,(17,'test17','test17','test17','',1,1,'','test17',6)
,(18,'test18','test18','test18','',1,1,'','test18',7);


        */

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
                    String avatar = resultSet.getString("avatar");                    
                    boolean expert = resultSet.getBoolean("expert");                    
                    boolean admin = resultSet.getBoolean("admin");                    
                    String mailadress = resultSet.getString("mailadress");                    
                    String password = resultSet.getString("password");                    
                    int category = resultSet.getInt("category");
                    users.add(new User(userid, name, firstname, nickname, avatar, expert, admin, mailadress, password, category));
/*
                    System.out.print(">|" + resultSet.getLong("userid") + "|" + userid + "|");
                    System.out.print(">|" + resultSet.getString("name") + "|" + name + "|");
                    System.out.print(">|" + resultSet.getString("firstname") + "|" + firstname + "|");
                    System.out.print(">|" + resultSet.getString("nickname") + "|" + nickname + "|");
                    System.out.print(">|" + resultSet.getString("avatar") + "|" + avatar + "|");
                    System.out.print(">|" + resultSet.getBoolean("expert") + "|" + expert + "|");
                    System.out.print(">|" + resultSet.getString("mailadress") + "|" + mailadress + "|");
                    System.out.print(">|" + resultSet.getString("password") + "|" + password + "|");
                    System.out.print(">|" + resultSet.getInt("category") + "|" + category + "|");
                    System.out.println("end while>|"+userid+"|");
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
                + "SkillHubDB.user (name, firstname, nickname, avatar, expert, admin, mailadress, password, category)"
                + "VALUES (?,?,?,?,?,?,?,?,?)"
            );

            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getAvatar());
            statement.setBoolean(5, user.isExpert());
            statement.setBoolean(6, user.isAdmin());
            statement.setString(7, user.getMailAdress());
            statement.setString(8, user.getPassWord());
            statement.setInt(9, user.getCategory());

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
                String avatar = resultSet.getString("avatar");
                boolean expert = resultSet.getBoolean("expert");
                boolean admin = resultSet.getBoolean("admin");
                String mailadress = resultSet.getString("mailadress");
                String password = resultSet.getString("password");
                int category = resultSet.getInt("category");

                return new User(userid, name, firstname, nickname, avatar, expert, admin, mailadress, password, category);
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
                    "UPDATE SkillHubDB.user SET name=?, firstname=?, nickname=?, avatar=?, expert=?, admin=?, mailadress=?, password=?, category=? WHERE userid=?"
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getAvatar());
            statement.setBoolean(5, user.isExpert());
            statement.setBoolean(6, user.isAdmin());
            statement.setString(7, user.getMailAdress());
            statement.setString(8, user.getPassWord());
            statement.setInt(9, user.getCategory());
            statement.setLong(10, user.getUserId());

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

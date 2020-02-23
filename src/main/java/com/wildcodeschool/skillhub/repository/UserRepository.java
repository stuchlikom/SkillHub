package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Avatar;
import com.wildcodeschool.skillhub.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

public class UserRepository implements CrudDao<User> {

    private final static String DB_URL = "jdbc:mariadb://db02eylw.mariadb.hosting.zone";
    private final static String DB_USER = "db02eylw_aevsybn";
    private final static String DB_PASSWORD = "3GQMpC*X";
    String role;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    AvatarRepository avatarRepository = new AvatarRepository();
    		
    String hashedPassword;

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
                }
                
                return users;
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
    public User save(User user) 
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try 
        {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                "INSERT INTO "
                + "db02eylw.user (name, firstname, nickname, role, mailadress, password)"
                + "VALUES (?,?,?,?,?,?);"
         
                , Statement.RETURN_GENERATED_KEYS
            );

            System.out.println("Name: " + user.getFirstName());
            System.out.println("Nachname: " + user.getName());
            System.out.println("Mail: " + user.getMailAdress());
            System.out.println("Password: " + user.getPassWord());

            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getMailAdress());
            hashedPassword = passwordEncoder.encode(user.getPassWord());
            statement.setString(6, hashedPassword);

            if (statement.executeUpdate() != 1) 
            {
                throw new SQLException("failed to insert data");
            }

            generatedKeys = statement.getGeneratedKeys();
 
            /////////////
//            PreparedStatement avatarstatement = connection.prepareStatement(
//                    "INSERT INTO db02eylw.avatar (avatarid, avatar) VALUES (?, ?)"
//            );
//            User neuUser = findByNick(user.getNickName());
//            avatarstatement.setLong(1, neuUser.getUserId());
//            avatarstatement.setBytes(2, getResourceAsStream("/defaultavatar.jpg")
//            
//            avatarstatement.executeUpdate();
            ////////// 
            
            Avatar neuAvatar = new Avatar();
            User avatarUser = findByNick(user.getNickName());
            neuAvatar.setAvatarId(avatarUser.getUserId());
            avatarRepository.save(neuAvatar);
            
            if (generatedKeys.next()) 
            {
                Long userid = generatedKeys.getLong(1);
                user.setUserId(userid);
                return user;
            } else 
            {
                throw new SQLException("failed to get inserted userid");
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
    public User update(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "UPDATE db02eylw.user SET name=?, firstname=?, nickname=?, role=?, mailadress=?, password=? WHERE userid=?"
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getNickName());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getMailAdress());
            hashedPassword = passwordEncoder.encode(user.getPassWord());
            statement.setString(6, hashedPassword);
            statement.setLong(7, user.getUserId());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public User findById(Long userid) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.user WHERE userid = ?"
            );
            statement.setLong(1, userid);
            resultSet = statement.executeQuery();

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
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

//    @Override
    public User findByNick(String nick) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;  
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.user WHERE nickname = ?"
            );
            statement.setString(1, nick);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long userid = resultSet.getLong("userid");
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
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }    

    public User findByMail(String mail) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;  
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.user WHERE mailadress = ?"
            );
            statement.setString(1, mail);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long userid = resultSet.getLong("userid");
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
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }    

    @Override
    public void deleteById(Long userid) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "DELETE FROM db02eylw.user WHERE userid=?"
            );
            statement.setLong(1, userid);

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

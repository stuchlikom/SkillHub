package com.wildcodeschool.skillhub.repository;


import com.wildcodeschool.skillhub.entity.Avatar;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class AvatarRepository implements CrudDao<Avatar> {

    private final static String DB_URL = "jdbc:mariadb://db02eylw.mariadb.hosting.zone";
    private final static String DB_USER = "db02eylw_aevsybn";
    private final static String DB_PASSWORD = "3GQMpC*X";

    @Override
    public Avatar update(Avatar avatar) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {        	
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
/////
            		"SELECT * from db02eylw.avatar WHERE avatarid=?",
            		Statement.RETURN_GENERATED_KEYS);	
            statement.setLong(1, avatar.getAvatarId());
            
            if (statement.execute("Empty set")) {
            	save(avatar);
            } else {
                    
            statement = connection.prepareStatement(
            		"UPDATE db02eylw.avatar SET avatar=? WHERE avatarid=?", 
                    Statement.RETURN_GENERATED_KEYS					
                    );
                    ///
//                    "INSERT INTO db02eylw.avatar (avatar) VALUES (?)",
//                    Statement.RETURN_GENERATED_KEYS
/////////////////

            statement.setBytes(1, avatar.getAvatar());
            statement.setLong(2, avatar.getAvatarId());
            System.out.println("ich war im Avatarrepo");
            } 
           if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to insert data");
            }

            generatedKeys = statement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(generatedKeys);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return avatar;
    }
    
    
    
    
    
    
//    @Override
//    public Avatar save(Avatar avatar) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet generatedKeys = null;
//        try {        	
//            connection = DriverManager.getConnection(
//                    DB_URL, DB_USER, DB_PASSWORD
//            );
//            statement = connection.prepareStatement(
//            		"UPDATE avatar SET avatar=? WHERE avatarid=?", // "INSERT INTO avatar (avatar) VALUES (?) WHERE avatarId = 1",
//                    Statement.RETURN_GENERATED_KEYS					// UPDATE avatar SET avatar=? WHERE avatarid=1;
//            );
//            statement.setBytes(1, avatar.getAvatar());
//            statement.setLong(2, avatar.getAvatarId());
//            System.out.println("ich war im Avatarrepo");
// 
//           if (statement.executeUpdate() != 1) {
//                throw new SQLException("failed to insert data");
//            }
//
//            generatedKeys = statement.getGeneratedKeys();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.closeResultSet(generatedKeys);
//            JdbcUtils.closeStatement(statement);
//            JdbcUtils.closeConnection(connection);
//        }
//        return avatar;
//    }

    @Override
    public Avatar findById(Long avatarId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;  
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.avatar WHERE avatarid = ?;"
            );
            statement.setLong(1, avatarId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	//avatarId = resultSet.getLong("avatarId");
                byte[] avatar = resultSet.getBytes("avatar");
 
                return new Avatar(avatarId, avatar);
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
    public List<Avatar> findAll(Long avatarId) { 
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;  
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM db02eylw.avatar;"
            );
            resultSet = statement.executeQuery();

            List<Avatar> avatars = new ArrayList<>();

            while (resultSet.next()) {
                avatarId = resultSet.getLong("avatarId");
                byte[] avatar = resultSet.getBytes("avatar");
                avatars.add(new Avatar(avatarId, avatar));
            }
            return avatars;
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
//<<<<< HEAD
    public Avatar save (Avatar avatar) {
//=====
//  public Avatar update (Avatar avatar) {
      Connection connection = null;
      PreparedStatement statement = null;
//>>>>> ac34a46e108885074830eb58cdca31dc295ea498
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "INSERT INTO db02eylw.avatar (avatar) VALUES (?)"
            );
            statement.setBytes(1, avatar.getAvatar());
//            statement.setLong(2, avatar.getAvatarId());
            
            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return avatar;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void deleteById(Long avatarId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "DELETE FROM avatar WHERE avatarId=?"
            );
            statement.setLong(1, avatarId);

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

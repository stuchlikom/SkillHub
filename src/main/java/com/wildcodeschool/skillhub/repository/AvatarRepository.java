package com.wildcodeschool.skillhub.repository;


import com.wildcodeschool.skillhub.entity.Avatar;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wildcodeschool.skillhub.util.JdbcUtils;

@Repository
public class AvatarRepository implements CrudDao<Avatar> {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/SkillHubDB";
    private final static String DB_USER = "sh_admin";
    private final static String DB_PASSWORD = "sPfdA-1234";

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
            		"SELECT * from avatar WHERE avatarid=?",
            		Statement.RETURN_GENERATED_KEYS);	
            statement.setLong(1, avatar.getAvatarId());
            
            if (statement.execute("Empty set")) {
            	save(avatar);
            } else {
                    
            statement = connection.prepareStatement(
            		"UPDATE avatar SET avatar=? WHERE avatarid=?", 
                    Statement.RETURN_GENERATED_KEYS					
            );
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
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM avatar WHERE avatarid = ?;"
            );
            statement.setLong(1, avatarId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	//avatarId = resultSet.getLong("avatarId");
                byte[] avatar = resultSet.getBytes("avatar");
 
                return new Avatar(avatarId, avatar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





    @Override
    public List<Avatar> findAll(Long avatarId) { 
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM avatar;"
            );
            ResultSet resultSet = statement.executeQuery();

            List<Avatar> avatars = new ArrayList<>();

            while (resultSet.next()) {
                avatarId = resultSet.getLong("avatarId");
                byte[] avatar = resultSet.getBytes("avatar");
                avatars.add(new Avatar(avatarId, avatar));
            }
            return avatars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Avatar save (Avatar avatar) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE avatar SET avatar=? WHERE avatarId=?"
            );
            statement.setBytes(1, avatar.getAvatar());
            statement.setLong(2, avatar.getAvatarId());
            
            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to update data");
            }
            return avatar;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long avatarId) {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM avatar WHERE avatarId=?"
            );
            statement.setLong(1, avatarId);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("failed to delete data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

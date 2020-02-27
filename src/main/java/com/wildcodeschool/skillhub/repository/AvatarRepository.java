package com.wildcodeschool.skillhub.repository;

import com.wildcodeschool.skillhub.entity.Avatar;
import com.wildcodeschool.skillhub.repository.CrudDao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

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
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"SELECT * from db02eylw.avatar WHERE avatarid=?");
			statement.setLong(1, avatar.getAvatarId());

			if (statement.executeQuery().next()) {
				statement.close();
				statement = connection.prepareStatement("UPDATE db02eylw.avatar SET avatar=? WHERE avatarid=?");
				statement.setBytes(1, avatar.getAvatar());
				statement.setLong(2, avatar.getAvatarId());
				System.out.println("ich war im Avatarrepo");

				if (statement.executeUpdate() != 1) {
					throw new SQLException("failed to insert data");
				}
			} else {
				save(avatar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeResultSet(generatedKeys);
			JdbcUtils.closeStatement(statement);
			JdbcUtils.closeConnection(connection);
		}
		return avatar;
	}

	@Override
	public Avatar findById(Long avatarId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("SELECT * FROM db02eylw.avatar WHERE avatarid = ?;");
			statement.setLong(1, avatarId);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
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
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("SELECT * FROM db02eylw.avatar;");
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
	public Avatar save(Avatar avatar) {

		byte[] dummyAvatarAsByteArray = null;
		InputStream imageAsStream = this.getClass().getResourceAsStream("/static/dummy-avatar.jpg");
		try	{
			dummyAvatarAsByteArray = IOUtils.toByteArray(imageAsStream);
		} catch (IOException e) {
			System.out.println("Dummy-Bild nicht gefunden");
		}
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement(
					"INSERT INTO db02eylw.avatar (avatarid, avatar) VALUES (?, ?)"
			);
			statement.setLong(1, avatar.getAvatarId());
			statement.setBytes(2, dummyAvatarAsByteArray);

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
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("DELETE FROM db02eylw.avatar WHERE avatarId=?");
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

package com.openclassrooms.PayMyBuddy.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.openclassrooms.PayMyBuddy.constant.Constants;
import com.openclassrooms.PayMyBuddy.constant.DatabaseConfig;
import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public class UserRepository {

	public DatabaseConfig databaseConfig;

	public UserRepository(DatabaseConfig config) {
		databaseConfig = config;
	}

	public Result saveUser(User user) {
		Connection connection = null;
		try {
			connection = databaseConfig.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(Constants.SaveUser);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFirstName());
			preparedStatement.setString(4, user.getLastName());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.execute();
			return Result.success;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Result.failure;
	}

	public User getUser(String email) {
		Connection connection = null;
		User user = null;
		try {
			connection = databaseConfig.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(Constants.GetUserByEmail);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setEmail(email);
				user.setId(resultSet.getInt(1));
				user.setFirstName(resultSet.getString(3));
				user.setLastName(resultSet.getString(4));
			}
			databaseConfig.closeResultSet(resultSet);
			databaseConfig.closePreparedStatement(preparedStatement);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return user;
	}

	public User getUserById(int userId) {
		Connection connection = null;
		User user = null;
		try {
			connection = databaseConfig.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(Constants.GetUserById);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setEmail(resultSet.getString(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
			}
			databaseConfig.closeResultSet(resultSet);
			databaseConfig.closePreparedStatement(preparedStatement);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return user;
	}
}

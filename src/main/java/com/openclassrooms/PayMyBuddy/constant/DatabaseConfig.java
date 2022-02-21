package com.openclassrooms.PayMyBuddy.constant;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
	private Logger logger = LogManager.getLogger("DataBaseConfig");
	private Connection connection;

	public Connection connect() {
		if (connection == null) {
			try {
				Properties properties = new Properties();
				FileInputStream inputStream = new FileInputStream("src/main/resources/application.properties");
				properties.load(inputStream);
				inputStream.close();
				String databaseDriver = properties.getProperty("jdbc.driver");
				Class.forName(databaseDriver);
				String databaseUrl = properties.getProperty("jdbc.url");
				String username = properties.getProperty("jdbc.username");
				String password = properties.getProperty("jdbc.password");
				connection = DriverManager.getConnection(databaseUrl, username, password);
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public void closePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
				logger.info("Closing Prepared Statement");
			} catch (SQLException e) {
				logger.error("Error while closing prepared statement", e);
			}
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
				logger.info("Closing Result Set");
			} catch (SQLException e) {
				logger.error("Error while closing result set", e);
			}
		}
	}
}

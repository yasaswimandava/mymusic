package com.mindtree.music.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicJdbcConnectionUtility {
	private static final String URL = "jdbc:mysql://localhost:3306/Musicsystem";
	private static final String username = "root";
	private static final String password = "Welcome123";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, username, password);
		} catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
		}
		return connection;
	}

	public void closeResource(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}
	}

	public void closeResource(ResultSet rst) {
		if (rst != null) {
			try {
				rst.close();
			} catch (SQLException e) {
			}
		}
	}

	public void closeResource(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {

			}
		}
	}
}

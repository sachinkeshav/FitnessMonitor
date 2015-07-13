package org.mustard.fitnessmonitor.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class JdbcMainTest {
	private Connection connection = null;
	private Statement statement = null;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/db_mustard_fitness";

	// Database credentials
	static final String USER = "root";
	static final String PASSASSWORD = "root";

	public JdbcMainTest() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASSASSWORD);
			statement = connection.createStatement();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException se2) {

		}
		try {
			if (connection != null)
				System.out.println("Closing onnection");
			connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public Map<String, Map<String, Double>> fetchData() throws SQLException {
		String sql = "SELECT activity_date, weight, running_cal, swimming_cal, walking_cal, cycling_cal, aerobics_cal, total_cal FROM tbl_activity";
		ResultSet resultSet = statement.executeQuery(sql);

		Map<String, Map<String, Double>> resultMap = new HashMap<>();

		while (resultSet.next()) {
			String dateTime = "" + resultSet.getDate("activity_date");
			if (!resultMap.containsKey(dateTime)) {
				Map<String, Double> activityMap = new HashMap<>();
				activityMap.put("weight", resultSet.getDouble("weight"));
				activityMap.put("running", resultSet.getDouble("running_cal"));
				activityMap.put("swimming", resultSet.getDouble("swimming_cal"));
				activityMap.put("walking", resultSet.getDouble("walking_cal"));
				activityMap.put("cycling", resultSet.getDouble("cycling_cal"));
				activityMap.put("aerobics", resultSet.getDouble("aerobics_cal"));
				activityMap.put("total", resultSet.getDouble("total_cal"));
				resultMap.put(dateTime, activityMap);
			}
		}
		this.close();
		return resultMap;
	}
}

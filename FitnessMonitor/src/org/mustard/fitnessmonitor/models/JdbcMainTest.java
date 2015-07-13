package org.mustard.fitnessmonitor.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	public Map<String, List<Object>> fetchData() throws SQLException {
		String sql = "SELECT activity_date, weight, running_cal, swimming_cal, walking_cal, cycling_cal, aerobics_cal, total_cal FROM tbl_activity";
		ResultSet resultSet = statement.executeQuery(sql);

		Map<String, List<Object>> resultMap = new HashMap<>();

		while (resultSet.next()) {
			String date = "" + resultSet.getDate("activity_date");
			double weight = resultSet.getDouble("weight");
			double running = resultSet.getDouble("running_cal");
			double swimming = resultSet.getDouble("swimming_cal");
			double walking = resultSet.getDouble("walking_cal");
			double cycling = resultSet.getDouble("cycling_cal");
			double aerobics = resultSet.getDouble("aerobics_cal");
			double total = resultSet.getDouble("total_cal");
			
			populateResultMap(resultMap, date, "date");
			populateResultMap(resultMap, weight, "weight");
			populateResultMap(resultMap, running, "running");
			populateResultMap(resultMap, swimming, "swimming");
			populateResultMap(resultMap, walking, "walking");
			populateResultMap(resultMap, cycling, "cycling");
			populateResultMap(resultMap, aerobics, "aerobics");
			populateResultMap(resultMap, total, "total");
		}
		
		this.close();
		return resultMap;
	}

	private void populateResultMap(Map<String, List<Object>> resultMap, Object measure, String measureKey) {
		if(!resultMap.containsKey(measureKey)){
			List<Object> measureList = new ArrayList<>();
			measureList.add(measure);
			resultMap.put(measureKey, measureList);
		}else{
			resultMap.get(measureKey).add(measure);
		}
	}
}

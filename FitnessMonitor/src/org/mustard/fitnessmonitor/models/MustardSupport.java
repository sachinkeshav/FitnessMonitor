package org.mustard.fitnessmonitor.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MustardSupport {
	public void showData() throws SQLException {
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		String sql;
		sql = "SELECT date, weight, calories FROM tbl_fitness";
		ResultSet resultSet = stmt.executeQuery(sql);
		while (resultSet.next()) {
			String dateTime = "" + resultSet.getDate("date");
			Double weight = resultSet.getDouble("weight");
			Double calories = resultSet.getDouble("calories");
			System.out.println(dateTime + "\n" + weight + "\n" + calories + "\n");
		}
		jdbcMain.close();
	}

	public  void storeData(double totalCalories) throws SQLException {
		double test = totalCalories;
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		// Date date = new Date().getTime();
		String sql = "INSERT INTO tbl_fitness(date,weight,calories) " + "VALUES ('" + currentTime + "',23,'" + test
				+ "')";
		stmt.executeUpdate(sql);
		jdbcMain.close();
	}
}

package org.mustard.fitnessmonitor.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MustardSupport {
	double runningDb;
	double walkingDb;
	double cyclingDb;
	double swimmingDb;
	double yogaDb;
	String dateDb;

	public Map<String, List<Object>> fetchAllData() throws SQLException {
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		String sql;
		sql = "SELECT * FROM fitness ORDER BY date  DESC LIMIT 7";
		ResultSet resultSet = stmt.executeQuery(sql);

		Map<String, List<Object>> resultMap = new HashMap<>();

		while (resultSet.next()) {
			String dateTime = "" + resultSet.getDate("date");
			double weight = resultSet.getDouble("weight");
			double running = resultSet.getDouble("running");
			double walking = resultSet.getDouble("walking");
			double cycling = resultSet.getDouble("cycling");
			double swimming = resultSet.getDouble("swimming");
			double yoga = resultSet.getDouble("yoga");
			double total = resultSet.getDouble("totalCalories");

			populateResultMap(resultMap, dateTime, "date");
			populateResultMap(resultMap, weight, "weight");
			populateResultMap(resultMap, running, "running");
			populateResultMap(resultMap, walking, "walking");
			populateResultMap(resultMap, cycling, "cycling");
			populateResultMap(resultMap, swimming, "swimming");
			populateResultMap(resultMap, yoga, "yoga");
			populateResultMap(resultMap, total, "total");

		}
		jdbcMain.close();
		return resultMap;
	}

	private void populateResultMap(Map<String, List<Object>> resultMap, Object itemValue, String item) {
		if (!resultMap.containsKey(item)) {
			List<Object> itemValueList = new ArrayList<>();
			itemValueList.add(itemValue);
			resultMap.put(item, itemValueList);
		} else {
			resultMap.get(item).add(itemValue);
		}
	}

	public double fetchWeight() throws SQLException {
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		String sql = "SELECT weight FROM Profile";
		ResultSet resultSet = stmt.executeQuery(sql);
		return resultSet.getDouble("weight");
	}

	public HashMap<String, Double> fetchData(String currentDate) throws SQLException {
		HashMap<String, Double> prevoiusValueMap = new HashMap<String, Double>();
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		String sql = "SELECT * FROM fitness where date = '" + currentDate + "'";
		ResultSet resultSet = stmt.executeQuery(sql);
		if (resultSet.next()) {
			prevoiusValueMap.put("id", new Double(resultSet.getInt("id")));
			prevoiusValueMap.put("running", resultSet.getDouble("running"));
			prevoiusValueMap.put("walking", resultSet.getDouble("walking"));
			prevoiusValueMap.put("cycling", resultSet.getDouble("cycling"));
			prevoiusValueMap.put("swimming", resultSet.getDouble("swimming"));
			prevoiusValueMap.put("yoga", resultSet.getDouble("yoga"));
		}
		System.out.println(prevoiusValueMap);
		return prevoiusValueMap;
	}

	public void storeData(HashMap<String, Double> map) throws SQLException {
		double totalCaloriesBurnt = 0;
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		HashMap<String, Double> previousMap = new MustardSupport().fetchData(currentTime);
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		if (!previousMap.isEmpty()) {
			runningDb = previousMap.get("running") + map.get("running");
			walkingDb = previousMap.get("walking") + map.get("walking");
			cyclingDb = previousMap.get("cycling") + map.get("cycling");
			swimmingDb = previousMap.get("swimming") + map.get("swimming");
			yogaDb = previousMap.get("yoga") + map.get("yoga");
			totalCaloriesBurnt = runningDb + walkingDb + cyclingDb + swimmingDb + yogaDb;
			String updateQuery = " UPDATE fitness set id='" + previousMap.get("id").intValue() + "',weight='"
					+ Physical.weight + "',running='" + runningDb + "'," + "walking='" + walkingDb + "',cycling='"
					+ cyclingDb + "'" + ",swimming='" + swimmingDb + "',yoga='" + yogaDb + "',totalCalories='"
					+ totalCaloriesBurnt + "' where date='" + currentTime + "'";

			System.out.println(updateQuery);
			stmt.executeUpdate(updateQuery);
		} else {
			for (Double caloriesBurnt : map.values()) {
				totalCaloriesBurnt += caloriesBurnt;
			}
			System.out.println("total calories Burnt" + totalCaloriesBurnt);
			String insertQuery = "INSERT INTO fitness(weight,running,walking,cycling,swimming,yoga,date,totalCalories) "
					+ "VALUES ('" + Physical.weight + "','" + map.get("running") + "','" + map.get("walking") + "','"
					+ map.get("cycling") + "','" + map.get("swimming") + "','" + map.get("yoga") + "','" + currentTime
					+ "','" + totalCaloriesBurnt + "')";
			System.out.println(insertQuery);
			stmt.executeUpdate(insertQuery); // add for each new Days
		}
		jdbcMain.close();
	}
}

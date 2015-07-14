package org.mustard.fitnessmonitor.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MustardSupport {

	/**
	 * perform mapping of all indivdual type of calories burnt over last seven
	 * days in a list and stored in map
	 * 
	 * @return map containing list
	 * @throws SQLException
	 */
	public Map<String, List<Object>> fetchAllData() throws SQLException {
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		String sql;
		sql = "SELECT * FROM (SELECT * FROM table_fitness ORDER BY date DESC LIMIT 7) var ORDER BY date";
		ResultSet resultSet = stmt.executeQuery(sql);

		Map<String, List<Object>> resultMap = new HashMap<>();

		while (resultSet.next()) {
			String dateTime = "" + resultSet.getDate("date");
			double weight = resultSet.getDouble("weight");
			double running = resultSet.getDouble("treadMill");
			double walking = resultSet.getDouble("dumbBells");
			double cycling = resultSet.getDouble("cycling");
			double swimming = resultSet.getDouble("pullUp");
			double yoga = resultSet.getDouble("benchPress");
			double total = resultSet.getDouble("totalCalories");

			populateResultMap(resultMap, dateTime, "date");
			populateResultMap(resultMap, weight, "weight");
			populateResultMap(resultMap, running, "treadMill");
			populateResultMap(resultMap, walking, "dumbBells");
			populateResultMap(resultMap, cycling, "cycling");
			populateResultMap(resultMap, swimming, "pullUp");
			populateResultMap(resultMap, yoga, "benchPress");
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

	/**
	 * retreives weight from profile table
	 * 
	 * @return weight
	 * @throws SQLException
	 */
	public double fetchWeight() throws SQLException {
		double weight = 0;
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();
		String sql = "SELECT * FROM profile";
		ResultSet resultSet = stmt.executeQuery(sql);
		while (resultSet.next()) {
			weight = resultSet.getDouble("Weight");
		}
		jdbcMain.close();
		return weight;
	}

	/**
	 * 
	 * @param currentDate
	 * @return all the caloriesburnt placed individually in map
	 * @throws SQLException
	 */
	public HashMap<String, Double> fetchData(String currentDate) throws SQLException {
		HashMap<String, Double> prevoiusValueMap = new HashMap<String, Double>();
		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();

		String sql = "SELECT * FROM table_fitness where date = '" + currentDate + "'";

		ResultSet resultSet = stmt.executeQuery(sql);
		if (resultSet.next()) {
			prevoiusValueMap.put("id", new Double(resultSet.getInt("id")));
			prevoiusValueMap.put("treadMill", resultSet.getDouble("treadMill"));
			prevoiusValueMap.put("dumbBells", resultSet.getDouble("dumbBells"));
			prevoiusValueMap.put("cycling", resultSet.getDouble("cycling"));
			prevoiusValueMap.put("pullUp", resultSet.getDouble("pullUp"));
			prevoiusValueMap.put("benchPress", resultSet.getDouble("benchPress"));
		}
		return prevoiusValueMap;
	}

	/**
	 * performs sql data insertion and update
	 * 
	 * @param map
	 *            with activity and caloriesBurnt
	 * @throws SQLException
	 */
	public void storeData(HashMap<String, Double> map) throws SQLException {
		double totalCaloriesBurnt = 0;
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);

		HashMap<String, Double> previousMap = new MustardSupport().fetchData(currentTime);

		JdbcMain jdbcMain = new JdbcMain();
		Statement stmt = jdbcMain.connection();

		if (!previousMap.isEmpty()) {
			double treadMillDb = previousMap.get("treadMill") + map.get("treadMill");
			double dumbBellsDb = previousMap.get("dumbBells") + map.get("dumbBells");
			double cyclingDb = previousMap.get("cycling") + map.get("cycling");
			double pullUpDb = previousMap.get("pullUp") + map.get("pullUp");
			double benchPressDb = previousMap.get("benchPress") + map.get("benchPress");
			totalCaloriesBurnt = treadMillDb + dumbBellsDb + cyclingDb + pullUpDb + benchPressDb;

			String updateQuery = " UPDATE table_fitness set id='" + previousMap.get("id").intValue() + "',weight='"
					+ Physical.weight + "',treadMill='" + treadMillDb + "'," + "dumbBells='" + dumbBellsDb
					+ "',cycling='" + cyclingDb + "'" + ",pullUP='" + pullUpDb + "',benchPress='" + benchPressDb
					+ "',totalCalories='" + totalCaloriesBurnt + "' where date='" + currentTime + "'";

			stmt.executeUpdate(updateQuery);
		} else {
			for (Double caloriesBurnt : map.values()) {
				totalCaloriesBurnt += caloriesBurnt;
			}

			String insertQuery = "INSERT INTO table_fitness(weight,treadMill,dumbBells,cycling,pullUp,benchPress,date,totalCalories) "
					+ "VALUES ('" + Physical.weight + "','" + map.get("treadMill") + "','" + map.get("dumbBells")
					+ "','" + map.get("cycling") + "','" + map.get("pullUp") + "','" + map.get("benchPress") + "','"
					+ currentTime + "','" + totalCaloriesBurnt + "')";

			stmt.executeUpdate(insertQuery);
		}
		jdbcMain.close();
	}
}

package org.mustard.fitnessmonitor.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mustard.fitnessmonitor.models.Cycling;
import org.mustard.fitnessmonitor.models.MustardSupport;
import org.mustard.fitnessmonitor.models.Physical;
import org.mustard.fitnessmonitor.models.Running;
import org.mustard.fitnessmonitor.models.Swimming;
import org.mustard.fitnessmonitor.models.Walking;
import org.mustard.fitnessmonitor.models.Yoga;

/**
 * Servlet implementation class AddActivity
 */
@WebServlet("/AddActivity")
public class AddActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MustardSupport ms = new MustardSupport();
		Physical[] physicals = new Physical[5];
		physicals[0] = new Running();
		physicals[1] = new Walking();
		physicals[2] = new Swimming();
		physicals[3] = new Cycling();
		physicals[4] = new Yoga();

		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("running", 0.0); // setting default value of caloriesburnt by
									// all activities to 0
		map.put("walking", 0.0);
		map.put("cycling", 0.0);
		map.put("swimming", 0.0);
		map.put("yoga", 0.0);

		String activity = "";
		response.getWriter().append("Served at: ").append(request.getContextPath());
		activity = request.getParameter("newActivity");
		String level = request.getParameter("level");
		String measType = request.getParameter("measType");
		String specifiedMiles = request.getParameter("specifiedMiles");
		String specifiedTime = request.getParameter("specifiedTime");
		double weight = 0.0;
		HashMap<String, Integer> levelMap = new HashMap<String, Integer>();
		levelMap.put("low", 1);
		levelMap.put("medium", 2);
		levelMap.put("high", 3);
		try {
			Physical.weight=ms.fetchWeight();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (activity.equalsIgnoreCase("running")) {
			if (measType.equalsIgnoreCase("Distance")) {
				physicals[0].setDistance(Double.parseDouble(specifiedMiles));
			} else {
				physicals[0].setTime(Double.parseDouble(specifiedTime));
			}
			physicals[0].setLevel(levelMap.get(level));
			map.put("running", physicals[0].calculateCalories());
		} else if (activity.equalsIgnoreCase("walking")) {

			if (measType.equalsIgnoreCase("Distance")) {
				physicals[1].setDistance(Double.parseDouble(specifiedMiles));
			} else {
				physicals[1].setTime(Double.parseDouble(specifiedTime));
			}
			physicals[1].setLevel(levelMap.get(level));
			map.put("walking", physicals[1].calculateCalories());
		} else if (activity.equalsIgnoreCase("swimming")) {
			if (measType.equalsIgnoreCase("Distance")) {
				physicals[2].setDistance(Double.parseDouble(specifiedMiles));
			} else {
				physicals[2].setTime(Double.parseDouble(specifiedTime));
			}
			map.put("swimming", physicals[2].calculateCalories());
		} else if (activity.equalsIgnoreCase("cycling")) {
			if (measType.equalsIgnoreCase("Distance")) {
				physicals[3].setDistance(Double.parseDouble(specifiedMiles));
			} else {
				physicals[3].setTime(Double.parseDouble(specifiedTime));
			}
			physicals[3].setLevel(levelMap.get(level));
			map.put("cycling", physicals[3].calculateCalories());
		} else if (activity.equalsIgnoreCase("Yoga/Aerobics")) {
			physicals[4].setTime(Double.parseDouble(specifiedTime));
			physicals[4].setLevel(levelMap.get(level));
			map.put("yoga", physicals[4].calculateCalories());
		} else {

		}
		System.out.println(map);
		// ms.showData();
		try {
			ms.storeData(map);
			// ms.showData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
import org.mustard.fitnessmonitor.models.TreadMill;
import org.mustard.fitnessmonitor.models.PullUp;
import org.mustard.fitnessmonitor.models.DumbBells;
import org.mustard.fitnessmonitor.models.BenchPress;

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
		physicals[0] = new TreadMill();
		physicals[1] = new DumbBells();
		physicals[2] = new PullUp();
		physicals[3] = new Cycling();
		physicals[4] = new BenchPress();

		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("treadMill", 0.0);
		map.put("dumbBells", 0.0);
		map.put("cycling", 0.0);
		map.put("pullUp", 0.0);
		map.put("benchPress", 0.0);

		response.getWriter().append("Served at: ").append(request.getContextPath());
		String activity = request.getParameter("newActivity");
		String level = request.getParameter("level");
		String measType = request.getParameter("measType");
		String specifiedMiles = request.getParameter("specifiedMiles");
		String specifiedTime = request.getParameter("specifiedTime");
		String actWeight = request.getParameter("weight");
		String repetition = request.getParameter("repetition");

		try {
			double weight = ms.fetchWeight();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HashMap<String, Integer> levelMap = new HashMap<String, Integer>();
		levelMap.put("low", 1);
		levelMap.put("medium", 2);
		levelMap.put("high", 3);
		try {
			Physical.weight = ms.fetchWeight();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (activity.equalsIgnoreCase("treadMill")) {
			if (measType.equalsIgnoreCase("Distance")) {
				physicals[0].setDistance(Double.parseDouble(specifiedMiles));
			} else {
				physicals[0].setTime(Double.parseDouble(specifiedTime));
			}
			physicals[0].setLevel(levelMap.get(level));
			map.put("treadMill", physicals[0].calculateCalories());
		} else if (activity.equalsIgnoreCase("dumbBells")) {
			physicals[1].setRepetition(Integer.parseInt(repetition));
			physicals[1].setDumbbellWeight(Double.parseDouble(actWeight));
			map.put("dumbBells", physicals[1].calculateCalories());
		} else if (activity.equalsIgnoreCase("Pull-up"))

		{
			physicals[2].setRepetition(Integer.parseInt(repetition));
			map.put("pullUp", physicals[2].calculateCalories());
		} else if (activity.equalsIgnoreCase("Excercise Bike"))

		{
			if (measType.equalsIgnoreCase("Distance")) {
				physicals[3].setDistance(Double.parseDouble(specifiedMiles));
			} else {
				physicals[3].setTime(Double.parseDouble(specifiedTime));
			}
			physicals[3].setLevel(levelMap.get(level));
			map.put("cycling", physicals[3].calculateCalories());
		} else if (activity.equalsIgnoreCase("Bench-press"))

		{

			physicals[4].setRepetition(Integer.parseInt(repetition));
			physicals[4].setBenchPressWeight(Double.parseDouble(actWeight));
			map.put("benchPress", physicals[4].calculateCalories());
		} else {
		}
		System.out.println(map);
		// ms.showData();
		try

		{
			ms.storeData(map);
			// ms.showData();
		} catch (

		SQLException e)

		{
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

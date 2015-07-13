package org.mustard.fitnessmonitor.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mustard.fitnessmonitor.models.MustardSupport;

/**
 * Servlet implementation class ShowHistory
 */
@WebServlet("/ShowHistory")
public class ShowHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		MustardSupport support = new MustardSupport();

		Map<String, List<Object>> result = null;
		try {
			result = support.fetchAllData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(result);

		request.setAttribute("date", result.get("date"));
		request.setAttribute("weight", result.get("weight"));
		request.setAttribute("treadMill", result.get("treadMill"));
		request.setAttribute("dumbBells", result.get("dumbBells"));
		request.setAttribute("cycling", result.get("cycling"));
		request.setAttribute("pullUp", result.get("pullUp"));
		request.setAttribute("benchPress", result.get("benchPress"));
		request.setAttribute("total", result.get("total"));
		request.getRequestDispatcher("/jsp/history.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

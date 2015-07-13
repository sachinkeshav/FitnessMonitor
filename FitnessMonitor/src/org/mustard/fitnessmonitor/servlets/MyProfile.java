package org.mustard.fitnessmonitor.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mustard.fitnessmonitor.models.JdbcMain;
import org.mustard.fitnessmonitor.models.Profile;

/**
 * Servlet implementation class MyProfile
 */
@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JdbcMain main = new JdbcMain();
		Statement conn = main.connection();

		if (request.getParameter("firstName") != null) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String height = request.getParameter("height");
			String weight = request.getParameter("weight");
			String query = "update profile set FirstName=\"" + firstName + "\", LastName=\"" + lastName
					+ "\", Gender=\"" + gender + "\", Dob=\"" + dob + "\", Height=\"" + height + "\", Weight=\""
					+ weight + "\" where Id='1'";
			try {
				conn.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Profile profile = new Profile();
		String query = "select * from profile";
		try {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				profile.setFirstName(rs.getString("FirstName"));
				profile.setLastName(rs.getString("LastName"));
				profile.setDob(rs.getDate("DOB"));
				profile.setGender(rs.getString("Gender").charAt(0));
				profile.setHeight(rs.getString("Height"));
				profile.setWeight(rs.getString("Weight"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("profile", profile);

		getServletContext().getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
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

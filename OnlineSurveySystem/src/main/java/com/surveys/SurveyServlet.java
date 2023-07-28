package com.surveys;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SurveyServlet
 */
@WebServlet("/SurveyServlet")
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fullnames = request.getParameter("fullnames");
		String emails = request.getParameter("emails");
		String feedbacks = request.getParameter("feedbacks");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinesurvey","root","@deveshi2004");
			String query = "insert into customer_response values(?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,fullnames);
			ps.setString(2,emails);
			ps.setString(3,feedbacks);
			int i = ps.executeUpdate();
			if(i>0)
			{
				out.print("<h1>Thankyou for completing the survey </h1><br>");
				out.print("<h1>Your Response is recorded");
			}else
			{
				out.print("<h1>Please enter Valid response</h1>");
			}
			ps.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			out.print(e);
			e.printStackTrace();
		} catch (SQLException e) {
			out.print(e);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddEmployeeServlet extends HttpServlet {
@Override
	protected void doGet(
	
	HttpServletRequest req,
	HttpServletResponse resp) throws IOException{
	
	//get PrintWriter
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
		}
		//generate the connection
		try {
			String jdbcURL = "jdbc:mysql://localhost:3306/project1?useSSL=false";
			String jdbcUsername = "root";
			String jdbcPassword = "N@ti(7652)";
		Connection conn =
		
		DriverManager.getConnection(
		jdbcURL,jdbcUsername,jdbcPassword);
		
		final String query =
				"insert into employees(name, designation, salary) values(?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		resp.setContentType("text/html");
		//get the book info
		String employeeName = req.getParameter("name");
		String designation = req.getParameter("designation");
		float salary = Float.parseFloat(req.getParameter("salary"));
		ps.setString(1, employeeName);
		ps.setString(2, designation);
		ps.setFloat(3, salary);
		int count = ps.executeUpdate();
		if(count == 1) {
		pw.println("<h2> Employee registered successfully.</h2");
		}
		else {
		pw.println("<h2> Employee Not registered successfully.</h2");
		}
		} catch (SQLException se) {
		se.printStackTrace();
		pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
			}
//		pw.println("<a href='Home.jsp'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='Home.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='ViewEmployeesServlet'>Employee List</a>");
	}
	@Override
	
	protected void doPost(
	
	HttpServletRequest req,
	HttpServletResponse resp) throws IOException {
	
		doGet(req, resp);
	}
}

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class RegisterServlet
 */
public class ViewEmployeesServlet extends HttpServlet {
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
		
		final String query = "select id, name,designation, salary from employees";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		pw.println("<table border = '1'>");
		pw.println("<tr>");
		pw.println("<th>Id</th>");
		pw.println("<th>Name</th>");
		pw.println("<th>Designation</th>");
		pw.println("<th>Salary</th>");
		pw.println("<th>Edit</th>");
		pw.println("<th>Delete</th>");
		pw.println("</tr>");
		while(rs.next()) {
			pw.println("<tr>");
			pw.println("<td>" + rs.getInt(1) + "</td>");
			pw.println("<td>" + rs.getString(2) + "</td>");
			pw.println("<td>" + rs.getString(3) + "</td>");
			pw.println("<td>" + rs.getFloat(4) + "</td>");
			pw.println("<td><a href ='EditScreenServlet?id=" + rs.getInt(1) +
			"'>edit</a></td>");
			pw.println("<td><a href ='DeleteEmployeeServlet?id=" + rs.getInt(1) +
			"'>delete</a></td>");
			pw.println("</tr>");
			}
		
			pw.println("</table>");
		} catch (SQLException se) {
		se.printStackTrace();
		pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
			}
			pw.println("<a href='Home.html'>Home</a>");
			
	}
	@Override
	
	protected void doPost(
	
	HttpServletRequest req,
	HttpServletResponse resp) throws IOException {
	
		doGet(req, resp);
	}
}
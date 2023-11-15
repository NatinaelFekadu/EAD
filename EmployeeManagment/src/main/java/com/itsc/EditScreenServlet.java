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

/**
 * Servlet implementation class EditScreenServlet
 */
public class EditScreenServlet extends HttpServlet {
	private static final String query =
	"select name, designation, salary from employees where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse
	resp) throws IOException{
	
	//get PrintWriter
	PrintWriter pw = resp.getWriter();
	//set content type
	resp.setContentType("text/html");
	// get the id of record
	int id = Integer.parseInt(req.getParameter("id"));
	//load the jdbc driver
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
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
	
	PreparedStatement ps = conn.prepareStatement(query);
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	rs.next();
	pw.println("<form action = 'EditEmployeeServlet?id="+id+"' method = 'post'>");
	pw.println("<table>");
	pw.println("<tr>");
	pw.println("<td>Employee Name</td>");
	pw.println("<td><input type = 'text', name = 'name', value = '" + rs.getString(1)+"'</td>");

	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<td>Designation</td>");
	pw.println("<td><input type = 'text', name = 'designation', value = '" + rs.getString(2)+"'</td>");

	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<td>Salary</td>");
	pw.println("<td><input type = 'text', name = 'salary', value = '" + rs.getFloat(3)+"'</td>");

	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<td><input type = 'submit' value = 'Edit'></td>");

	pw.println("<td><input type = 'reset' value = 'Cancel'></td>");

	pw.println("</tr>");
	pw.println("</table>");
	pw.println("</form>");
	
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
	protected void doPost(HttpServletRequest req, HttpServletResponse
	resp) throws IOException{
	
	doGet(req, resp);
	}
}
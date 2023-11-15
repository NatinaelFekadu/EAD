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
 * Servlet implementation class EditServlet
 */
public class EditEmployeeServlet extends HttpServlet {
	private static final String query ="update employees set name=?, designation=?, salary=? where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse
	resp) throws IOException {
	
	//get PrintWriter
	PrintWriter pw = resp.getWriter();
	//set content type
	resp.setContentType("text/html");
	// get the id of record
	int id = Integer.parseInt(req.getParameter("id"));
	//get the edited data
	String name = req.getParameter("name");
	String designation = req.getParameter("designation");
	float salary =
	
	Float.parseFloat(req.getParameter("salary"));
	
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
	ps.setString(1, name);
	ps.setString(2, designation);
	ps.setFloat(3, salary);
	ps.setInt(4, id);
	int count = ps.executeUpdate();
	if(count == 1) {
	pw.println("<h2>Record is edited successfully.</h2>");
	
	}else {
	pw.println("<h2>Record not edited.</h2>");
	
	}
	} catch (SQLException se) {
	se.printStackTrace();
	pw.println("<h1>" + se.getMessage() + "</h1>");
	} catch (Exception e) {
	e.printStackTrace();
	pw.println("<h1>" + e.getMessage() + "</h1>");
	}
	pw.println("<a href='Home.html'>Home</a>");
	pw.print("<br>");
	pw.println("<a href='ViewEmployeesServlet'>Employees List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse
	resp) throws ServletException, IOException {
	
	doGet(req, resp);
	}
}
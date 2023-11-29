package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "select * from users where email = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("name");
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("welcome.jsp");
                } else {
                    response.sendRedirect("login.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
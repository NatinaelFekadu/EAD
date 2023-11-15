package com.itsc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class OperationServlet
 */
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        // Dispatch to the appropriate servlet based on the selected operation
        if ("add".equals(operation)) {
            request.getRequestDispatcher("/AdditionServlet").forward(request, response);
        } else if ("multiply".equals(operation)) {
            request.getRequestDispatcher("/MultiplicationServlet").forward(request, response);
        } else {
            // Handle invalid operation
            response.getWriter().println("Invalid operation selected");
        
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

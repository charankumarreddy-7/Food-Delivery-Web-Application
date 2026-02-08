package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String username = (String) session.getAttribute("myUsername");
		String password = (String) session.getAttribute("myPassword");
		
		PrintWriter out = resp.getWriter();
		
		out.println(" <H1>" + username +  "Your account has been blocked. Try after 24 hrs </H1> ");
		
	}

}

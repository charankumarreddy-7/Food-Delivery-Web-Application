package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.Employee;
import com.tap.model.User;
import com.tap.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String role = req.getParameter("role");
		
		User user = new User(name, password, email, address, role);
		
		UserDao userDaoImpl = new UserDaoImpl();
		
		userDaoImpl.addUser(user);
		
		resp.sendRedirect("login.jsp");
		
		}
		
		
		
		
	
		
		
		
		
		
		
		
	

}

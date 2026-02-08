package com.tap.servlets;

import java.io.IOException;

import com.tap.dao.UserDao;
import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String password = req.getParameter("password");

		UserDao userDaoImpl = new UserDaoImpl();
		User user = userDaoImpl.getUserByUserName(name);

		if (user != null && password.equals(user.getPassword())) {

			HttpSession session = req.getSession();

			session.setAttribute("user", user);

			resp.sendRedirect("checkout");
			
			return;

		} else {

			resp.sendRedirect("login.html");
		}
	}
}

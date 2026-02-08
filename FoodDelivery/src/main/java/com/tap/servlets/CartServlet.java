package com.tap.servlets;

import java.io.IOException;

import com.tap.dao.MenuDao;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		Integer oldRestaurantId = (Integer) session.getAttribute("oldRestaurantId");

		String action = req.getParameter("action");

		if ("add".equals(action)) {
			int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
			if ((cart == null) || (oldRestaurantId == null) || (oldRestaurantId != restaurantId)) {

				cart = new Cart(); // creation of new cart

				session.setAttribute("cart", cart);
				session.setAttribute("oldRestaurantId", restaurantId);
			}
			addItemToCart(req, cart);
			
		} else if ("update".equals(action) && (cart != null)) {
			updateItemOfCart(req, cart);
			
		} else if ("remove".equals(action) && (cart != null)) {
			
			removeItemFromCart(req, cart);
		}

		RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
		rd.forward(req, resp);
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {

		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		MenuDao impl = new MenuDaoImpl();
		Menu menu = impl.getMenu(itemId);

		String itemName = menu.getName();

		double price = menu.getPrice();

		CartItem cartItem = new CartItem(itemId, itemName, quantity, price);

		cart.addItems(cartItem);
	}

	private void updateItemOfCart(HttpServletRequest req, Cart cart) {

		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		cart.updateItems(itemId, quantity);

	}

	private void removeItemFromCart(HttpServletRequest req, Cart cart) {

		int itemId = Integer.parseInt(req.getParameter("itemId"));

		cart.removeItems(itemId);

	}

}

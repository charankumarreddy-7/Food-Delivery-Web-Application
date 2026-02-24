package com.tap.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.dao.OrdersDao;
import com.tap.dao.OrdersItemDao;
import com.tap.daoimpl.OrdersDaoImpl;
import com.tap.daoimpl.OrdersItemDaoImpl;
import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Orders;
import com.tap.model.OrdersItem;
import com.tap.model.Restaurant;
import com.tap.model.User;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("Checkout.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");

		Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");

		String address = req.getParameter("address");

		String paymentMethod = req.getParameter("paymentMethod");

		if (user == null) {
			resp.sendRedirect("login.jsp");
			return;
		}

		if (cart != null && user != null && !cart.getItems().isEmpty()) {

			Orders order = new Orders();
			order.setUserId(user.getId());
			order.setRestaurantId(restaurantId);
			order.setOrderDate(new Timestamp(System.currentTimeMillis()));
			order.setAddress(address);
			order.setStatus("peding");
			order.setPaymentMethod(paymentMethod);

			double totalAmount = 0.0;
			for (CartItem item : cart.getItems().values()) {
				totalAmount = totalAmount + item.getPrice() * item.getQuantity();
			}

			order.setTotalAmount(totalAmount);

			OrdersDao impl = new OrdersDaoImpl();
			int orderId = impl.addOrders(order);

			for (CartItem item : cart.getItems().values()) {
				int itemId = item.getItemId();
				int quantity = item.getQuantity();
				double price = item.getPrice();

				OrdersItem ordersItem = new OrdersItem();
				ordersItem.setOrderId(orderId);
				ordersItem.setMenuId(itemId);
				ordersItem.setQuantity(quantity);
				ordersItem.setTotalPrice(price);

				OrdersItemDao ordersItemDaoImpl = new OrdersItemDaoImpl();
				ordersItemDaoImpl.addOrderItems(ordersItem);
			}
			
			
			session.setAttribute("orderId", orderId);
			session.setAttribute("items", cart.getItems());
			session.setAttribute("totalAmount", totalAmount);

			session.removeAttribute("cart");
			session.removeAttribute("oldRestaurantId");

			resp.sendRedirect("orderConfirmation.jsp");

		} else {
			resp.sendRedirect("Cart.jsp");
		}
	}
}

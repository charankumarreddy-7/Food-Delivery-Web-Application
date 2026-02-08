package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrdersItemDao;
import com.tap.model.OrdersItem;
import com.tap.utility.DBConnection;

public class OrdersItemDaoImpl implements OrdersItemDao {

	private static String ADD_ORDERSITEM = "Insert into `ordersitem`(`order_id`, `menu_id`, `quantity`, `total_price`) values(?,?,?,?) ";

	private static String GET_ORDERSITEM = "select * from `ordersitem` where `ordersitem_id` = ? ";

	private static String UPDATE_ORDERSITEM = "update `ordersitem` set `order_id` = ?, `menu_id` = ?, `quantity` = ?, `total_price` = ? "
			+ " where `ordersitem_id` = ? ";

	private static String DELETE_ORDERSITEM = "delete from `ordersitem` where `ordersitem_id` = ? ";

	private static String GET_ALL_ORDERSITEM = "select `ordersitem_id`, `order_id`, `menu_id`, `quantity`, `total_price` from `ordersitem` ";

	@Override
	public void addOrderItems(OrdersItem items) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDERSITEM);) {

			preparedStatement.setInt(1, items.getOrderId());
			preparedStatement.setInt(2, items.getMenuId());
			preparedStatement.setInt(3, items.getQuantity());
			preparedStatement.setDouble(4, items.getTotalPrice());

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public OrdersItem getOrdersItem(int id) {

		OrdersItem ordersItem = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERSITEM);) {

			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				
				int ordersItemId = result.getInt("ordersitem_id");
				int orderId = result.getInt("order_id");
				int menuId = result.getInt("menu_id");
				int quantity = result.getInt("quantity");
				Double totalPrice = result.getDouble("total_price");

				ordersItem = new OrdersItem(ordersItemId, orderId, menuId, quantity, totalPrice);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ordersItem;
	}

	@Override
	public void updateOrdersItems(OrdersItem items) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERSITEM);) {

			preparedStatement.setInt(1, items.getOrderId());
			preparedStatement.setInt(2, items.getMenuId());
			preparedStatement.setInt(3, items.getQuantity());
			preparedStatement.setDouble(4, items.getTotalPrice());
			preparedStatement.setInt(5, items.getOrderItemId());

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrdersItems(int id) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDERSITEM);) {

			preparedStatement.setInt(1, id);

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<OrdersItem> getAllOrdersItems() {

		List<OrdersItem> list = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERSITEM);) {

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				
				int ordersItemId = result.getInt("ordersitem_id");
				int orderId = result.getInt("order_id");
				int menuId = result.getInt("menu_id");
				int quantity = result.getInt("quantity");
				Double total_price = result.getDouble("total_price");

				OrdersItem ordersItem = new OrdersItem(ordersItemId, orderId, menuId, quantity, total_price);
				list.add(ordersItem);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}

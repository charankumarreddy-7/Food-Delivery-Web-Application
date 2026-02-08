package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrdersDao;
import com.tap.model.Orders;
import com.tap.utility.DBConnection;

public class OrdersDaoImpl implements OrdersDao {

	private static String ADD_ORDERS = "Insert into `orders`(`user_id`, `restaurant_id`, `total_amount`, `order_date`, `address`, "
			+ " `payment_method`, `status`) values(?,?,?,?,?,?,?) ";

	private static String GET_ORDERS = "select * from `orders` where `order_id` = ? ";

	private static String UPDATE_ORDERS = "update `orders` set `user_id` = ?, `restaurant_id` = ?, `total_amount`  = ?, `order_date`  = ?, `address`  = ?,"
			+ " `payment_method`  = ?, `status`  = ? where `order_id` = ? ";

	private static String DELETE_ORDERS = "delete from `orders` where `order_id` = ? ";

	private static String GET_ALL_ORDERS = "select `order_id`, `user_id`, `restaurant_id`, `total_amount`, `order_date`, `address`, `payment_method`, `status` from `orders` ";

	@Override
	public int addOrders(Orders orders) {
		
		int orderId = 0;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDERS, Statement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setInt(1, orders.getUserId());
			preparedStatement.setInt(2, orders.getRestaurantId());
			preparedStatement.setDouble(3, orders.getTotalAmount());
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(5, orders.getAddress());
			preparedStatement.setString(6, orders.getPaymentMethod());
			preparedStatement.setString(7, orders.getStatus());

			preparedStatement.execute();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if(generatedKeys.next()) {
				orderId = generatedKeys.getInt(1);
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return orderId;

	}

	@Override
	public Orders getOrders(int id) {

		Orders orders = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS);) {

			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				
				int orderId = result.getInt("order_id");
				int userId = result.getInt("user_id");
				int restaurantId = result.getInt("restaurant_id");
				double totalAmount = result.getDouble("total_amount");
				Timestamp orderDate = result.getTimestamp("order_date");
				String address = result.getString("address");
				String paymentMethod = result.getString("payment_method");
				String status = result.getString("status");

				orders = new Orders(orderId, userId, restaurantId, totalAmount, orderDate, address, paymentMethod, status);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public void updateOrders(Orders orders) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERS);) {

			preparedStatement.setInt(1, orders.getUserId());
			preparedStatement.setInt(2, orders.getRestaurantId());
			preparedStatement.setDouble(3, orders.getTotalAmount());
			preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(5, orders.getAddress());
			preparedStatement.setString(6, orders.getPaymentMethod());
			preparedStatement.setString(7, orders.getStatus());
			preparedStatement.setInt(8, orders.getOrderId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrders(int id) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDERS);) {

			preparedStatement.setInt(1, id);

			preparedStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Orders> getAllOrders() {

		List<Orders> list = new ArrayList<>();

		Orders orders = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);) {

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				
				int orderId = result.getInt("order_id");
				int userId = result.getInt("user_id");
				int restaurantId = result.getInt("restaurant_id");
				double totalAmount = result.getDouble("total_amount");
				Timestamp orderDate = result.getTimestamp("order_date");
				String address = result.getString("address");
				String paymentMethod = result.getString("payment_method");
				String status = result.getString("status");

				orders = new Orders(orderId, userId, restaurantId, totalAmount, orderDate, address, paymentMethod, status);

				list.add(orders);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}

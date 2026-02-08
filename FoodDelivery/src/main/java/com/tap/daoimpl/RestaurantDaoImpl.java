package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;
import com.tap.utility.DBConnection;

public class RestaurantDaoImpl implements RestaurantDao {

	private String ADD_RESTAURANT = "Insert into `restaurant`(`name`, `admin_id`, `address`, `cuisine_type`, `rating`, `eta`, `image_url`, `is_available`) "
			+ "values(?,?,?,?,?,?,?,?) ";

	private String GET_RESTAURANT = "select * from `restaurant` where `restaurant_id` = ? ";

	private String UPDATE_RESTAURANT = "update `restaurant` set `name` = ?, `admin_id` = ?, `address` = ?, `cuisine_type` = ?, "
			+ " `rating` = ?, `eta` = ?, `image_url` = ?, `is_available` = ? where `restaurant_id` = ? ";

	private String DELETE_RESTAURANT = "delete from `restaurant` where `restaurant_id` = ? ";

	private String GET_ALL_RESTAURANTS = "select `restauran" + ""
			+ "t_id`,  `name`, `admin_id`, `address`, `cuisine_type`, `rating`, `eta`, `image_url`,"
			+ " `is_available` from `restaurant` ";

	@Override
	public void addRestaurant(Restaurant restaurant) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_RESTAURANT);) {

			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setInt(2, restaurant.getAdminId());
			preparedStatement.setString(3, restaurant.getAddress());
			preparedStatement.setString(4, restaurant.getCuisineType());
			preparedStatement.setDouble(5, restaurant.getRating());
			preparedStatement.setInt(6, restaurant.getEta());
			preparedStatement.setString(7, restaurant.getImageUrl());
			preparedStatement.setBoolean(8, restaurant.isavailable());

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurant_id) {

		Restaurant restaurant = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT);) {

			preparedStatement.setInt(1, restaurant_id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {

				int restaurantId = result.getInt("restaurant_id");
				String name = result.getString("name");
				int adminId = result.getInt("admin_id");
				String address = result.getString("address");
				String cuisineType = result.getString("cuisine_type");
				double rating = result.getDouble("rating");
				int eta = result.getInt("eta");
				String imageUrl = result.getString("image_url");
				boolean isAvailable = result.getBoolean("is_available");

				restaurant = new Restaurant(restaurantId, name, adminId, address, cuisineType, rating, eta, imageUrl,
						isAvailable);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurant;

	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT);

			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setInt(2, restaurant.getAdminId());
			preparedStatement.setString(3, restaurant.getAddress());
			preparedStatement.setString(4, restaurant.getCuisineType());
			preparedStatement.setDouble(5, restaurant.getRating());
			preparedStatement.setInt(6, restaurant.getEta());
			preparedStatement.setString(7, restaurant.getImageUrl());
			preparedStatement.setBoolean(8, restaurant.isavailable());
			preparedStatement.setInt(9, restaurant.getRestaurantId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteRestaurant(int restaurant) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT);

			preparedStatement.setInt(1, restaurant);

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Restaurant> getAllRestaurants() {

		List<Restaurant> restaurant = new ArrayList<>();

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESTAURANTS);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {

				int restaurantId = result.getInt("restaurant_id");
				String name = result.getString("name");
				int admin_id = result.getInt("admin_id");
				String address = result.getString("address");
				String cuisineType = result.getString("cuisine_type");
				double rating = result.getDouble("rating");
				int eta = result.getInt("eta");
				String imageUrl = result.getString("image_url");
				boolean isAvailable = result.getBoolean("is_available");

				Restaurant restaurant1 = new Restaurant(restaurantId, name, admin_id, address, cuisineType, rating, eta,
						imageUrl, isAvailable);

				restaurant.add(restaurant1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return restaurant;
	}

}

package com.tap.daoimpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.*;
import com.tap.model.Menu;
import com.tap.utility.DBConnection;

public class MenuDaoImpl implements MenuDao {

	private static String ADD_MENU = "Insert into `menu`(`restaurant_id`, `name`, `description`, `price`, `rating`, `image_url`, `type`, `is_available`) "
			+ " values(?,?,?,?,?,?,?,?) ";

	private static String GET_MENU = "select * from `menu` where `menu_id` = ?";

	private static String UPDATE_MENU = "update `menu` set `restaurant_id` = ?, `name` = ?, `description` = ?, `price` = ?, `rating` = ?, "
			+ "`image_url` = ?, `type` = ?, `is_available` = ? where `menu_id` = ? ";

	private static String DELETE_MENU = "delete from `menu` where `menu_id` = ? ";

	private static String GET_ALL_MENUS = "select `menu_id`, `restaurant_id`, `name`, `description`, `price`, `rating`, `image_url`, `type`, "
			+ "`is_available` from `menu` ";

	private static String GET_ALL_MENUS_BY_RESTAURANT = "select * from `menu` where `restaurant_id`= ? ";

	public void addMenu(Menu menu) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(ADD_MENU);) {

			preparedStatement.setInt(1, menu.getRestaurantId());
			preparedStatement.setString(2, menu.getName());
			preparedStatement.setString(3, menu.getDescription());
			preparedStatement.setDouble(4, menu.getPrice());
			preparedStatement.setDouble(5, menu.getRating());
			preparedStatement.setString(6, menu.getImage_url());
			preparedStatement.setString(7, menu.getType());
			preparedStatement.setBoolean(8, menu.isAvailable());

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Menu getMenu(int id) {

		Menu menu = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU);) {

			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {

				int menuId = result.getInt("menu_id");
				int restaurantId = result.getInt("restaurant_id");
				String name = result.getString("name");
				String description = result.getString("description");
				double price = result.getDouble("price");
				double rating = result.getDouble("rating");
				String image_url = result.getString("image_url");
				String type = result.getString("type");
				boolean isAvailable = result.getBoolean("is_available");

				menu = new Menu(menuId, restaurantId, name, description, price, rating, image_url, type, isAvailable);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return menu;
	}

	@Override
	public void updateMenu(Menu menu) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU);) {

			preparedStatement.setInt(1, menu.getRestaurantId());
			preparedStatement.setString(2, menu.getName());
			preparedStatement.setString(3, menu.getDescription());
			preparedStatement.setDouble(4, menu.getPrice());
			preparedStatement.setDouble(5, menu.getRating());
			preparedStatement.setString(6, menu.getImage_url());
			preparedStatement.setString(7, menu.getType());
			preparedStatement.setBoolean(8, menu.isAvailable());
			preparedStatement.setInt(9, menu.getMenuId());

			preparedStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMenu(int id) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU);) {

			preparedStatement.setInt(1, id);

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Menu> getAllMenus() {

		List<Menu> list = new ArrayList<>();

		Menu menu = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MENUS);) {

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {

				int menuId = result.getInt("menu_id");
				int restaurant_id = result.getInt("restaurant_id");
				String name = result.getString("name");
				String description = result.getString("description");
				double price = result.getDouble("price");
				double rating = result.getDouble("rating");
				String image_url = result.getString("image_url");
				String type = result.getString("type");
				boolean isAvailable = result.getBoolean("is_available");

				menu = new Menu(menuId, restaurant_id, name, description, price, rating, image_url, type, isAvailable);

				list.add(menu);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Menu> getAllMenusByRestaurant(int id) {

		

		List<Menu> list = new ArrayList<>();

		try(Connection connection = DBConnection.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MENUS_BY_RESTAURANT);) {
			

			preparedStatement.setInt(1, id);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {

				int menuId = result.getInt("menu_id");
				int restaurantId = result.getInt("restaurant_id");
				String name = result.getString("name");
				String description = result.getString("description");
				double price = result.getDouble("price");
				double rating = result.getDouble("rating");
				String image_url = result.getString("image_url");
				String type = result.getString("type");
				boolean isAvailable = result.getBoolean("is_available");

				Menu menu = new Menu(menuId, restaurantId, name, description, price, rating, image_url, type,
						isAvailable);

				list.add(menu);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}

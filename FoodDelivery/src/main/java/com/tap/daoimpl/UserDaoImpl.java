package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.UserDao;
import com.tap.utility.DBConnection;
import com.tap.model.User;

public class UserDaoImpl implements UserDao {

	private String ADD_USER = "INSERT INTO `user` (`username`, `password`, `email`, `address`, "
			+ " `role`, `createdDate`, `lastLoginDate`) values (?,?,?,?,?,?,?)";

	private String GET_USER = "SELECT * FROM `user` WHERE `userid` = ?";

	private String UPDATE_USER = "UPDATE `user` SET `password` = ?,  `email` = ?, `address` = ?, `role` = ?  WHERE `userid` = ? ";

	private String DELETE_USER = "DELETE FROM `user` where `userid` = ? ";

	private String GET_ALL_USERS = "SELECT `userid`, `username`, `password`, `email`, `address`, `role`, `createdDate`, `lastLoginDate` FROM `user` ";

	private String GET_BY_USERNAME = "SELECT * FROM user where username = ? ";

	@Override
	public void addUser(User u) {

		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);

			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getEmail());
			preparedStatement.setString(4, u.getAddress());
			preparedStatement.setString(5, u.getRole());
			preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // System means current
																							// executing system i.e
																							// computer
			preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));

			preparedStatement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User getUser(int id) {

		Connection connection = DBConnection.getConnection();
		User u = null;

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(GET_USER);
			prepareStatement.setInt(1, id);

			ResultSet rs = prepareStatement.executeQuery();

			while (rs.next()) {

				int id1 = rs.getInt("userid");
				String username = rs.getString("username"); // these are resultset methods for getting values
				String password = rs.getString("password");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String role = rs.getString("role");
				Timestamp createdData = rs.getTimestamp("createdDate");
				Timestamp lastLoginDate = rs.getTimestamp("lastLoginDate");

				u = new User(id1, username, password, email, address, role, createdData, lastLoginDate);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public void updateUser(User u) {

		Connection connection = DBConnection.getConnection();

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_USER);

			prepareStatement.setString(1, u.getPassword());
			prepareStatement.setString(2, u.getEmail());
			prepareStatement.setString(3, u.getAddress());
			prepareStatement.setString(4, u.getRole());
			prepareStatement.setInt(5, u.getId());

			prepareStatement.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int id) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USER);) { // try with resource
																									// for adding
																									// finally block by
																									// itself

			prepareStatement.setInt(1, id);

			prepareStatement.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> getAllUser() {

		Connection connection = DBConnection.getConnection();

		List<User> user = new ArrayList<>();

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_USERS);

			ResultSet result = prepareStatement.executeQuery();

			while (result.next()) {

				int id = result.getInt("userid");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String address = result.getString("address");
				String role = result.getString("role");
				Timestamp createdDate = result.getTimestamp("createdDate");
				Timestamp lastLoginDate = result.getTimestamp("lastLoginDate");

				User u = new User(id, username, password, email, address, role, createdDate, lastLoginDate);

				user.add(u);

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUserByUserName(String username) {

		User user = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_BY_USERNAME);) {

			statement.setString(1, username);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				String username1 = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String address = result.getString("address");
				String role = result.getString("role");

				user = new User(username1, password, email, address, role);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

}

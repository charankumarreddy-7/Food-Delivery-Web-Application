package com.tap.dao;

import java.util.List;

import com.tap.model.Restaurant;

public interface RestaurantDao {
	
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurant_id);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurant);
	List<Restaurant> getAllRestaurants();

}

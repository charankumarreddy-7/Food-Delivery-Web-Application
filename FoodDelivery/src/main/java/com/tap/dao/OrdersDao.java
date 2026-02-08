package com.tap.dao;

import java.util.List;

import com.tap.model.Orders;

public interface OrdersDao {
	
	int addOrders(Orders orders);
	Orders getOrders(int id);
	void updateOrders(Orders orders);
	void deleteOrders(int id);
	List<Orders> getAllOrders();

}

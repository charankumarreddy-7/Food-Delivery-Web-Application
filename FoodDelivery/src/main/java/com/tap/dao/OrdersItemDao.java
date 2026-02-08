package com.tap.dao;

import java.util.List;

import com.tap.model.OrdersItem;

public interface OrdersItemDao {
	
	void addOrderItems(OrdersItem items);
	OrdersItem getOrdersItem(int id);
	void updateOrdersItems(OrdersItem items);
	void deleteOrdersItems(int id);
	List<OrdersItem> getAllOrdersItems();

}

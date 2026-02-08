package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer, CartItem> items;

	public Cart() {
		this.items = new HashMap<>();
	}

	public void addItems(CartItem item) {
		int itemId = item.getItemId();
		if (items.containsKey(itemId)) {
			CartItem existingItem = items.get(itemId);

			int newQua = item.getQuantity();

			int oldQua = existingItem.getQuantity();

			int sumQua = newQua + oldQua;

			existingItem.setQuantity(sumQua);
		} else {
			items.put(itemId, item);
		}
	}

	public void updateItems(int itemId, int quantity) {

		if (items.containsKey(itemId)) {
			if (quantity <= 0) {
				items.remove(itemId);
			} else {
				CartItem existingCartItem = items.get(itemId);
				existingCartItem.setQuantity(quantity);
			}
		}

	}

	public void removeItems(int itemId) {

		items.remove(itemId);
	}

	public double getTotalPrice() {

		double totalPrice = 0.0;
		for (CartItem cartItem : items.values()) {

			totalPrice = totalPrice + (cartItem.getPrice() * cartItem.getQuantity());
		}

		return totalPrice;
	}

	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	public void clear() {
		items.clear();
	}
}

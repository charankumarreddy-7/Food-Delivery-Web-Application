package com.tap.model;

public class CartItem {

	private int ItemId;
	private String name;
	private int quantity;
	private double price;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	
	public CartItem(int itemId, String name, int quantity, double price) {
		super();
		this.ItemId = itemId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public int getItemId() {
		return ItemId;
	}
	public void setItemId(int itemId) {
		this.ItemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CartItem [ItemId=" + ItemId + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}

	
}

package com.example.ItemOrderClientApplication.domain;

import java.util.List;

public class Order {
	private String orderId;
	private String orderDate;
	private String billOwner;
	private List<Item> itemsList;
	
	public Order(String orderId, String orderDate, String billOwner, List<Item> itemsList) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.billOwner = billOwner;
		this.itemsList = itemsList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getBillOwner() {
		return billOwner;
	}

	public void setBillOwner(String billOwner) {
		this.billOwner = billOwner;
	}

	public List<Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}
	
}

package com.shoppingcart.dto;

import java.util.List;

public class ShoppingCart {

	private List<Item> items;
	private double salesTaxes;
	private double total;

	public ShoppingCart(List<Item> items, double salesTaxes, double total) {
		this.items = items;
		this.salesTaxes = salesTaxes;
		this.total = total;
	}

	public List<Item> getItems() {
		return items;
	}

	public double getSalesTaxes() {
		return salesTaxes;
	}

	public double getTotal() {
		return total;
	}
}

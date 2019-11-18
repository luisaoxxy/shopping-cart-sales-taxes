package com.shoppingcart.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Item {

	private int amount;
	private String name;
	private boolean imported;
	private boolean exempt;
	private double price;
	private double saleTax;

	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}
	public boolean isImported() {
		return imported;
	}

	public boolean isExempt() {
		return exempt;
	}

	public double getPrice() {
		return this.price;
	}

	public double getTotalPrice() {
		return this.price + saleTax;
	}

	public void setSaleTax(double tax) {
		this.saleTax = tax;
	}

	public double getSaleTax() {
		return saleTax;
	}

	public static class Builder {

		private final Item object;

		public Builder withName(String value) {
			object.name = value;
			return this;
		}

		public Builder withImported(boolean value) {
			object.imported = value;
			return this;
		}

		public Builder withExempt(boolean value) {
			object.exempt = value;
			return this;
		}

		public Builder withAmount(int value) {
			object.amount = value;
			return this;
		}

		public Builder withPrice(double value) {
			object.price = value;
			return this;
		}

		public Builder withSaleTax(double value) {
			object.saleTax = value;
			return this;
		}
		public Builder() {
			object = new Item();
		}
		public Item build() {
			return object;
		}
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		Item aItem = (Item) o;

		return new EqualsBuilder().append(name, aItem.name)
				.append(imported, aItem.imported).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(7, 37)
				.append(name).append(imported).toHashCode();
	}
}

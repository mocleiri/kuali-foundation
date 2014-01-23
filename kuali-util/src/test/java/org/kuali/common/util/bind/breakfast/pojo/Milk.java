package org.kuali.common.util.bind.breakfast.pojo;

public class Milk {

	public Milk() {
		System.out.println("yo!");
	}

	double price;
	String type;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

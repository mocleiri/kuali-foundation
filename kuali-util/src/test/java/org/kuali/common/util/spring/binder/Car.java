package org.kuali.common.util.spring.binder;

import javax.validation.constraints.Min;

public class Car {

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	private String manufacturer;
	private String color;

	// No negative sticker prices!
	@Min(0)
	private double stickerPrice;

	// First car was built in 1886
	@Min(1886)
	private int year;

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getStickerPrice() {
		return stickerPrice;
	}

	public void setStickerPrice(double stickerPrice) {
		this.stickerPrice = stickerPrice;
	}

}

package org.kuali.common.util.spring.binder;

public class Car {

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

	private String manufacturer;
	private String color;
	private double stickerPrice;

}

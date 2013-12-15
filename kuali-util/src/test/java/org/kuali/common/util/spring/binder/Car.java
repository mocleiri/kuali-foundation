package org.kuali.common.util.spring.binder;

public class Car {

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	private String manufacturer;
	private String color;
	private double stickerPrice;
	private int year; // First year a car was made was 1886

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

package org.kuali.common.util.validate.hibernate;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.ImmutableList;

public class Car {

	public List<String> getPassengers() {
		return passengers;
	}

	@NotNull
	private final String manufacturer;

	@NotNull
	@Size(min = 2, max = 14)
	@CheckCase(value = CaseMode.UPPER, message = "{CheckCase.Car.licensePlate}")
	private String licensePlate;

	@Min(2)
	private final int seatCount;

	@NotNull
	private final List<String> passengers;

	public Car(String manufacturer, String licensePlate, int seatCount) {
		this(manufacturer, licensePlate, seatCount, ImmutableList.<String> of());
	}

	public Car(String manufacturer, String licensePlate, int seatCount, List<String> passengers) {
		this.manufacturer = manufacturer;
		this.licensePlate = licensePlate;
		this.seatCount = seatCount;
		this.passengers = ImmutableList.copyOf(passengers);
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public int getSeatCount() {
		return seatCount;
	}

}

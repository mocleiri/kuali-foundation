package org.kuali.common.util.builder;

import org.junit.Test;

public class CarTest {

	@Test
	public void test() {
		try {
			Car car = new Car.Builder("Ford").description(" awesome  ").build();
			System.out.println(car.getMake());
			System.out.println(car.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
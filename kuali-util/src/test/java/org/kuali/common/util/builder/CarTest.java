package org.kuali.common.util.builder;

import org.junit.Test;

public class CarTest {

	@Test
	public void licensePlateCase() {
		try {
			Car car = new Car.Builder("Ford").description("Awesome and new").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
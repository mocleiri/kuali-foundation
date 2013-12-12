package org.kuali.common.util.builder;

import org.junit.Test;

public class CarTest {

	@Test
	public void test() {
		try {
			new Car.Builder("Ford").description("Awesome and new").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
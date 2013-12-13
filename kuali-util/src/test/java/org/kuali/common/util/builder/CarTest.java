package org.kuali.common.util.builder;

import org.junit.Test;

public class CarTest {

	@Test
	public void test() {
		try {
			new Car.Builder(null).description("   ").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
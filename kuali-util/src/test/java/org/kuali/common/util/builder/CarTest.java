package org.kuali.common.util.builder;

import org.junit.Test;
import org.kuali.common.util.nullify.NullUtils;

public class CarTest {

	@Test
	public void test() {
		try {
			System.setProperty("car.description", "        ");
			System.setProperty("car.make", "ford");
			Car car = new Car.Builder(null).description("sucks").build();
			String description = NullUtils.toNone(car.getDescription());
			System.out.println(car.getMake());
			System.out.println(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
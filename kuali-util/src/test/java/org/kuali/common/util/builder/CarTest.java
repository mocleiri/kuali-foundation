package org.kuali.common.util.builder;

import org.junit.Test;
import org.kuali.common.util.nullify.NullUtils;

public class CarTest {

	@Test
	public void test() {
		try {
			System.setProperty("car.description", "    awesome    ");
			System.setProperty("car.make", "");
			Car car = new Car.Builder(null).build();
			System.out.println(car.getMake());
			String description = NullUtils.NONE;
			if (car.getDescription().isPresent()) {
				description = car.getDescription().get();
			}
			System.out.println(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
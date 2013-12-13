package org.kuali.common.util.builder;

import org.junit.Test;

public class CarTest {

	@Test
	public void test() {
		try {
			BuilderContext ctx = new BuilderContext.Builder().build();
			new Car.Builder(ctx, null).description("   ").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
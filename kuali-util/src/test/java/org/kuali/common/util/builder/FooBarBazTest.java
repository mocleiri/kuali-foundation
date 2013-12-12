package org.kuali.common.util.builder;

import org.junit.Test;

public class FooBarBazTest {

	@Test
	public void test1() {
		try {
			new Car.Builder("Ford").description("Awesome and new").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
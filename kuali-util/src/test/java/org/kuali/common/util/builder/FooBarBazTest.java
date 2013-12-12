package org.kuali.common.util.builder;


public class FooBarBazTest {

	public static void main(String[] args) {
		try {
			new MyCar.Builder("Ford").description("Awesome and new").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
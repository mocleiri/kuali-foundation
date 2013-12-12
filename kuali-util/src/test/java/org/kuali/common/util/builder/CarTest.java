package org.kuali.common.util.builder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class CarTest {

	@Test
	public void test() {
		try {
			Car car = new Car.Builder("Ford").description("Awesome and new").build();
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			validator.validate(car);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
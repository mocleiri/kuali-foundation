package org.kuali.common.util.builder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.kuali.common.util.env.DefaultOverrideService;
import org.kuali.common.util.env.OverrideService;

public class CarTest {

	@Test
	public void test() {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			OverrideService overrider = new DefaultOverrideService();
			BuilderContext ctx = new BuilderContext.Builder(validator, overrider).build();

			new Car.Builder(ctx, null).description("   ").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
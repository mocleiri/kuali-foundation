package org.kuali.common.util.spring.binder;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class DataBinderTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Car car = new Car();
			// String name = "manufacttttturer";
			PropertyValue m = new PropertyValue("manufacturer", "ford");
			PropertyValue c = new PropertyValue("color", "red");
			PropertyValue s = new PropertyValue("stickerPrice", "3");

			MutablePropertyValues pvs = new MutablePropertyValues();
			pvs.addPropertyValue(m);
			pvs.addPropertyValue(c);
			pvs.addPropertyValue(s);
			DataBinder binder = new DataBinder(car);
			binder.bind(pvs);
			BindingResult result = binder.getBindingResult();
			if (result.hasErrors()) {
				throw new IllegalStateException("there were errors");
			}
			logger.info("car.manufacturer=[{}]", car.getManufacturer());
			logger.info("car.color=[{}]", car.getColor());
			logger.info("car.stickerPrice=[{}]", car.getStickerPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

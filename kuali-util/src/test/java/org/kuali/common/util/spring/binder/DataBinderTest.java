package org.kuali.common.util.spring.binder;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class DataBinderTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Car car = new Car();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("manufacturer", "ford");
			map.put("color", "red");
			map.put("stickerPrice", 19750);

			MutablePropertyValues pvs = new MutablePropertyValues(map);
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

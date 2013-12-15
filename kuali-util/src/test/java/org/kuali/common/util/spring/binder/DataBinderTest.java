package org.kuali.common.util.spring.binder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

public class DataBinderTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Car car = new Car();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("manufacturer", "ford");
			map.put("color", "red");
			map.put("stickerPrice", "foo");
			map.put("year", "bar");

			MutablePropertyValues pvs = new MutablePropertyValues(map);
			DataBinder binder = new DataBinder(car);
			binder.bind(pvs);
			BindingResult result = binder.getBindingResult();
			if (result.hasErrors()) {
				List<ObjectError> errors = result.getAllErrors();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < errors.size(); i++) {
					if (i != 0) {
						sb.append(",");
					}
					ObjectError error = errors.get(i);
					sb.append("[" + error.getDefaultMessage() + "]");
				}
				throw new IllegalStateException(sb.toString());
			}
			logger.info("car.manufacturer=[{}]", car.getManufacturer());
			logger.info("car.color=[{}]", car.getColor());
			logger.info("car.stickerPrice=[{}]", car.getStickerPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

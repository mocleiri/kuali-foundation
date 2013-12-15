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

public class ImmutableCarDataBinderTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			ImmutableCar.Builder builder = new ImmutableCar.Builder();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("manufacturer", "ford");
			map.put("color", "black");
			map.put("year", "1901");
			map.put("stickerPrice", "2000");

			MutablePropertyValues pvs = new MutablePropertyValues(map);
			DataBinder binder = new DataBinder(builder);
			binder.bind(pvs);
			BindingResult result = binder.getBindingResult();
			if (result.hasErrors()) {
				throw new IllegalStateException(getErrorMessage(result.getAllErrors()));
			}
			ImmutableCar car = builder.build();
			logger.info("car.manufacturer=[{}]", car.getManufacturer());
			logger.info("car.color=[{}]", car.getColor());
			logger.info("car.stickerPrice=[{}]", car.getStickerPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getErrorMessage(List<ObjectError> errors) {
		StringBuilder sb = new StringBuilder();
		sb.append("Unexpected binding error(s):\n\n");
		for (int i = 0; i < errors.size(); i++) {
			if (i != 0) {
				sb.append("\n");
			}
			ObjectError error = errors.get(i);
			sb.append("[" + error.getDefaultMessage() + "]");
		}
		sb.append("\n");
		return sb.toString();
	}

}

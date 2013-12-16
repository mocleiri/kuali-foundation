package org.kuali.common.util.spring.binder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

public class ImmutableCarDataBinderTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			ImmutableCar.Builder builder = ImmutableCar.builder();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("year", 2001);
			map.put("make", "Ford");
			map.put("model", "Expedition");
			map.put("price", 21579);
			map.put("internalHardDriveSize", "252.5g");
			map.put("zeroToSixtyTime", "4.7s");

			MutablePropertyValues pvs = new MutablePropertyValues(map);
			DefaultFormattingConversionService service = new DefaultFormattingConversionService();
			service.addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
			service.addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
			DataBinder binder = new DataBinder(builder);
			binder.setConversionService(service);
			binder.bind(pvs);
			System.out.println(binder.getConversionService().getClass().getCanonicalName());
			BindingResult result = binder.getBindingResult();
			if (result.hasErrors()) {
				throw new IllegalStateException(getErrorMessage(result.getAllErrors()));
			}
			Formatter<Number> withDigits = new BytesFormatter(true);
			Formatter<Number> sansDigits = new BytesFormatter(false);
			ImmutableCar car = builder.build();
			logger.info("car.year=[{}]", car.getYear());
			logger.info("car.make=[{}]", car.getMake());
			logger.info("car.model=[{}]", car.getModel());
			logger.info("car.price=[{}]", car.getPrice());
			logger.info("car.internalHardDriveSize.raw=[{}]", car.getInternalHardDriveSize());
			logger.info("car.internalHardDriveSize.withDigits=[{}]", withDigits.print(car.getInternalHardDriveSize(), null));
			logger.info("car.internalHardDriveSize.sansDigits=[{}]", sansDigits.print(car.getInternalHardDriveSize(), null));
			logger.info("car.zeroToSixtyTime=[{}]", car.getZeroToSixtyTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getErrorMessage(List<ObjectError> errors) {
		StringBuilder sb = new StringBuilder();
		if (errors.size() == 1) {
			sb.append("Unexpected binding error:\n\n");
		} else {
			sb.append("Unexpected binding error(s):\n\n");
		}
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

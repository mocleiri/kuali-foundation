package org.kuali.common.util.spring.binder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.validate.ValidationUtils;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.Assert;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class CarTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Car.Builder builder = Car.builder();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("year", 1887);
			map.put("make", "Ford");
			map.put("model", "Expedition");
			map.put("price", 21579);
			map.put("internalHardDriveSizeInBytes", "252.5g");
			map.put("zeroToSixtyTimeInMillis", "4.7s");
			javax.validation.Validator hibernateValidator = ValidationUtils.getDefaultValidator();
			Validator validator = new SpringValidatorAdapter(hibernateValidator);

			MutablePropertyValues pvs = new MutablePropertyValues(map);
			DefaultFormattingConversionService service = new DefaultFormattingConversionService();
			service.addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
			service.addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
			DataBinder binder = new DataBinder(builder);
			binder.setConversionService(service);
			binder.setValidator(validator);
			binder.bind(pvs);
			Errors bindErrors = binder.getBindingResult();
			if (bindErrors.hasErrors()) {
				throw new IllegalStateException(getErrorMessage("Binding", binder));
			}
			binder.validate();
			Errors validationErrors = binder.getBindingResult();
			if (validationErrors.hasErrors()) {
				throw new IllegalStateException(getErrorMessage("Validation", binder));
			}
			builder.withZeroToSixtyTimeInMillis(25);
			doCar(builder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doCar(Car.Builder builder) {
		Formatter<Number> withDigits = new BytesFormatter(true);
		Formatter<Number> sansDigits = new BytesFormatter(false);
		Car car = builder.build();
		logger.info("car.year=[{}]", car.getYear());
		logger.info("car.make=[{}]", car.getMake());
		logger.info("car.model=[{}]", car.getModel());
		logger.info("car.price=[{}]", car.getPrice());
		logger.info("car.internalHardDriveSize.raw=[{}]", car.getInternalHardDriveSizeInBytes());
		logger.info("car.internalHardDriveSize.withDigits=[{}]", withDigits.print(car.getInternalHardDriveSizeInBytes(), null));
		logger.info("car.internalHardDriveSize.sansDigits=[{}]", sansDigits.print(car.getInternalHardDriveSizeInBytes(), null));
		logger.info("car.zeroToSixtyTime=[{}]", car.getZeroToSixtyTimeInMillis());
	}

	protected String getErrorMessage(String type, DataBinder binder) {
		Errors errors = binder.getBindingResult();
		Assert.isTrue(errors.hasErrors(), "No errors were reported");
		List<FieldError> fieldErrors = errors.getFieldErrors();
		List<ObjectError> globalErrors = errors.getGlobalErrors();
		StringBuilder sb = new StringBuilder();
		if (errors.getErrorCount() == 1) {
			sb.append(type + " error:\n\n");
		} else {
			sb.append(type + " error(s):\n\n");
		}
		String classDeclarationPath = ReflectionUtils.getDeclarationPath(binder.getTarget().getClass());
		for (FieldError fieldError : fieldErrors) {
			String propertyPath = classDeclarationPath + "." + fieldError.getField();
			String message = fieldError.getDefaultMessage();
			Object rejectedValue = fieldError.getRejectedValue();
			String errorMessage = "[" + propertyPath + "] " + message + " - [" + rejectedValue + "] is invalid";
			sb.append(errorMessage + "\n");
		}
		for (ObjectError globalError : globalErrors) {
			String message = globalError.getDefaultMessage();
			String errorMessage = "[" + classDeclarationPath + "] " + message;
			sb.append(errorMessage + "\n");
		}
		return sb.toString();
	}
}

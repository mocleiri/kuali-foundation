package org.kuali.common.util.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.kuali.common.util.CollectionUtils;

public class ValidationUtils {

	private static Validator instance;

	public synchronized static Validator getDefaultValidator() {
		if (instance == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			instance = factory.getValidator();
		}
		return instance;
	}

	public static <T> void check(Set<ConstraintViolation<T>> violations) {
		if (violations.size() == 0) {
			return;
		}
		List<String> errorMessages = new ArrayList<String>();
		for (ConstraintViolation<T> violation : violations) {
			String errorMessage = getErrorMessage(violation);
			errorMessages.add(errorMessage);
		}
		String errorMessage = CollectionUtils.asCSV(errorMessages);
		throw new IllegalArgumentException(errorMessage);
	}

	public static <T> String getErrorMessage(ConstraintViolation<T> violation) {
		String name = violation.getRootBeanClass().getSimpleName();
		String property = violation.getPropertyPath() + "";
		return "[" + name + "." + property + " " + violation.getMessage() + "]";
	}

}

package org.kuali.common.util.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (ConstraintViolation<T> violation : violations) {
			if (i++ != 0) {
				sb.append(", ");
			}
			sb.append("[" + violation.getMessage() + "]");
		}
		throw new IllegalArgumentException(sb.toString());
	}

}

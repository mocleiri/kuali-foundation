package org.kuali.common.util.validate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class ValidationUtils {

	private static Validator instance;

	public synchronized static Validator getDefaultValidator() {
		if (instance == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			instance = factory.getValidator();
		}
		return instance;
	}

	public static Optional<String> errorMessage(Field field, String suffix) {
		return Optional.of(getErrorMessage(field, suffix));
	}

	public static String getErrorMessage(Field field, String suffix) {
		String classDeclarationPath = ReflectionUtils.getDeclarationPath(field.getDeclaringClass());
		return "[" + classDeclarationPath + "." + field.getName() + "] " + suffix;
	}

	public static <T> void check(Set<ConstraintViolation<T>> violations) {
		if (violations.size() == 0) {
			return;
		}
		List<String> errorMessages = getErrorMessages(violations);
		String errorMessage = CollectionUtils.asCSV(errorMessages);
		throw new IllegalArgumentException(errorMessage);
	}

	public static <T> List<String> getErrorMessages(Set<ConstraintViolation<T>> violations) {
		List<String> errorMessages = new ArrayList<String>();
		for (ConstraintViolation<T> violation : violations) {
			String errorMessage = getErrorMessage(violation);
			errorMessages.add(errorMessage);
		}
		return errorMessages;
	}

	public static <T> String getErrorMessage(ConstraintViolation<T> violation) {
		String classDeclarationPath = ReflectionUtils.getDeclarationPath(violation.getRootBeanClass());
		String property = violation.getPropertyPath() + "";
		return "[" + classDeclarationPath + "." + property + " " + violation.getMessage() + "]";
	}

}

package org.kuali.common.core.validate;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.ReflectionUtils.getDeclarationPath;
import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class Validation {

	private static final ValidatorFactory FACTORY = buildDefaultValidatorFactory();
	private static final Validator VALIDATOR = FACTORY.getValidator();
	private static final Class<?>[] EMPTY_CLASS_ARRAY = {};

	public static ValidatorFactory getDefaultValidatorFactory() {
		return FACTORY;
	}

	public static Validator getDefaultValidator() {
		return VALIDATOR;
	}

	public static Optional<String> errorMessage(Field field, String suffix) {
		return Optional.of(getErrorMessage(field, suffix));
	}

	public static String getErrorMessage(Field field, String suffix) {
		String classDeclarationPath = getDeclarationPath(field.getDeclaringClass());
		return "[" + classDeclarationPath + "." + field.getName() + "] " + suffix;
	}

	public static <T> boolean isValid(T instance, Validator validator, List<Class<?>> groups) {
		return getViolations(instance, validator, groups).size() == 0;
	}

	public static <T> Set<ConstraintViolation<T>> getViolations(T instance, Validator validator, List<Class<?>> groups) {
		return validator.validate(instance, groups.toArray(EMPTY_CLASS_ARRAY));
	}

	public static <T> T checkConstraints(T instance, Validator validator) {
		checkNotNull(instance, "instance");
		checkNotNull(validator, "validator");
		checkViolations(getViolations(instance, validator, ImmutableList.<Class<?>> of()));
		return instance;
	}

	public static <T> T checkConstraints(T instance, Validator validator, List<Class<?>> groups) {
		checkNotNull(instance, "instance");
		checkNotNull(validator, "validator");
		checkNotNull(groups, "groups");
		checkViolations(getViolations(instance, validator, groups));
		return instance;
	}

	public static <T> T checkConstraints(T instance) {
		return checkConstraints(instance, VALIDATOR);
	}

	public static <T> void checkViolations(Set<ConstraintViolation<T>> violations) {
		checkNotNull(violations, "violations");
		if (violations.isEmpty()) {
			return;
		}
		List<String> errorMessages = getErrorMessages(violations);
		StringBuilder sb = new StringBuilder();
		sb.append("Validation failed:\n\n");
		sb.append(Joiner.on('\n').join(errorMessages));
		sb.append("\n");
		throw illegalArgument(sb.toString());
	}

	public static <T> List<String> getErrorMessages(Set<ConstraintViolation<T>> violations) {
		List<String> errorMessages = newArrayList();
		for (ConstraintViolation<T> violation : violations) {
			errorMessages.add(getErrorMessage(violation));
		}
		return errorMessages;
	}

	public static <T> String getErrorMessage(ConstraintViolation<T> violation) {
		String classDeclarationPath = getDeclarationPath(violation.getRootBeanClass());
		String propertyPath = violation.getPropertyPath() + "";
		if (isBlank(propertyPath)) {
			return format("[%s - %s]", classDeclarationPath, violation.getMessage());
		} else {
			return format("[%s.%s - %s]", classDeclarationPath, propertyPath, violation.getMessage());
		}
	}

}

package org.kuali.common.util.validate;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class Validation {

	private static ValidatorFactory FACTORY = javax.validation.Validation.buildDefaultValidatorFactory();
	private static Validator VALIDATOR = FACTORY.getValidator();

	public static Validator getDefaultValidator() {
		return VALIDATOR;
	}

	public static List<Annotation> getConstraints(Field field) {
		return getConstraints(field.getAnnotations());
	}

	public static List<Annotation> getConstraints(Class<?> type) {
		return getConstraints(type.getAnnotations());
	}

	public static List<Annotation> getConstraints(Annotation[] annotations) {
		List<Annotation> list = new ArrayList<Annotation>();
		for (Annotation element : annotations) {
			if (isConstraint(element)) {
				list.add(element);
			}
		}
		return ImmutableList.copyOf(list);
	}

	public static boolean isConstraint(Annotation annotation) {
		return annotation.annotationType().isAnnotationPresent(Constraint.class);
	}

	public static Optional<String> errorMessage(Field field, String suffix) {
		return Optional.of(getErrorMessage(field, suffix));
	}

	public static String getErrorMessage(Field field, String suffix) {
		String classDeclarationPath = ReflectionUtils.getDeclarationPath(field.getDeclaringClass());
		return "[" + classDeclarationPath + "." + field.getName() + "] " + suffix;
	}

	public static <T> T checkConstraints(Validator validator, T instance) {
		checkNotNull(instance, "instance");
		checkNotNull(validator, "validator");
		checkViolations(validator.validate(instance));
		return instance;
	}

	public static <T> T checkConstraints(T instance) {
		return checkConstraints(VALIDATOR, instance);
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
		String classDeclarationPath = ReflectionUtils.getDeclarationPath(violation.getRootBeanClass());
		String propertyPath = violation.getPropertyPath() + "";
		if (isBlank(propertyPath)) {
			return "[" + classDeclarationPath + " - " + violation.getMessage() + "]";
		} else {
			return "[" + classDeclarationPath + "." + propertyPath + " " + violation.getMessage() + "]";
		}
	}

}

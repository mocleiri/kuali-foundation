package org.kuali.common.util.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class Validation {

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

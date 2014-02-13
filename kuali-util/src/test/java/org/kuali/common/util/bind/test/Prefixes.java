package org.kuali.common.util.bind.test;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.kuali.common.util.Annotations.extractClassAnnotation;
import static org.kuali.common.util.Annotations.extractFieldAnnotation;

import java.lang.reflect.Field;

import org.kuali.common.util.bind.api.Bind;

import com.google.common.base.Optional;

public class Prefixes {

	public static Optional<String> get(Class<?> type) {
		Optional<String> prefix = getPrefix(extractClassAnnotation(type, Bind.class));
		if (prefix.isPresent()) {
			return prefix;
		}
		return Optional.of(getPrefix(type));
	}

	public static Optional<String> get(Field field) {
		Optional<String> prefix = getPrefix(extractFieldAnnotation(field, Bind.class));
		if (prefix.isPresent()) {
			return prefix;
		}
		return Optional.of(field.getName());
	}

	protected static Optional<String> getPrefix(Optional<Bind> annotation) {
		if (annotation.isPresent()) {
			String value = annotation.get().value();
			checkState(!isBlank(value), "[%s.value()] cannot be blank", Bind.class.getCanonicalName());
			return Optional.of(value);
		} else {
			return absent();
		}
	}

	protected static String getPrefix(Class<?> type) {
		return uncapitalize(type.getSimpleName());
	}

}

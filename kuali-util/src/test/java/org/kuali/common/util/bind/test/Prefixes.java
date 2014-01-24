package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Annotations;
import org.kuali.common.util.bind.api.BindingPrefix;
import org.kuali.common.util.bind.api.NoBindingPrefix;

import com.google.common.base.Optional;

public class Prefixes {

	public static Optional<String> get(Class<?> type) {
		Optional<String> prefix = getPrefix(Annotations.get(type, BindingPrefix.class));
		if (prefix.isPresent()) {
			return prefix;
		}
		if (type.isAnnotationPresent(NoBindingPrefix.class)) {
			return Optional.absent();
		} else {
			return Optional.of(getPrefix(type));
		}
	}

	public static Optional<String> get(Field field) {
		Optional<String> prefix = getPrefix(Annotations.get(field, BindingPrefix.class));
		if (prefix.isPresent()) {
			return prefix;
		}
		if (field.isAnnotationPresent(NoBindingPrefix.class)) {
			return Optional.absent();
		} else {
			return Optional.of(field.getName());
		}
	}

	protected static Optional<String> getPrefix(Optional<BindingPrefix> annotation) {
		if (annotation.isPresent()) {
			String value = annotation.get().value();
			checkState(!StringUtils.isBlank(value), "[%s.value()] cannot be blank", BindingPrefix.class.getCanonicalName());
			return Optional.of(value);
		} else {
			return Optional.absent();
		}
	}

	protected static String getPrefix(Class<?> type) {
		return StringUtils.uncapitalize(type.getSimpleName());
	}

}

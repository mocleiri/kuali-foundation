package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.kuali.common.util.Annotations.extractClassAnnotation;
import static org.kuali.common.util.Annotations.extractFieldAnnotation;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;
import java.util.List;

import org.kuali.common.util.bind.api.Bind;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

public class BindKeyFunction implements Function<List<Field>, String> {

	public BindKeyFunction(Class<?> type) {
		this.type = checkNotNull(type, "type");
	}

	public static BindKeyFunction make(Class<?> type) {
		return new BindKeyFunction(type);
	}

	private final Joiner joiner = Joiner.on('.');
	private final Class<?> type;

	@Override
	public String apply(List<Field> fields) {
		checkNotNull(fields, "fields");
		List<String> strings = newArrayList();
		addIfPresent(strings, getToken(type));
		for (Field field : fields) {
			addIfPresent(strings, getToken(field));
		}
		return joiner.join(strings);
	}

	protected void addIfPresent(List<String> strings, Optional<String> optional) {
		if (optional.isPresent()) {
			strings.add(optional.get());
		}
	}

	protected Optional<String> getToken(Class<?> type) {
		Optional<Bind> annotation = extractClassAnnotation(type, Bind.class);
		String provided = uncapitalize(type.getSimpleName());
		return getToken(provided, annotation);
	}

	protected Optional<String> getToken(Field field) {
		Optional<Bind> annotation = extractFieldAnnotation(field, Bind.class);
		String provided = uncapitalize(field.getName());
		return getToken(provided, annotation);
	}

	protected Optional<String> getToken(String provided, Optional<Bind> annotation) {
		if (!annotation.isPresent()) {
			return Optional.of(provided);
		}
		String value = annotation.get().value();
		checkNotBlank(value, Bind.class.getCanonicalName() + ".value()");
		if (Bind.ABSENT.equals(value)) {
			return absent();
		}
		if (Bind.DEFAULT.equals(value)) {
			return Optional.of(provided);
		} else {
			return Optional.of(value);
		}
	}
}

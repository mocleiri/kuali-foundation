package org.kuali.common.util.bind.breakfast.immutable;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.kuali.common.util.Annotations.extractClassAnnotation;
import static org.kuali.common.util.Annotations.extractFieldAnnotation;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.bind.api.Bind.ABSENT;
import static org.kuali.common.util.bind.api.Bind.DEFAULT;

import java.lang.reflect.Field;
import java.util.List;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class BindKeysFunction implements Function<List<Field>, List<String>> {

	private static final char SEPARATOR = '.';
	private static final Joiner JOINER = Joiner.on(SEPARATOR);

	public BindKeysFunction(Class<?> type) {
		this.type = checkNotNull(type, "type");
	}

	public static BindKeysFunction newBindKeyFunction(Class<?> type) {
		return new BindKeysFunction(type);
	}

	private final Class<?> type;

	@Override
	public List<String> apply(List<Field> fields) {
		checkNotNull(fields, "fields");
		Optional<String> prefix = getPrefix(type, fields);
		List<String> suffixes = getSuffixes(fields);
		List<String> bindKeys = newArrayList();
		for (String suffix : suffixes) {
			if (prefix.isPresent()) {
				bindKeys.add(prefix.get() + SEPARATOR + suffix);
			} else {
				bindKeys.add(suffix);
			}
		}
		return bindKeys;
	}

	protected List<String> getSuffixes(List<Field> fields) {
		List<String> suffixes = newArrayList();
		Field lastField = fields.get(fields.size() - 1);
		Optional<Alias> annotation = extractFieldAnnotation(lastField, Alias.class);
		if (annotation.isPresent()) {
			suffixes.addAll(ImmutableList.copyOf(annotation.get().value()));
		}
		suffixes.add(lastField.getName());
		return suffixes;
	}

	protected Optional<String> getPrefix(Class<?> type, List<Field> fields) {
		List<String> strings = newArrayList();
		addIfPresent(strings, getToken(type));
		for (int i = 0; i < fields.size() - 1; i++) {
			Field field = fields.get(i);
			addIfPresent(strings, getToken(field));
		}
		if (strings.isEmpty()) {
			return absent();
		} else {
			return Optional.of(JOINER.join(strings));
		}
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
		if (ABSENT.equals(value)) {
			return absent();
		}
		if (DEFAULT.equals(value)) {
			return Optional.of(provided);
		} else {
			return Optional.of(value);
		}
	}
}

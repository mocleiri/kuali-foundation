package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BindAlias;
import org.kuali.common.util.bind.api.BindPrefix;
import org.kuali.common.util.system.SystemProperties;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BindUtilsTest {

	@Test
	public void test() {
		try {
			Set<String> keys = getKeys(SystemProperties.class);
			System.out.println("----- Keys -----");
			for (String key : keys) {
				System.out.println(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Set<String> getKeys(Class<?> type) {
		return getKeys(Optional.<String> absent(), type);
	}

	public Set<String> getKeys(Optional<String> prefix, Class<?> type) {
		SortedSet<String> keys = Sets.newTreeSet();
		Optional<BindPrefix> classPrefixAnnotation = Optional.fromNullable(type.getAnnotation(BindPrefix.class));
		Optional<String> actualPrefix = getPrefix(prefix, type, classPrefixAnnotation);
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			if (field.isAnnotationPresent(Bind.class)) {
				Optional<BindPrefix> fieldPrefixAnnotation = Optional.fromNullable(field.getAnnotation(BindPrefix.class));
				Optional<String> fieldPrefix = getPrefix(Optional.<String> absent(), field.getType(), fieldPrefixAnnotation);
				Optional<String> newPrefix = combine(prefix, fieldPrefix, ".");
				Set<String> nestedKeys = getKeys(newPrefix, field.getType());
				keys.addAll(nestedKeys);
			} else {
				List<String> fieldKeys = getKeys(field);
				List<String> transformed = transform(fieldKeys, actualPrefix, ".");
				keys.addAll(transformed);
			}
		}
		return keys;
	}

	protected Optional<String> combine(Optional<String> s1, Optional<String> s2, String separator) {
		StringBuilder sb = new StringBuilder();
		if (s1.isPresent()) {
			sb.append(s1.get());
		}
		if (s1.isPresent() && s2.isPresent()) {
			sb.append(separator);
		}
		if (s2.isPresent()) {
			sb.append(s2.get());
		}
		return Optional.fromNullable(StringUtils.trimToNull(sb.toString()));
	}

	protected List<String> transform(List<String> original, Optional<String> prefix, String separator) {
		if (prefix.isPresent()) {
			Function<String, String> prefixer = PrefixFunction.make(prefix.get(), separator);
			return Lists.transform(original, prefixer);
		} else {
			return original;
		}
	}

	protected List<String> getKeys(Field field) {
		Optional<BindAlias> alias = Optional.fromNullable(field.getAnnotation(BindAlias.class));
		if (!alias.isPresent()) {
			return ImmutableList.of(field.getName());
		} else {
			List<String> keys = Lists.newArrayList();
			keys.addAll(ImmutableList.copyOf(alias.get().value()));
			if (alias.get().includeFieldName()) {
				keys.add(field.getName());
			}
			return keys;
		}
	}

	protected Optional<String> getPrefix(Optional<String> prefix, Class<?> type, Optional<BindPrefix> annotation) {
		// Explicit prefix. This overrides everything
		if (prefix.isPresent()) {
			return prefix;
		}

		if (!annotation.isPresent()) {
			return Optional.of(getPrefix(type));
		}

		BindPrefix bind = annotation.get();

		// They have specifically said, "don't use a prefix"
		if (bind.none()) {
			return Optional.absent();
		}

		// An explicit prefix class has been configured on the annotation. This overrides value()
		if (!bind.type().equals(void.class)) {
			return Optional.of(getPrefix(bind.type()));
		}

		if (bind.value().equals("")) {
			// Use a prefix derived from the class type
			return Optional.of(getPrefix(type));
		} else {
			// An explicit prefix value has been configured on the annotation.
			checkState(!StringUtils.isBlank(bind.value()), "[%s.value()] cannot be blank", bind.getClass().getCanonicalName());
			return Optional.of(bind.value());
		}

	}

	protected String getPrefix(Class<?> type) {
		return StringUtils.uncapitalize(type.getSimpleName());
	}

}

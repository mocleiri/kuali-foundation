package org.kuali.common.util.bind.test;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
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
		Optional<Bind> classAnnotation = Optional.fromNullable(type.getAnnotation(Bind.class));
		Optional<String> actualPrefix = getPrefix(prefix, type, classAnnotation);
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			List<String> fieldKeys = getKeys(field);
			List<String> transformed = transform(fieldKeys, actualPrefix);
			keys.addAll(transformed);
			Optional<Bind> fieldAnnotation = Optional.fromNullable(field.getAnnotation(Bind.class));
			if (fieldAnnotation.isPresent()) {
				keys.addAll(getKeys(prefix, field.getType()));
			}
		}
		return keys;
	}

	protected List<String> transform(List<String> original, Optional<String> prefix) {
		if (prefix.isPresent()) {
			Function<String, String> prefixer = PrefixFunction.make(prefix.get(), ".");
			return Lists.transform(original, prefixer);
		} else {
			return original;
		}
	}

	protected List<String> getKeys(Field field) {
		Optional<Alias> alias = Optional.fromNullable(field.getAnnotation(Alias.class));
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

	protected Optional<String> getPrefix(Optional<String> prefix, Class<?> type, Optional<Bind> annotation) {
		// Explicit prefix. This overrides everything
		if (prefix.isPresent()) {
			return prefix;
		}

		if (!annotation.isPresent()) {
			return Optional.of(getPrefix(type));
		}

		Bind bind = annotation.get();

		// They have specifically said, "don't use a prefix"
		if (bind.noPrefix()) {
			return Optional.absent();
		}

		// An explicit prefix class has been configured on the annotation. This overrides value()
		if (!bind.prefix().equals(void.class)) {
			return Optional.of(getPrefix(bind.prefix()));
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
		return StringUtils.uncapitalise(type.getSimpleName());
	}

}

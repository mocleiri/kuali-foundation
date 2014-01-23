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
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.system.SystemProperties;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BindUtilsTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Set<String> keys = getKeys(SystemProperties.class);
			for (String key : keys) {
				logger.info("key={}", key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Set<String> getKeys(Class<?> type) {
		return getKeys(Optional.<String> absent(), type);
	}

	public Set<String> getKeys(Optional<String> prefix, Class<?> type) {
		if (!type.isAnnotationPresent(Bind.class)) {
			return Sets.newHashSet();
		}
		SortedSet<String> keys = Sets.newTreeSet();
		Optional<String> actualPrefix = getPrefix(prefix, type, type.getAnnotation(Bind.class));
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			List<String> aliases = getAliases(field);
			List<String> prefixed = Lists.transform(aliases, null);
			String fieldName = field.getName();
			if (actualPrefix.isPresent()) {
				String key = actualPrefix.get() + "." + fieldName;
				keys.add(key);
			} else {
				String key = fieldName;
				keys.add(key);
			}
		}
		return keys;
	}

	protected List<String> getAliases(Field field) {
		Optional<Alias> alias = Optional.of(field.getAnnotation(Alias.class));
		if (!alias.isPresent()) {
			return ImmutableList.of();
		} else {
			return ImmutableList.copyOf(alias.get().value());
		}
	}

	protected Optional<String> getPrefix(Optional<String> prefix, Class<?> type, Bind bind) {
		// Explicit prefix. This overrides everything
		if (prefix.isPresent()) {
			return prefix;
		}

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

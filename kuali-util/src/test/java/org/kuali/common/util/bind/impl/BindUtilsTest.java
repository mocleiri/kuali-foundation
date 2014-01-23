package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkState;

import java.util.Set;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.system.SystemProperties;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class BindUtilsTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Set<String> keys = getKeys(SystemProperties.class);
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
		Optional<String> actualPrefix = getPrefix(prefix, type, type.getAnnotation(Bind.class));

		return null;
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

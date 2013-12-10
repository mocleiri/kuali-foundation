package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.annotation.EnvOverride;
import org.kuali.common.util.spring.env.annotation.EnvPrefix;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class DefaultEnvironmentOverrideService implements EnvironmentOverrideService {

	public org.kuali.common.util.spring.env.EnvironmentService getEnv() {
		return env;
	}

	private final org.kuali.common.util.spring.env.EnvironmentService env;

	@Override
	public void override(Object instance) {
		Optional<String> prefix = getPrefix(instance);
		for (Field field : instance.getClass().getDeclaredFields()) {
			EnvOverride annotation = field.getAnnotation(EnvOverride.class);
			if (annotation != null) {
				List<String> keys = getKeys(prefix, field, annotation);
				override(instance, field, keys);
			}
		}
	}

	private Optional<String> getPrefix(Object instance) {
		EnvPrefix prefix = instance.getClass().getAnnotation(EnvPrefix.class);
		if (prefix == null || StringUtils.isBlank(prefix.value())) {
			return Optional.<String> absent();
		} else {
			return Optional.of(prefix.value());
		}
	}

	protected void set(Object instance, Field field, Object value) {
		try {
			field.set(instance, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void override(Object instance, Field field, List<String> keys) {
		Class<?> type = field.getType();
		for (String key : keys) {
			Optional<?> optional = SpringUtils.getOptionalProperty(env, key, type);
			if (optional.isPresent()) {
				set(this, field, optional.get());
				return;
			}
		}
	}

	protected List<String> getKeys(Optional<String> prefix, Field field, EnvOverride override) {
		List<String> keys = new ArrayList<String>();
		if (override.keys().length > 0) {
			keys.addAll(ImmutableList.copyOf(override.keys()));
		} else {
			keys.add(field.getName());
		}
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", keys);
		} else {
			return keys;
		}
	}

	private DefaultEnvironmentOverrideService(Builder builder) {
		this.env = builder.env;
	}

	public static class Builder {

		private org.kuali.common.util.spring.env.EnvironmentService env = new BasicEnvironmentService();

		public Builder env(org.kuali.common.util.spring.env.EnvironmentService env) {
			this.env = env;
			return this;
		}

		public DefaultEnvironmentOverrideService build() {
			DefaultEnvironmentOverrideService instance = new DefaultEnvironmentOverrideService(this);
			validate(instance);
			return instance;
		}

		private void validate(DefaultEnvironmentOverrideService instance) {
			Preconditions.checkNotNull(instance.env, "env may not be null");
		}
	}

}

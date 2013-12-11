package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.annotation.EnvOverride;
import org.kuali.common.util.spring.env.annotation.EnvOverrideOptional;
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
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			override(prefix, instance, field);
			overrideOptionals(prefix, instance, field);
		}
	}

	private void overrideOptionals(Optional<String> prefix, Object instance, Field field) {
		EnvOverrideOptional override = field.getAnnotation(EnvOverrideOptional.class);
		if (override != null) {
			List<String> keys = getKeys(prefix, field, override.keys());
			override(instance, field, keys, override.type());
		}
	}

	private void override(Optional<String> prefix, Object instance, Field field) {
		EnvOverride override = field.getAnnotation(EnvOverride.class);
		if (override != null) {
			List<String> keys = getKeys(prefix, field, override.keys());
			override(instance, field, keys, field.getType());
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

	protected void override(Object instance, Field field, List<String> keys, Class<?> type) {
		for (String key : keys) {
			Optional<?> optional = SpringUtils.getOptionalProperty(env, key, type);
			if (optional.isPresent()) {
				Object value = optional.get();
				set(instance, field, value);
			}
		}
	}

	protected void set(Object instance, Field field, Object value) {
		try {
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}
			field.set(instance, value);
			if (!accessible) {
				field.setAccessible(false);
			}
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	protected List<String> getKeys(Optional<String> prefix, Field field, String[] keys) {
		List<String> list = new ArrayList<String>();
		if (keys.length > 0) {
			list.addAll(ImmutableList.copyOf(keys));
		} else {
			list.add(field.getName());
		}
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", list);
		} else {
			return list;
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

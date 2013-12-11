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

	private void override(Optional<String> prefix, Object instance, Field field) {
		EnvOverride annotation = field.getAnnotation(EnvOverride.class);
		if (annotation != null) {
			List<String> keys = getKeys(prefix, field, annotation.keys());
			Optional<?> value = SpringUtils.getOptionalProperty(env, keys, field.getType());
			if (value.isPresent()) {
				set(instance, field, value.get());
			}
		}
	}

	private void overrideOptionals(Optional<String> prefix, Object instance, Field field) {
		EnvOverrideOptional annotation = field.getAnnotation(EnvOverrideOptional.class);
		if (annotation != null) {
			List<String> keys = getKeys(prefix, field, annotation.keys());
			Optional<?> value = SpringUtils.getOptionalProperty(env, keys, annotation.type());
			if (value.isPresent()) {
				set(instance, field, value);
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

	private void set(Object instance, Field field, Object value) {
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

	private List<String> getKeys(Optional<String> prefix, Field field, String[] keys) {
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
			Preconditions.checkNotNull(instance.env, "'env' cannot be null");
		}
	}

}

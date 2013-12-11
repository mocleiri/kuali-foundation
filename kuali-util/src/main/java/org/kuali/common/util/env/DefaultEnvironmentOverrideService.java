package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.annotation.EnvOverride;

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
		EnvOverride annotation = instance.getClass().getAnnotation(EnvOverride.class);
		if (annotation != null && annotation.skip()) {
			return;
		}
		Optional<String> prefix = getPrefix(instance);
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			override(prefix, instance, field);
		}
	}

	private void override(Optional<String> prefix, Object instance, Field field) {
		EnvOverride annotation = field.getAnnotation(EnvOverride.class);
		if (annotation != null && annotation.skip()) {
			return;
		}
		List<String> keys = getKeys(prefix, field, annotation);
		Class<?> type = field.getType();
		Optional<?> value = SpringUtils.getOptionalProperty(env, keys, type);
		if (value.isPresent()) {
			set(instance, field, value.get());
		}
	}

	private Optional<String> getPrefix(Object instance) {
		EnvOverride annotation = instance.getClass().getAnnotation(EnvOverride.class);
		if (annotation == null || StringUtils.isBlank(annotation.prefix())) {
			return Optional.<String> absent();
		} else {
			return Optional.of(annotation.prefix());
		}
	}

	private void set(Object instance, Field field, Object value) {
		try {
			synchronized (field) {
				boolean accessible = field.isAccessible();
				if (!accessible) {
					field.setAccessible(true);
				}
				field.set(instance, value);
				if (!accessible) {
					field.setAccessible(false);
				}
			}
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	private List<String> getKeys(Optional<String> globalPrefix, Field field, EnvOverride annotation) {
		List<String> list = new ArrayList<String>();
		if (annotation.keys().length > 0) {
			list.addAll(ImmutableList.copyOf(annotation.keys()));
		} else {
			list.add(field.getName());
		}
		Optional<String> prefix = getPrefix(globalPrefix, annotation.prefix());
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", list);
		} else {
			return list;
		}
	}

	private Optional<String> getPrefix(Optional<String> globalPrefix, String fieldPrefix) {
		if (!StringUtils.isBlank(fieldPrefix)) {
			return Optional.of(fieldPrefix);
		} else if (globalPrefix.isPresent()) {
			return Optional.of(globalPrefix.get());
		} else {
			return Optional.absent();
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

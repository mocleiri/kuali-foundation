package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.ListUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.env.adapter.EnvironmentAdapter;
import org.kuali.common.util.env.annotation.EnvAdapter;
import org.kuali.common.util.env.annotation.EnvOverride;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;

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
		Optional<EnvOverride> classAnnotation = Optional.fromNullable(instance.getClass().getAnnotation(EnvOverride.class));
		if (classAnnotation.isPresent() && classAnnotation.get().skip()) {
			return;
		}
		Optional<String> classPrefix = getPrefix(classAnnotation);
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			override(classAnnotation, classPrefix, instance, field);
		}
	}

	private void override(Optional<EnvOverride> classAnnotation, Optional<String> classPrefix, Object instance, Field field) {
		Optional<EnvOverride> fieldAnnotation = Optional.fromNullable(field.getAnnotation(EnvOverride.class));

		// No class annotation and no field annotation, nothing to do
		if (!classAnnotation.isPresent() && !fieldAnnotation.isPresent()) {
			return;
		}

		// The field level annotation is present but we are skipping this field
		if (fieldAnnotation.isPresent() && fieldAnnotation.get().skip()) {
			return;
		}

		// Either the class annotation or the field annotation was present
		List<String> keys = getKeys(classPrefix, field, fieldAnnotation);
		Optional<EnvAdapter> conversionAnnotation = Optional.fromNullable(field.getAnnotation(EnvAdapter.class));
		Optional<? extends EnvironmentAdapter<?, ?>> converter = getConverter(conversionAnnotation);
		Class<?> type = converter.isPresent() ? converter.get().getSourceType() : field.getType();
		Optional<?> value = SpringUtils.getOptionalProperty(env, keys, type);
		if (!value.isPresent()) {
			return; // nothing to do if there is no value
		}
		if (converter.isPresent()) {
			Object result = ReflectionUtils.invokeMethod(converter.get(), "convert", value.get());
			value = Optional.of(result);
		}
		set(instance, field, value.get());
	}

	private Optional<? extends EnvironmentAdapter<?, ?>> getConverter(Optional<EnvAdapter> conversionAnnotation) {
		if (conversionAnnotation.isPresent()) {
			Class<? extends EnvironmentAdapter<?, ?>> converterClass = conversionAnnotation.get().value();
			EnvironmentAdapter<?, ?> converter = ReflectionUtils.newInstance(converterClass);
			return Optional.of(converter);
		} else {
			return Optional.absent();
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

	private List<String> getKeys(Optional<String> globalPrefix, Field field, Optional<EnvOverride> fieldAnnotation) {
		List<String> list = new ArrayList<String>();
		if (fieldAnnotation.isPresent() && fieldAnnotation.get().keys().length > 0) {
			list.addAll(ImmutableList.copyOf(fieldAnnotation.get().keys()));
		} else {
			list.add(field.getName());
		}
		Optional<String> prefix = getPrefix(globalPrefix, fieldAnnotation);
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", list);
		} else {
			return list;
		}
	}

	private Optional<String> getPrefix(Optional<String> globalPrefix, Optional<EnvOverride> fieldAnnotation) {
		Optional<String> fieldPrefix = getPrefix(fieldAnnotation);
		if (fieldPrefix.isPresent()) {
			return fieldPrefix;
		} else {
			return globalPrefix;
		}
	}

	private Optional<String> getPrefix(Optional<EnvOverride> annotation) {
		if (!annotation.isPresent()) {
			return Optional.absent();
		}
		String prefix = annotation.get().prefix();
		if (EnvOverride.NONE.equals(prefix)) {
			return Optional.absent();
		} else {
			return Optional.of(prefix);
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

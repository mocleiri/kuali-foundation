package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.ListUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.env.adapter.EnvAdapter;
import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.env.annotation.EnvAdapterClass;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class DefaultOverrideService implements OverrideService {

	public EnvironmentService getEnv() {
		return env;
	}

	private final EnvironmentService env;

	@Override
	public void override(Object instance) {
		Optional<Env> classAnnotation = Optional.fromNullable(instance.getClass().getAnnotation(Env.class));
		if (classAnnotation.isPresent() && classAnnotation.get().skip()) {
			return;
		}
		Optional<String> classPrefix = getPrefix(classAnnotation);
		List<Field> fields = ReflectionUtils.getDeclaredFields(instance.getClass(), classAnnotation.get().includeInheritedFields());
		for (Field field : fields) {
			override(classAnnotation, classPrefix, instance, field);
		}
	}

	private void override(Optional<Env> classAnnotation, Optional<String> classPrefix, Object instance, Field field) {
		Optional<Env> fieldAnnotation = Optional.fromNullable(field.getAnnotation(Env.class));

		// No class annotation and no field annotation, nothing to do
		if (!classAnnotation.isPresent() && !fieldAnnotation.isPresent()) {
			return;
		}

		// The field level annotation is present but we are skipping this field
		if (fieldAnnotation.isPresent() && fieldAnnotation.get().skip()) {
			return;
		}

		// The class annotation OR the field annotation was present
		// Examine them + the field info to get a list of keys
		List<String> keys = getKeys(classPrefix, field, fieldAnnotation);

		// Extract the adapter annotation (if there is one)
		Optional<EnvAdapterClass> adapterAnnotation = Optional.fromNullable(field.getAnnotation(EnvAdapterClass.class));

		// Extract the adapter itself (if there is one)
		Optional<? extends EnvAdapter<?, ?>> adapter = getConverter(adapterAnnotation);

		// Figure out what the source type is in the environment abstraction
		Class<?> type = adapter.isPresent() ? adapter.get().getSourceType() : field.getType();

		// Extract a value from the environment
		Optional<?> value = SpringUtils.getOptionalProperty(env, keys, type);

		// nothing to do if there is no value
		if (!value.isPresent()) {
			return;
		}

		// If there is an adapter, use it to convert the value stored in the environment
		if (adapter.isPresent()) {
			Object result = ReflectionUtils.invokeMethod(adapter.get(), "convert", value.get());
			value = Optional.fromNullable(result);
		}

		// Store the value we have on the object
		set(instance, field, value.orNull());
	}

	private Optional<? extends EnvAdapter<?, ?>> getConverter(Optional<EnvAdapterClass> conversionAnnotation) {
		if (conversionAnnotation.isPresent()) {
			Class<? extends EnvAdapter<?, ?>> converterClass = conversionAnnotation.get().value();
			EnvAdapter<?, ?> converter = ReflectionUtils.newInstance(converterClass);
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

	private List<String> getKeys(Optional<String> globalPrefix, Field field, Optional<Env> fieldAnnotation) {
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

	private Optional<String> getPrefix(Optional<String> globalPrefix, Optional<Env> fieldAnnotation) {
		Optional<String> fieldPrefix = getPrefix(fieldAnnotation);
		if (fieldPrefix.isPresent()) {
			return fieldPrefix;
		} else {
			return globalPrefix;
		}
	}

	private Optional<String> getPrefix(Optional<Env> annotation) {
		if (!annotation.isPresent()) {
			return Optional.absent();
		}
		String prefix = annotation.get().prefix();
		if (Env.NOPREFIX.equals(prefix)) {
			return Optional.absent();
		} else {
			return Optional.of(prefix);
		}
	}

	private DefaultOverrideService(Builder builder) {
		this.env = builder.env;
	}

	public static class Builder {

		private EnvironmentService env = new BasicEnvironmentService();

		public Builder env(EnvironmentService env) {
			this.env = env;
			return this;
		}

		public DefaultOverrideService build() {
			DefaultOverrideService instance = new DefaultOverrideService(this);
			validate(instance);
			return instance;
		}

		private void validate(DefaultOverrideService instance) {
			Preconditions.checkNotNull(instance.getEnv(), "'env' cannot be null");
		}
	}

}

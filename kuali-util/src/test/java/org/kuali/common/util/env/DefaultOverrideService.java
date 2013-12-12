package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.ListUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.env.adapter.EnvAdapter;
import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.env.annotation.EnvAdapterClass;
import org.kuali.common.util.env.annotation.EnvIgnore;
import org.kuali.common.util.env.annotation.EnvKeys;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.springframework.util.Assert;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultOverrideService implements OverrideService {

	public DefaultOverrideService() {
		this(new BasicEnvironmentService());
	}

	public DefaultOverrideService(EnvironmentService env) {
		Assert.notNull(env, "'env' cannot be null");
		this.env = env;
	}

	public EnvironmentService getEnv() {
		return env;
	}

	private final EnvironmentService env;

	@Override
	public void override(Object instance) {
		Optional<Env> optional = Optional.fromNullable(instance.getClass().getAnnotation(Env.class));
		if (!optional.isPresent()) {
			return;
		}
		Env annotation = optional.get();
		if (annotation.skip()) {
			return;
		}
		Optional<String> prefix = getPrefix(annotation);
		List<Field> fields = ReflectionUtils.getDeclaredFields(instance.getClass(), annotation.includeInheritedFields());
		for (Field field : fields) {
			override(prefix, instance, field);
		}
	}

	private void override(Optional<String> prefix, Object instance, Field field) {
		Optional<EnvIgnore> ignore = Optional.fromNullable(field.getAnnotation(EnvIgnore.class));
		if (ignore.isPresent()) {
			return;
		}

		// Get the list of environment keys to look things up by
		List<String> keys = getKeys(prefix, field);

		// Extract the adapter annotation (if there is one)
		Optional<EnvAdapterClass> adapterAnnotation = Optional.fromNullable(field.getAnnotation(EnvAdapterClass.class));

		// Extract the adapter itself (if there is one)
		Optional<? extends EnvAdapter<?, ?>> adapter = getAdapter(adapterAnnotation);

		// Target type in this context is the type we want the conversion service to convert the environment value into.
		// The value that comes out of the environment is the "source" value for the conversion to the specific value on our
		// domain model object
		Class<?> targetType = adapter.isPresent() ? adapter.get().getSourceType() : field.getType();

		// Extract a value from the environment
		Optional<?> value = SpringUtils.getOptionalProperty(env, keys, targetType);

		// nothing to do if there is no value
		if (!value.isPresent()) {
			return;
		}

		// If there is an adapter, use it to convert the value we extracted from the environment into the value we need
		if (adapter.isPresent()) {
			Object result = ReflectionUtils.invokeMethod(adapter.get(), "convert", value.get());
			value = Optional.fromNullable(result);
		}

		// Store the value we have on the object
		set(instance, field, value.orNull());
	}

	private Optional<? extends EnvAdapter<?, ?>> getAdapter(Optional<EnvAdapterClass> annotation) {
		if (annotation.isPresent()) {
			Class<? extends EnvAdapter<?, ?>> adapterClass = annotation.get().value();
			EnvAdapter<?, ?> adapter = ReflectionUtils.newInstance(adapterClass);
			return Optional.of(adapter);
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

	private List<String> getKeys(Optional<String> prefix, Field field) {
		Optional<EnvKeys> optional = Optional.fromNullable(field.getAnnotation(EnvKeys.class));

		List<String> list = new ArrayList<String>();
		if (optional.isPresent() && optional.get().values().length > 0) {
			list.addAll(ImmutableList.copyOf(optional.get().values()));
		} else {
			list.add(field.getName());
		}
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", list);
		} else {
			return list;
		}
	}

	private Optional<String> getPrefix(Env annotation) {
		String prefix = annotation.prefix();
		if (Env.NOPREFIX.equals(prefix)) {
			return Optional.absent();
		} else {
			return Optional.of(prefix);
		}
	}

}

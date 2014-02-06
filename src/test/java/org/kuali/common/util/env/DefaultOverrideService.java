package org.kuali.common.util.env;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.kuali.common.util.Annotations;
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

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class DefaultOverrideService implements OverrideService {

	public EnvironmentService getEnv() {
		return env;
	}

	public boolean isSkip() {
		return skip;
	}

	private final EnvironmentService env;
	private final boolean skip;

	@Override
	public void override(Object instance) {
		Optional<Env> optional = Annotations.get(instance.getClass(), Env.class);
		if (!optional.isPresent()) {
			return;
		}
		Env annotation = optional.get();
		if (annotation.skip()) {
			return;
		}
		Optional<String> prefix = getPrefix(annotation);
		Set<Field> fields = ReflectionUtils.getFields(instance.getClass(), annotation.includeInheritedFields());
		for (Field field : fields) {
			override(prefix, instance, field);
		}
	}

	private void override(Optional<String> prefix, Object instance, Field field) {
		Optional<EnvIgnore> ignore = Annotations.get(instance.getClass(), EnvIgnore.class);
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
		ReflectionUtils.set(instance, field, value.orNull());
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

	private DefaultOverrideService(Builder builder) {
		this.env = builder.env;
		this.skip = builder.skip;
	}

	public static class Builder {

		private EnvironmentService env = new BasicEnvironmentService();
		private boolean skip = false;

		public Builder withEnv(EnvironmentService env) {
			this.env = env;
			return this;
		}

		public Builder withSkip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public DefaultOverrideService build() {
			DefaultOverrideService instance = new DefaultOverrideService(this);
			validate(instance);
			return instance;
		}

		private void validate(DefaultOverrideService instance) {
			Preconditions.checkNotNull(instance.env, "env may not be null");
		}
	}

}

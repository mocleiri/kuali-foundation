package org.kuali.common.util.validate.hibernate.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import org.hibernate.validator.cfg.ConstraintDef;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public final class DefaultConstraintDefService implements ConstraintDefService {

	private final Map<Class<? extends Annotation>, ConstraintDefFactory<? extends ConstraintDef<?, ?>, ?>> factories;

	private DefaultConstraintDefService(Builder builder) {
		this.factories = builder.factories;
	}

	@Override
	public boolean supports(Class<? extends Annotation> annotationType) {
		Preconditions.checkNotNull(annotationType, "'annotationType' cannot be null");
		return factories.containsKey(annotationType);
	}

	@Override
	public ConstraintDef<?, ?> getConstraintDef(Field annotatedField, Class<? extends Annotation> annotationType) {
		Preconditions.checkNotNull(annotatedField, "'annotatedField' cannot be null");
		Preconditions.checkNotNull(annotationType, "'annotationType' cannot be null");
		Preconditions.checkState(supports(annotationType), "[" + annotationType.getCanonicalName() + "] is not supported");
		ConstraintDefFactory<?, ?> factory = factories.get(annotationType);
		return factory.getConstraintDef(annotatedField);
	}

	@Override
	public ConstraintDef<?, ?> getConstraintDef(Class<?> annotatedClass, Class<? extends Annotation> annotationType) {
		Preconditions.checkNotNull(annotatedClass, "'annotatedClass' cannot be null");
		Preconditions.checkNotNull(annotationType, "'annotationType' cannot be null");
		Preconditions.checkState(supports(annotationType), "[" + annotationType.getCanonicalName() + "] is not supported");
		ConstraintDefFactory<?, ?> factory = factories.get(annotationType);
		return factory.getConstraintDef(annotatedClass);
	}

	public static class Builder implements org.kuali.common.util.builder.Builder<DefaultConstraintDefService> {

		private Map<Class<? extends Annotation>, ConstraintDefFactory<? extends ConstraintDef<?, ?>, ?>> factories = Maps.newHashMap();

		public Builder factories(Map<Class<? extends Annotation>, ConstraintDefFactory<? extends ConstraintDef<?, ?>, ?>> factories) {
			this.factories = factories;
			return this;
		}

		public Builder register(ConstraintDefFactory<? extends ConstraintDef<?, ?>, ?> factory) {
			factories.put(factory.getAnnotationType(), factory);
			return this;
		}

		@Override
		public DefaultConstraintDefService build() {
			this.factories = ImmutableMap.copyOf(factories);
			DefaultConstraintDefService instance = new DefaultConstraintDefService(this);
			validate(instance);
			return instance;
		}

		private void validate(DefaultConstraintDefService instance) {
			Preconditions.checkNotNull(instance.getFactories(), "'factories' cannot be null");
			Class<?> mapClass = instance.getFactories().getClass();
			boolean immutable = ReflectionUtils.isImmutableGuavaMap(mapClass);
			Preconditions.checkArgument(immutable, "[%s] is not an immutable Guava map", mapClass.getCanonicalName());
		}
	}

	public Map<Class<? extends Annotation>, ConstraintDefFactory<? extends ConstraintDef<?, ?>, ?>> getFactories() {
		return factories;
	}

}

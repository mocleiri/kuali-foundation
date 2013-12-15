package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.Assert;
import org.kuali.common.util.env.annotation.EnvIgnore;
import org.kuali.common.util.validate.ValidationUtils;

import com.google.common.base.Optional;

public abstract class AbstractBuilder<T> implements Builder<T> {

	public BuilderContext getContext() {
		return context;
	}

	public AbstractBuilder() {
		this(Optional.<BuilderContext> absent());
	}

	public AbstractBuilder(BuilderContext context) {
		this(Optional.of(context));
	}

	public AbstractBuilder(Optional<BuilderContext> context) {
		Assert.notNull(context, "'context' cannot be null");
		if (context.isPresent()) {
			this.context = context.get();
		} else {
			this.context = BuilderContext.builder().build();
		}
	}

	@EnvIgnore
	private final BuilderContext context;

	@Override
	public final T build() {
		context.getOverrider().override(this);
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = context.getValidator().validate(instance);
		ValidationUtils.check(violations);
		return instance;
	}

	protected abstract T getInstance();

}

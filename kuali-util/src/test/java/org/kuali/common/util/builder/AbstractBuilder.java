package org.kuali.common.util.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.Assert;
import org.kuali.common.util.env.annotation.EnvIgnore;
import org.kuali.common.util.validate.ValidatorUtils;

public abstract class AbstractBuilder<T> implements Builder<T> {

	public BuilderContext getContext() {
		return context;
	}

	public AbstractBuilder(BuilderContext context) {
		Assert.notNull(context, "'context' cannot be null");
		this.context = context;
	}

	@EnvIgnore
	private final BuilderContext context;

	@Override
	public final T build() {
		context.getOverrider().override(this);
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = context.getValidator().validate(instance);
		ValidatorUtils.validate(violations);
		return instance;
	}

	protected abstract T getInstance();

}

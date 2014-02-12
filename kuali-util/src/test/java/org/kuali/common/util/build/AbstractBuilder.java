package org.kuali.common.util.build;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.Assert;
import org.kuali.common.util.env.annotation.EnvIgnore;
import org.kuali.common.util.validate.Validation;

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
		Validation.checkViolations(violations);
		return instance;
	}

	protected abstract T getInstance();

}

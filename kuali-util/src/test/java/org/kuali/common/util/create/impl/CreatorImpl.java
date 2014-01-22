package org.kuali.common.util.create.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.kuali.common.util.bind.api.Binder;
import org.kuali.common.util.build.InstanceBuilder;
import org.kuali.common.util.create.Creator;
import org.kuali.common.util.validate.Validation;

public class CreatorImpl implements Creator {

	private final Validator validator;
	private final Binder binder;

	@Override
	public <T> T create(InstanceBuilder<T> builder) {
		List<String> errors = binder.bind(builder);
		checkState(errors.size() == 0, "There were %s binding errors", errors.size());
		T instance = builder.getInstance();
		Set<ConstraintViolation<T>> violations = validator.validate(instance);
		Validation.check(violations);
		return instance;
	}

	private CreatorImpl(Builder builder) {
		this.validator = builder.validator;
		this.binder = builder.binder;
	}

	public static CreatorImpl create(Validator validator, Binder binder) {
		return builder().validator(validator).binder(binder).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private Validator validator;
		private Binder binder;

		public Builder validator(Validator validator) {
			this.validator = validator;
			return this;
		}

		public Builder binder(Binder binder) {
			this.binder = binder;
			return this;
		}

		public CreatorImpl build() {
			CreatorImpl instance = new CreatorImpl(this);
			validate(instance);
			return instance;
		}

		private static void validate(CreatorImpl instance) {
			checkNotNull(instance.validator, "'validator' cannot be null");
			checkNotNull(instance.binder, "'binder' cannot be null");
		}
	}

}

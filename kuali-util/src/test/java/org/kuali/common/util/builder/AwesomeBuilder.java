package org.kuali.common.util.builder;

import static com.google.common.base.Preconditions.checkState;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Binding;
import org.kuali.common.util.validate.Validation;
import org.springframework.validation.BindingResult;

public abstract class AwesomeBuilder<T> implements Builder<T> {

	protected abstract T getInstance();

	protected Validator validator = Validation.getDefaultValidator();
	protected BinderService binder = Binding.getDefaultBinderService();

	@Override
	public final T build() {
		BindingResult result = binder.bind(this);
		checkState(!result.hasErrors(), "Binding failed with %s errors", result.getAllErrors().size());
		T instance = getInstance();
		Set<ConstraintViolation<T>> violations = validate(instance);
		Validation.check(violations);
		return instance;
	}

	public Set<ConstraintViolation<T>> validate(T instance) {
		return validator.validate(instance);
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public BinderService getBinder() {
		return binder;
	}

	public void setBinder(BinderService binder) {
		this.binder = binder;
	}

}

package org.kuali.common.util.validate;

import org.springframework.util.Assert;

import com.google.common.base.Optional;

public class NoNullsValidator implements Validator {

	@Override
	public Optional<Errors> validate(Object instance) {
		Assert.notNull(instance, "'instance' cannot be null");
		return null;
	}

}

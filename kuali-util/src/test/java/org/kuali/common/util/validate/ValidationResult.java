package org.kuali.common.util.validate;

import java.util.List;

import org.springframework.util.Assert;

import com.google.common.collect.ImmutableList;

public final class ValidationResult {

	public boolean isHasErrors() {
		return hasErrors;
	}

	public ValidationResult() {
		this(ImmutableList.<String> of());
	}

	public ValidationResult(List<String> errorMessages) {
		Assert.notNull(errorMessages, "'errorMessages' cannot be null");
		this.errorMessages = ImmutableList.copyOf(errorMessages);
		this.hasErrors = errorMessages.size() > 0;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	private final List<String> errorMessages;
	private final boolean hasErrors;

}

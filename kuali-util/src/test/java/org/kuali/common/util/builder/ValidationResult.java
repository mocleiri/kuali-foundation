package org.kuali.common.util.builder;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class ValidationResult {

	public boolean isValid() {
		return valid;
	}

	public Optional<String> getMessage() {
		return message;
	}

	private final Optional<String> message;
	private final boolean valid;

	public ValidationResult(Optional<String> message) {
		Assert.notNull(message, "message is null");
		if (message.isPresent()) {
			Assert.noBlanksWithMsg("message content is blank", message.get());
		}
		this.message = message;
		this.valid = !message.isPresent();
	}

}

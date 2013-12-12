package org.kuali.common.util.validate;

import java.util.List;

import org.springframework.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Errors {

	public Errors(List<String> messages) {
		Assert.notNull(messages, "'messages' cannot be null");
		this.messages = ImmutableList.copyOf(messages);
	}

	public List<String> getMessages() {
		return messages;
	}

	private final List<String> messages;

}

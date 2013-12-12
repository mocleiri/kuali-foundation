package org.kuali.common.util.validate;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public final class Errors {

	private final List<String> messages;
	private final int count;

	private Errors(Builder builder) {
		this.messages = builder.messages;
		this.count = builder.count;
	}

	public static class Builder {

		private List<String> messages = new ArrayList<String>();
		private int count;

		public Builder reject(String message) {
			messages.add(message);
			return this;
		}

		public Builder withMessages(List<String> messages) {
			this.messages = messages;
			return this;
		}

		public Errors build() {
			this.messages = ImmutableList.copyOf(messages);
			this.count = messages.size();
			Errors instance = new Errors(this);
			validate(instance);
			return instance;
		}

		private void validate(Errors instance) {
			Preconditions.checkArgument(instance.messages != null, "'messages' cannot be null");
			Preconditions.checkArgument(instance.count == messages.size(), "count must always equal size");
		}
	}

}

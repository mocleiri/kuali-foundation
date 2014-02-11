package org.kuali.common.devops.metadata.model;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class EC2Tag {

	private final String key;
	private final String value;

	private EC2Tag(Builder builder) {
		this.key = builder.key;
		this.value = builder.value;
	}

	public static class Builder extends ValidatingBuilder<EC2Tag> {

		private String key;
		private String value;

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder value(String value) {
			this.value = value;
			return this;
		}

		@Override
		public EC2Tag getInstance() {
			return new EC2Tag(this);
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}

package org.kuali.common.devops.ci;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.aws.Tags;

@IdiotProofImmutable
public final class JenkinsContext {

	private final String dnsPrefix;
	private final Tags.Name name;
	private final Tags.Stack stack;

	private JenkinsContext(Builder builder) {
		this.dnsPrefix = builder.dnsPrefix;
		this.name = builder.name;
		this.stack = builder.stack;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JenkinsContext> {

		private String dnsPrefix;
		private Tags.Name name;
		private Tags.Stack stack;

		public Builder withDnsPrefix(String dnsPrefix) {
			this.dnsPrefix = dnsPrefix;
			return this;
		}

		public Builder withName(Tags.Name name) {
			this.name = name;
			return this;
		}

		public Builder withStack(Tags.Stack stack) {
			this.stack = stack;
			return this;
		}

		@Override
		public JenkinsContext build() {
			return validate(new JenkinsContext(this));
		}

		public String getDnsPrefix() {
			return dnsPrefix;
		}

		public void setDnsPrefix(String dnsPrefix) {
			this.dnsPrefix = dnsPrefix;
		}

		public Tags.Name getName() {
			return name;
		}

		public void setName(Tags.Name name) {
			this.name = name;
		}

		public Tags.Stack getStack() {
			return stack;
		}

		public void setStack(Tags.Stack stack) {
			this.stack = stack;
		}
	}

	public String getDnsPrefix() {
		return dnsPrefix;
	}

	public Tags.Name getName() {
		return name;
	}

	public Tags.Stack getStack() {
		return stack;
	}

}

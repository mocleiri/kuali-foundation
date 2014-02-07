package org.kuali.common.devops.model;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Account {

	private final String name;
	private final List<EC2Instance> instances;

	private Account(Builder builder) {
		this.name = builder.name;
		this.instances = builder.instances;
	}

	public static class Builder extends ValidatingBuilder<Account> {

		private String name;
		private List<EC2Instance> instances = ImmutableList.of();

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder instances(List<EC2Instance> instances) {
			this.instances = instances;
			return this;
		}

		@Override
		public Account getInstance() {
			return new Account(this);
		}

	}

}

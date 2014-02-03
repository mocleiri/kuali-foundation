package org.kuali.common.devops.model;

import java.util.List;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Group {

	private final String name;
	private final ImmutableList<Environment> environments;

	private Group(Builder builder) {
		this.name = builder.name;
		this.environments = ImmutableList.copyOf(builder.environments);
	}

	public static Group create(String name, List<Environment> environments) {
		return builder().name(name).environments(environments).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Group> {

		private String name;
		private List<Environment> environments;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder environments(List<Environment> environments) {
			this.environments = environments;
			return this;
		}

		@Override
		public Group getInstance() {
			return new Group(this);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Environment> getEnvironments() {
			return environments;
		}

		public void setEnvironments(List<Environment> environments) {
			this.environments = environments;
		}
	}

	public String getName() {
		return name;
	}

	public List<Environment> getEnvironments() {
		return environments;
	}
}

package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public final class Group {

	private final String name;
	private final List<Environment> environments;

	private Group(Builder builder) {
		this.name = builder.name;
		this.environments = builder.environments;
	}

	public static class Builder implements org.kuali.common.util.build.Builder<Group> {

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
		public Group build() {
			Group instance = new Group(this);
			validate(instance);
			return instance;
		}

		private static void validate(Group instance) {
			checkArgument(!StringUtils.isBlank(instance.name), "'name' cannot be blank");
			checkNotNull(instance.environments, "'environments' cannot be null");
		}
	}
}

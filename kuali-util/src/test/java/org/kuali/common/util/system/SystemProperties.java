package org.kuali.common.util.system;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class SystemProperties {

	private final User user;

	private SystemProperties(Builder builder) {
		this.user = builder.user;
	}

	public static class Builder extends AwesomeBuilder<SystemProperties> {

		private User user = User.create();

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		@Override
		public SystemProperties getInstance() {
			return new SystemProperties(this);
		}

	}

	public User getUser() {
		return user;
	}

}

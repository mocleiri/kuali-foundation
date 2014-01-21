package org.kuali.common.util.system;

import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Sys {

	private final User user;

	private Sys(Builder builder) {
		this.user = builder.user;
	}

	@Bound(prefix = false)
	public static class Builder extends AwesomeBuilder<Sys> {

		private User user = User.create();

		@Override
		public Sys getInstance() {
			return new Sys(this);
		}

	}

	public User getUser() {
		return user;
	}

}

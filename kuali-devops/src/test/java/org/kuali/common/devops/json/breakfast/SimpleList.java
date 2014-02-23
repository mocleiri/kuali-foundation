package org.kuali.common.devops.json.breakfast;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class SimpleList {

	private final ImmutableList<String> names;

	private SimpleList(Builder builder) {
		this.names = ImmutableList.copyOf(builder.names);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<SimpleList> {

		private List<String> names;

		@Override
		public SimpleList build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<SimpleList>> violations() {
			return violations(make());
		}

		private SimpleList make() {
			return new SimpleList(this);
		}

		public Builder names(List<String> names) {
			this.names = names;
			return this;
		}

		public List<String> getNames() {
			return names;
		}

		public void setNames(List<String> names) {
			this.names = names;
		}

	}

	public List<String> getNames() {
		return names;
	}

}

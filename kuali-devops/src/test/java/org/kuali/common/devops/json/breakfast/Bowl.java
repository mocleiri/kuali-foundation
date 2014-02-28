package org.kuali.common.devops.json.breakfast;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.build.ViolationsBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Bowl {

	private final double depth;
	private final double width;

	private Bowl(Builder builder) {
		this.depth = builder.depth;
		this.width = builder.width;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ViolationsBuilder<Bowl> {

		private double depth;
		private double width;

		@Override
		public Bowl build() {
			return validate(make());
		}

		@Override
		public Set<ConstraintViolation<Bowl>> violations() {
			return violations(make());
		}

		private Bowl make() {
			return new Bowl(this);
		}

		public Builder depth(double depth) {
			this.depth = depth;
			return this;
		}

		public Builder width(double width) {
			this.width = width;
			return this;
		}

		public double getDepth() {
			return depth;
		}

		public void setDepth(double depth) {
			this.depth = depth;
		}

		public double getWidth() {
			return width;
		}

		public void setWidth(double width) {
			this.width = width;
		}

	}

	public double getDepth() {
		return depth;
	}

	public double getWidth() {
		return width;
	}

}

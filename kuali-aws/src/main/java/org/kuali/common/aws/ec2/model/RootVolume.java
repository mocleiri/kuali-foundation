package org.kuali.common.aws.ec2.model;

import static com.google.common.base.Optional.absent;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class RootVolume {

	private final Optional<Integer> sizeInGigabytes;
	private final Optional<Boolean> deleteOnTermination;

	private RootVolume(Builder builder) {
		this.sizeInGigabytes = builder.sizeInGigabytes;
		this.deleteOnTermination = builder.deleteOnTermination;
	}

	public static class Builder extends ValidatingBuilder<RootVolume> {

		private Optional<Integer> sizeInGigabytes = absent();
		private Optional<Boolean> deleteOnTermination = absent();

		public Builder withSizeInGigabytes(Optional<Integer> sizeInGigabytes) {
			this.sizeInGigabytes = sizeInGigabytes;
			return this;
		}

		public Builder withDeleteOnTermination(Optional<Boolean> deleteOnTermination) {
			this.deleteOnTermination = deleteOnTermination;
			return this;
		}

		@Override
		public RootVolume build() {
			return validate(new RootVolume(this));
		}
	}

	public Optional<Integer> getSizeInGigabytes() {
		return sizeInGigabytes;
	}

	public Optional<Boolean> getDeleteOnTermination() {
		return deleteOnTermination;
	}

}
package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class RootVolume {

	private final Optional<Integer> sizeInGigabytes;
	private final Optional<Boolean> deleteOnTermination;

	public Optional<Integer> getSizeInGigabytes() {
		return sizeInGigabytes;
	}

	public Optional<Boolean> getDeleteOnTermination() {
		return deleteOnTermination;
	}

	private RootVolume(Builder builder) {
		this.sizeInGigabytes = builder.sizeInGigabytes;
		this.deleteOnTermination = builder.deleteOnTermination;
	}

	public static class Builder {

		// Optional
		private Optional<Integer> sizeInGigabytes = Optional.absent();
		private Optional<Boolean> deleteOnTermination = Optional.absent();

		public Builder(Optional<Integer> sizeInGigabytes, Optional<Boolean> deleteOnTermination) {
			this.sizeInGigabytes = sizeInGigabytes;
			this.deleteOnTermination = deleteOnTermination;
		}

		public Builder sizeInGigabytes(int sizeInGigabytes) {
			this.sizeInGigabytes = Optional.of(sizeInGigabytes);
			return this;
		}

		public Builder deleteOnTermination(boolean deleteOnTermination) {
			this.deleteOnTermination = Optional.of(deleteOnTermination);
			return this;
		}

		private void validate(RootVolume instance) {
			Assert.noNulls(instance.getDeleteOnTermination(), instance.getSizeInGigabytes());
		}

		public RootVolume build() {
			RootVolume instance = new RootVolume(this);
			validate(instance);
			return instance;
		}
	}

}
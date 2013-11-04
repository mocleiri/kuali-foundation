package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class RootVolume {

	public RootVolume() {
		this(Optional.<Integer> absent(), Optional.<Boolean> absent());
	}

	public RootVolume(int sizeInGigabytes) {
		this(Optional.of(sizeInGigabytes), Optional.<Boolean> absent());
	}

	public RootVolume(boolean deleteOnTermination) {
		this(Optional.<Integer> absent(), Optional.of(deleteOnTermination));
	}

	public RootVolume(int sizeInGigabytes, boolean deleteOnTermination) {
		this(Optional.of(sizeInGigabytes), Optional.of(deleteOnTermination));
	}

	public RootVolume(Optional<Integer> sizeInGigabytes, Optional<Boolean> deleteOnTermination) {
		Assert.noNulls(sizeInGigabytes);
		if (sizeInGigabytes.isPresent()) {
			Assert.positive(sizeInGigabytes.get());
		}
		this.sizeInGigabytes = sizeInGigabytes;
		this.deleteOnTermination = deleteOnTermination;
	}

	private final Optional<Integer> sizeInGigabytes;
	private final Optional<Boolean> deleteOnTermination;

	public Optional<Integer> getSizeInGigabytes() {
		return sizeInGigabytes;
	}

	public Optional<Boolean> getDeleteOnTermination() {
		return deleteOnTermination;
	}

}

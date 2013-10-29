package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class RootVolume {

	// Default is to delete the root volume when its associated instance is terminated
	private static final boolean DEFAULT_DELETE_ON_TERMINATION = true;

	public RootVolume() {
		this(DEFAULT_DELETE_ON_TERMINATION);
	}

	public RootVolume(int sizeInGigabytes) {
		this(sizeInGigabytes, DEFAULT_DELETE_ON_TERMINATION);
	}

	public RootVolume(boolean deleteOnTermination) {
		this(Optional.<Integer> absent(), deleteOnTermination);
	}

	public RootVolume(int sizeInGigabytes, boolean deleteOnTermination) {
		this(Optional.of(sizeInGigabytes), deleteOnTermination);
	}

	public RootVolume(Optional<Integer> sizeInGigabytes, boolean deleteOnTermination) {
		if (sizeInGigabytes.isPresent()) {
			Assert.positive(sizeInGigabytes.get());
		}
		this.sizeInGigabytes = sizeInGigabytes;
		this.deleteOnTermination = deleteOnTermination;
	}

	private final Optional<Integer> sizeInGigabytes;
	private final boolean deleteOnTermination;

	public Optional<Integer> getSizeInGigabytes() {
		return sizeInGigabytes;
	}

	public boolean isDeleteOnTermination() {
		return deleteOnTermination;
	}

}

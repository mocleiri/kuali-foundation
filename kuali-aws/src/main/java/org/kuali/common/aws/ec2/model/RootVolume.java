package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class RootVolume {

	// Default is to delete the root volume when its associated instance is terminated
	public static final boolean DEFAULT_DELETE_ON_TERMINATION = true;

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

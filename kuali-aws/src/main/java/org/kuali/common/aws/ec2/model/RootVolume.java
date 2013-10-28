package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public final class RootVolume {

	public static final boolean DEFAULT_DELETE_ON_TERMINATION = true;

	public RootVolume(int sizeInGigabytes) {
		this(sizeInGigabytes, DEFAULT_DELETE_ON_TERMINATION);
	}

	public RootVolume(int sizeInGigabytes, boolean deleteOnTermination) {
		Assert.positive(sizeInGigabytes);
		this.sizeInGigabytes = sizeInGigabytes;
		this.deleteOnTermination = deleteOnTermination;
	}

	private final int sizeInGigabytes;
	private final boolean deleteOnTermination;

	public int getSizeInGigabytes() {
		return sizeInGigabytes;
	}

	public boolean isDeleteOnTermination() {
		return deleteOnTermination;
	}

}

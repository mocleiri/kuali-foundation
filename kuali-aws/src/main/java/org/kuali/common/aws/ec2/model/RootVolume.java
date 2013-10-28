package org.kuali.common.aws.ec2.model;

public final class RootVolume {

	public RootVolume(int sizeInGigabytes, boolean deleteOnTermination) {
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

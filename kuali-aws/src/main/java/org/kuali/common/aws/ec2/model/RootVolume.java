package org.kuali.common.aws.ec2.model;

public final class RootVolume {

	public RootVolume(int size, boolean deleteOnTermination) {
		this.size = size;
		this.deleteOnTermination = deleteOnTermination;
	}

	private final int size;
	private final boolean deleteOnTermination;

	public int getSize() {
		return size;
	}

	public boolean isDeleteOnTermination() {
		return deleteOnTermination;
	}

}

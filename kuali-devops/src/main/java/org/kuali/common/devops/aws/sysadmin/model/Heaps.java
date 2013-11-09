package org.kuali.common.devops.aws.sysadmin.model;

import static org.kuali.common.util.FormatUtils.getBytes;

import org.kuali.common.util.Assert;

import com.amazonaws.services.ec2.model.InstanceType;

public enum Heaps {

	M1_MEDIUM(InstanceType.M1Medium, "512m", "2g"), //
	M1_LARGE(InstanceType.M1Large, "1g", "5g"), //
	M1_XLARGE(InstanceType.M1Xlarge, "1g", "12g"); //

	private final Heap heap;
	private final InstanceType type;

	private Heaps(InstanceType type, String maxPerm, String max) {
		this(type, max, max, maxPerm);
	}

	private Heaps(InstanceType type, String maxPerm, String min, String max) {
		Assert.noBlanks(min, max, maxPerm);
		Assert.noNulls(type);
		this.type = type;
		this.heap = new Heap.Builder().maxSizeInBytes(getBytes(max)).minSizeInBytes(getBytes(min)).maxPermSizeInBytes(getBytes(maxPerm)).build();
	}

}

package org.kuali.common.devops.aws.sysadmin.model;

import static org.kuali.common.util.FormatUtils.getBytes;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.util.Assert;

import com.amazonaws.services.ec2.model.InstanceType;

public enum Heaps {

	M1_MEDIUM(InstanceType.M1Medium, "512m", "2g"), //
	M1_LARGE(InstanceType.M1Large, "1g", "5g"), //
	M1_XLARGE(InstanceType.M1Xlarge, "1g", "12g"); //

	private final Heap heap;
	private final InstanceType type;

	private Heaps(InstanceType type, String maxPerm, String max) {
		Assert.noBlanks(maxPerm, max);
		Assert.noNulls(type);
		this.type = type;
		this.heap = new Heap.Builder().maxSizeInBytes(getBytes(max)).minSizeInBytes(getBytes(max)).maxPermSizeInBytes(getBytes(maxPerm)).build();
	}

	public Heap getHeap() {
		return heap;
	}

	public InstanceType getType() {
		return type;
	}

	public static final Map<String, Heap> asMap() {
		Map<String, Heap> map = new HashMap<String, Heap>();
		for (Heaps value : values()) {
			map.put(value.getType().toString(), value.getHeap());
		}
		return map;
	}

	public static final Map<InstanceType, Heap> asTypeMap() {
		Map<InstanceType, Heap> map = new HashMap<InstanceType, Heap>();
		for (Heaps value : values()) {
			map.put(value.getType(), value.getHeap());
		}
		return map;
	}

}

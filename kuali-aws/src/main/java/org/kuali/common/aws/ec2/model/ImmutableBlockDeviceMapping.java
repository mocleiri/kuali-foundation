/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.ec2.model;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class ImmutableBlockDeviceMapping extends BlockDeviceMapping {

	private static final long serialVersionUID = 1L;
	private static final String UOE_MSG = "Not supported for immutable block device mappings";

	/**
	 * The semi-magic device names come from the AWS console when launching an Ubuntu 12.04 LTS image
	 * 
	 * The semi-magic virtual names come from the blog Mike found:<br>
	 * http://theagileadmin.com/2010/03/23/amazon-ec2-ebs-instances-and-ephemeral-storage/
	 */
	public static final ImmutableBlockDeviceMapping INSTANCE_STORE_0 = new ImmutableBlockDeviceMapping("/dev/sdb", "ephemeral0");
	public static final ImmutableBlockDeviceMapping INSTANCE_STORE_1 = new ImmutableBlockDeviceMapping("/dev/sdc", "ephemeral1");
	public static final ImmutableList<BlockDeviceMapping> DEFAULT_INSTANCE_STORES = ImmutableList.<BlockDeviceMapping> of(copyOf(INSTANCE_STORE_0), copyOf(INSTANCE_STORE_1));

	public static ImmutableList<BlockDeviceMapping> copyOf(List<BlockDeviceMapping> mappings) {
		List<BlockDeviceMapping> list = newArrayList();
		for (BlockDeviceMapping mapping : mappings) {
			list.add(copyOf(mapping));
		}
		return ImmutableList.copyOf(list);
	}

	public static ImmutableBlockDeviceMapping copyOf(BlockDeviceMapping mapping) {
		if (mapping instanceof ImmutableBlockDeviceMapping) {
			return (ImmutableBlockDeviceMapping) mapping;
		}
		String deviceName = mapping.getDeviceName();
		Optional<EbsBlockDevice> ebs = getEbs(mapping);
		Optional<String> virtualName = fromNullable(trimToNull(mapping.getVirtualName()));
		Optional<String> noDevice = fromNullable(trimToNull(mapping.getNoDevice()));
		return new ImmutableBlockDeviceMapping(deviceName, ebs, virtualName, noDevice);
	}

	public ImmutableBlockDeviceMapping(String deviceName, EbsBlockDevice ebs) {
		this(deviceName, Optional.of(ebs), Optional.<String> absent(), Optional.<String> absent());
	}

	public ImmutableBlockDeviceMapping(String deviceName, String virtualName) {
		this(deviceName, Optional.<EbsBlockDevice> absent(), Optional.of(virtualName), Optional.<String> absent());
	}

	public ImmutableBlockDeviceMapping(String deviceName, Optional<EbsBlockDevice> ebs, Optional<String> virtualName, Optional<String> noDevice) {
		super.setDeviceName(checkNotBlank(deviceName, "deviceName"));
		super.setEbs(checkNotNull(ebs, "ebs").isPresent() ? ImmutableEbsBlockDevice.copyOf(ebs.get()) : null);
		super.setVirtualName(checkNotBlank(virtualName, "virtualName").orNull());
		super.setNoDevice(checkNotBlank(noDevice, "noDevice").orNull());
	}

	@Override
	public void setVirtualName(String virtualName) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public BlockDeviceMapping withVirtualName(String virtualName) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setDeviceName(String deviceName) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public BlockDeviceMapping withDeviceName(String deviceName) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setEbs(EbsBlockDevice ebs) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public BlockDeviceMapping withEbs(EbsBlockDevice ebs) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setNoDevice(String noDevice) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public BlockDeviceMapping withNoDevice(String noDevice) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	public static Optional<EbsBlockDevice> getEbs(BlockDeviceMapping mapping) {
		if (mapping.getEbs() == null) {
			return absent();
		} else {
			return Optional.<EbsBlockDevice> of(ImmutableEbsBlockDevice.copyOf(mapping.getEbs()));
		}
	}

}

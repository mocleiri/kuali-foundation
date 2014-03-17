package org.kuali.common.aws.ec2.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.google.common.base.Optional;

public final class ImmutableBlockDeviceMapping extends BlockDeviceMapping {

	private static final long serialVersionUID = 1L;
	private static final String UOE_MSG = "Not supported for immutable block device mappings";

	public static ImmutableBlockDeviceMapping copyOf(BlockDeviceMapping mapping) {
		String deviceName = mapping.getDeviceName();
		ImmutableEbsBlockDevice ebs = ImmutableEbsBlockDevice.copyOf(mapping.getEbs());
		Optional<String> virtualName = Optional.fromNullable(mapping.getVirtualName());
		Optional<String> noDevice = Optional.fromNullable(mapping.getNoDevice());
		return new ImmutableBlockDeviceMapping(deviceName, ebs, virtualName, noDevice);
	}

	public ImmutableBlockDeviceMapping(String deviceName, ImmutableEbsBlockDevice ebs, Optional<String> virtualName, Optional<String> noDevice) {
		super.setDeviceName(checkNotBlank(deviceName, "deviceName"));
		super.setEbs(checkNotNull(ebs, "ebs"));
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

}

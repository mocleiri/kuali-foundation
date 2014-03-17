package org.kuali.common.aws.ec2.model;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static org.kuali.common.util.base.Precondition.checkMax;
import static org.kuali.common.util.base.Precondition.checkMin;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.VolumeType;
import com.google.common.base.Optional;

public final class ImmutableEbsBlockDevice extends EbsBlockDevice {

	private static final long serialVersionUID = 1L;
	private static final String UOE_MSG = "Not supported for immutable ebs block devices";

	private ImmutableEbsBlockDevice(Builder builder) {
		super.setSnapshotId(builder.snapshotId);
		super.setVolumeType(builder.volumeType);
		super.setDeleteOnTermination(builder.deleteOnTermination);
		super.setIops(builder.iops.orNull());
		super.setVolumeSize(builder.volumeSizeInGigabytes.orNull());
	}

	public static ImmutableEbsBlockDevice copyOf(EbsBlockDevice ebs) {
		Builder builder = builder(ebs.getSnapshotId());
		if (ebs.getVolumeType() != null) {
			builder.withVolumeType(VolumeType.fromValue(ebs.getVolumeType()));
		}
		if (ebs.getDeleteOnTermination() != null) {
			builder.withDeleteOnTermination(ebs.getDeleteOnTermination());
		}
		if (ebs.getIops() != null) {
			builder.withIops(ebs.getIops());
		}
		if (ebs.getVolumeSize() != null) {
			builder.withVolumeSizeInGigabytes(ebs.getVolumeSize());
		}
		return builder.build();
	}

	public static Builder builder(String snapshotId) {
		return new Builder(snapshotId);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<ImmutableEbsBlockDevice> {

		private static final int IOPS_MIN = 100;
		private static final int IOPS_MIN_VOLUME_SIZE = 10;
		private static final int IOPS_MAX = 4000;

		private final String snapshotId;
		private VolumeType volumeType = VolumeType.Standard;
		private boolean deleteOnTermination = true;
		private Optional<Integer> iops = absent();
		private Optional<Integer> volumeSizeInGigabytes = absent();

		public Builder(String snapshotId) {
			this.snapshotId = snapshotId;
		}

		public Builder withDeleteOnTermination(boolean deleteOnTermination) {
			this.deleteOnTermination = deleteOnTermination;
			return this;
		}

		public Builder withVolumeType(VolumeType volumeType) {
			this.volumeType = volumeType;
			return this;
		}

		public Builder withIops(Optional<Integer> iops) {
			this.iops = iops;
			return this;
		}

		public Builder withIops(int iops) {
			return withIops(Optional.of(iops));
		}

		public Builder withVolumeSizeInGigabytes(Optional<Integer> volumeSizeInGigabytes) {
			this.volumeSizeInGigabytes = volumeSizeInGigabytes;
			return this;
		}

		public Builder withVolumeSizeInGigabytes(int volumeSizeInGigabytes) {
			return withVolumeSizeInGigabytes(volumeSizeInGigabytes);
		}

		@Override
		public ImmutableEbsBlockDevice build() {
			return validate(new ImmutableEbsBlockDevice(this));
		}

		private static ImmutableEbsBlockDevice validate(ImmutableEbsBlockDevice instance) {
			checkNotBlank(instance.getSnapshotId(), "snapshotId");
			checkNotBlank(instance.getVolumeType(), "volumeType");
			VolumeType vt = VolumeType.fromValue(instance.getVolumeType());
			if (vt.equals(VolumeType.Io1)) {
				Optional<Integer> iops = fromNullable(instance.getIops());
				Optional<Integer> volumeSize = fromNullable(instance.getVolumeSize());
				checkArgument(iops.isPresent(), "iops value is required for volume type %s", vt.toString());
				checkArgument(volumeSize.isPresent(), "volumeSize value is required for volume type %s", vt.toString());
				checkMin(iops, IOPS_MIN, "iops");
				checkMax(iops.get(), IOPS_MAX, "iops");
				checkMin(volumeSize, IOPS_MIN_VOLUME_SIZE, "volumeSize");
			}
			return instance;
		}

	}

	@Override
	public void setSnapshotId(String snapshotId) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public EbsBlockDevice withSnapshotId(String snapshotId) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setVolumeSize(Integer volumeSize) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public EbsBlockDevice withVolumeSize(Integer volumeSize) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setDeleteOnTermination(Boolean deleteOnTermination) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public EbsBlockDevice withDeleteOnTermination(Boolean deleteOnTermination) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setVolumeType(String volumeType) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public EbsBlockDevice withVolumeType(String volumeType) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setVolumeType(VolumeType volumeType) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public EbsBlockDevice withVolumeType(VolumeType volumeType) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public void setIops(Integer iops) {
		throw new UnsupportedOperationException(UOE_MSG);
	}

	@Override
	public EbsBlockDevice withIops(Integer iops) {
		throw new UnsupportedOperationException(UOE_MSG);
	}
}

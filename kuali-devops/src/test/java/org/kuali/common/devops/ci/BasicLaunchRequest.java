package org.kuali.common.devops.ci;

import static org.kuali.common.aws.ec2.model.AMI.UBUNTU_64_BIT_PRECISE_LTS_1204;
import static org.kuali.common.util.FormatUtils.getMillisAsInt;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.amazonaws.services.ec2.model.InstanceType;

@IdiotProofImmutable
public final class BasicLaunchRequest {

	private final String ami;
	private final InstanceType type;
	private final int rootVolumeSize;
	private final int timeoutMillis;

	private BasicLaunchRequest(Builder builder) {
		this.ami = builder.ami;
		this.type = builder.type;
		this.rootVolumeSize = builder.rootVolumeSize;
		this.timeoutMillis = builder.timeoutMillis;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<BasicLaunchRequest> {

		private String ami = UBUNTU_64_BIT_PRECISE_LTS_1204.getId();
		private InstanceType type = InstanceType.C3Xlarge;
		private int rootVolumeSize = 32;
		private int timeoutMillis = getMillisAsInt("15m");

		public Builder withAmi(String ami) {
			this.ami = ami;
			return this;
		}

		public Builder withType(InstanceType type) {
			this.type = type;
			return this;
		}

		public Builder withRootVolumeSize(int rootVolumeSize) {
			this.rootVolumeSize = rootVolumeSize;
			return this;
		}

		public Builder withTimeoutMillis(int timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
			return this;
		}

		@Override
		public BasicLaunchRequest build() {
			return validate(new BasicLaunchRequest(this));
		}

		public String getAmi() {
			return ami;
		}

		public void setAmi(String ami) {
			this.ami = ami;
		}

		public InstanceType getType() {
			return type;
		}

		public void setType(InstanceType type) {
			this.type = type;
		}

		public int getTimeoutMillis() {
			return timeoutMillis;
		}

		public void setTimeoutMillis(int timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
		}

		public int getRootVolumeSize() {
			return rootVolumeSize;
		}

		public void setRootVolumeSize(int rootVolumeSize) {
			this.rootVolumeSize = rootVolumeSize;
		}
	}

	public String getAmi() {
		return ami;
	}

	public InstanceType getType() {
		return type;
	}

	public int getSizeInGigabytes() {
		return rootVolumeSize;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

}

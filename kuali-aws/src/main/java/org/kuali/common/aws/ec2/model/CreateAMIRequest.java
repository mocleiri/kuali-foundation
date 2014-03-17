package org.kuali.common.aws.ec2.model;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class CreateAMIRequest {

	private final String instanceId;
	private final ImmutableTag name;
	private final String description;
	private final RootVolume rootVolume;
	private final int timeoutMillis;
	private final ImmutableList<BlockDeviceMapping> mappings;

	private CreateAMIRequest(Builder builder) {
		this.instanceId = builder.instanceId;
		this.name = builder.name;
		this.description = builder.description;
		this.rootVolume = builder.rootVolume;
		this.timeoutMillis = builder.timeoutMillis;
		List<BlockDeviceMapping> immutables = newArrayList();
		for (BlockDeviceMapping mapping : builder.mappings) {
			immutables.add(ImmutableBlockDeviceMapping.copyOf(mapping));
		}
		this.mappings = ImmutableList.copyOf(immutables);
	}

	public static class Builder extends ValidatingBuilder<CreateAMIRequest> {

		private String instanceId;
		private ImmutableTag name;
		private String description;
		private RootVolume rootVolume;
		private int timeoutMillis;
		private List<BlockDeviceMapping> mappings;

		public Builder withInstanceId(String instanceId) {
			this.instanceId = instanceId;
			return this;
		}

		public Builder withName(ImmutableTag name) {
			this.name = name;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withRootVolume(RootVolume rootVolume) {
			this.rootVolume = rootVolume;
			return this;
		}

		public Builder withTimeoutMillis(int timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
			return this;
		}

		public Builder withMappings(List<BlockDeviceMapping> mappings) {
			this.mappings = mappings;
			return this;
		}

		@Override
		public CreateAMIRequest build() {
			return validate(new CreateAMIRequest(this));
		}
	}

	public String getInstanceId() {
		return instanceId;
	}

	public ImmutableTag getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public RootVolume getRootVolume() {
		return rootVolume;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

	public List<BlockDeviceMapping> getMappings() {
		return mappings;
	}

}

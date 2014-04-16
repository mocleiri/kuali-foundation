package org.kuali.common.devops.ci;

import org.kuali.common.aws.ec2.model.ImmutableTag;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.aws.Tags;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.ec2.model.Tag;

@IdiotProofImmutable
public final class JenkinsCloneContext {

	private final Region region;
	private final ImmutableTag srcStack;
	private final ImmutableTag dstStack;

	private JenkinsCloneContext(Builder builder) {
		this.region = builder.region;
		this.srcStack = ImmutableTag.copyOf(builder.srcStack);
		this.dstStack = ImmutableTag.copyOf(builder.dstStack);
	}

	public static class Builder extends ValidatingBuilder<JenkinsCloneContext> {

		private Region region = RegionUtils.getRegion("us-west-1");
		private Tag srcStack = Tags.Stack.TEST.getTag();
		private Tag dstStack = Tags.Stack.PRODUCTION.getTag();

		public Builder withRegion(String region) {
			this.region = RegionUtils.getRegion(region);
			return this;
		}

		public Builder withSrcStack(ImmutableTag srcStack) {
			this.srcStack = srcStack;
			return this;
		}

		public Builder withDstStack(ImmutableTag dstStack) {
			this.dstStack = dstStack;
			return this;
		}

		@Override
		public JenkinsCloneContext build() {
			return validate(new JenkinsCloneContext(this));
		}
	}

	public Region getRegion() {
		return region;
	}

	public Tag getSrcStack() {
		return srcStack;
	}

	public Tag getDstStack() {
		return dstStack;
	}

}

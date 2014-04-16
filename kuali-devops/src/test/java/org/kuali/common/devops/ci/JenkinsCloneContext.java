package org.kuali.common.devops.ci;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.Tags.Stack;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;

@IdiotProofImmutable
public final class JenkinsCloneContext {

	private final Region region;
	private final Stack srcStack;
	private final Stack dstStack;

	private JenkinsCloneContext(Builder builder) {
		this.region = builder.region;
		this.srcStack = builder.srcStack;
		this.dstStack = builder.dstStack;
	}

	public static class Builder extends ValidatingBuilder<JenkinsCloneContext> {

		private Region region = RegionUtils.getRegion("us-west-1");
		private Stack srcStack = Tags.Stack.TEST;
		private Stack dstStack = Tags.Stack.PRODUCTION;

		public Builder withRegion(String region) {
			this.region = RegionUtils.getRegion(region);
			return this;
		}

		public Builder withSrcStack(Stack srcStack) {
			this.srcStack = srcStack;
			return this;
		}

		public Builder withDstStack(Stack dstStack) {
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

	public Stack getSrcStack() {
		return srcStack;
	}

	public Stack getDstStack() {
		return dstStack;
	}

}

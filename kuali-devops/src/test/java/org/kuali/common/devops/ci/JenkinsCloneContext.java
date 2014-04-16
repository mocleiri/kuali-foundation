package org.kuali.common.devops.ci;

import org.kuali.common.aws.ec2.model.ImmutableTag;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class JenkinsCloneContext {

	private final String region;
	private final ImmutableTag srcStack;
	private final ImmutableTag dstStack;

	private JenkinsCloneContext(Builder builder) {
		this.region = builder.region;
		this.srcStack = builder.srcStack;
		this.dstStack = builder.dstStack;
	}

	public static class Builder extends ValidatingBuilder<JenkinsCloneContext> {

		private String region;
		private ImmutableTag srcStack;
		private ImmutableTag dstStack;

		public Builder withRegion(String region) {
			this.region = region;
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

	public String getRegion() {
		return region;
	}

	public ImmutableTag getSrcStack() {
		return srcStack;
	}

	public ImmutableTag getDstStack() {
		return dstStack;
	}

}

package org.kuali.common.devops.ci.model;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.Tags.Stack;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;

@IdiotProofImmutable
public final class CloneJenkinsStackContext {

	private final Region region;
	private final Stack srcStack;
	private final Stack dstStack;
	private final BackupMode mode;

	private CloneJenkinsStackContext(Builder builder) {
		this.region = builder.region;
		this.srcStack = builder.srcStack;
		this.dstStack = builder.dstStack;
		this.mode = builder.mode;
	}

	public static class Builder extends ValidatingBuilder<CloneJenkinsStackContext> {

		private Region region = RegionUtils.getRegion("us-west-1");
		private Stack srcStack = Tags.Stack.TEST;
		private Stack dstStack = Tags.Stack.PRODUCTION;
		private BackupMode mode = BackupMode.THIN;

		public Builder withMode(BackupMode mode) {
			this.mode = mode;
			return this;
		}

		public Builder withMode(String mode) {
			return withMode(BackupMode.valueOf(mode));
		}

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
		public CloneJenkinsStackContext build() {
			return validate(new CloneJenkinsStackContext(this));
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

	public BackupMode getMode() {
		return mode;
	}

}

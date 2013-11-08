package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannelService;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.maven.model.Artifact;

public final class InstallJDKContext {

	private final InstallZipPackageContext zipPackage;

	public static class Builder {

		private static final String PACKAGE_NAME = "java";
		private static final String GROUP_ID = "com.oracle";
		private static final String ARTIFACT_ID_PREFIX = "jdk";
		private static final String CLASSIFIER = "linux-x64";
		private static final String TYPE = "zip";

		// Required
		private final InstallZipPackageContext zipPackage;

		public Builder(SecureChannelService service, ChannelContext context, JDKLevel level, String version) {
			this(service, context, getDefaultArtifact(level, version));
		}

		public Builder(SecureChannelService service, ChannelContext context, Artifact artifact) {
			this.zipPackage = new InstallZipPackageContext.Builder(service, context, artifact, PACKAGE_NAME).build();
		}

		private static Artifact getDefaultArtifact(JDKLevel level, String version) {
			Assert.noNulls(level);
			Assert.noBlanks(version);
			return new Artifact.Builder(GROUP_ID, ARTIFACT_ID_PREFIX + level.getVersion(), version).classifier(CLASSIFIER).type(TYPE).build();
		}

		public InstallJDKContext build() {
			Assert.noNulls(zipPackage);
			return new InstallJDKContext(this);
		}
	}

	private InstallJDKContext(Builder builder) {
		this.zipPackage = builder.zipPackage;
	}

	public InstallZipPackageContext getZipPackage() {
		return zipPackage;
	}

}

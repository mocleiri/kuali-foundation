package org.kuali.common.devops.model.metadata;

import org.kuali.common.devops.logic.function.TomcatVersionFunction;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;

@IdiotProofImmutable
public final class EnvironmentMetadata {

	private final MetadataUrl<String> tomcatVersion;

	private EnvironmentMetadata(Builder builder) {
		this.tomcatVersion = builder.tomcatVersion;
	}

	public static Builder builder(String fqdn) {
		String url = Builder.DEFAULT_PREFIX + fqdn + Builder.VERSION_SUFFIX;
		Function<String, String> contentConverter = new TomcatVersionFunction();
		return builder().tomcatVersion(MetadataUrl.create(url, contentConverter));
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EnvironmentMetadata> {

		private static final String DEFAULT_PREFIX = "http://";
		private static final String VERSION_SUFFIX = "/tomcat";

		private MetadataUrl<String> tomcatVersion;

		public Builder tomcatVersion(MetadataUrl<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
			return this;
		}

		@Override
		public EnvironmentMetadata getInstance() {
			return new EnvironmentMetadata(this);
		}

		public MetadataUrl<String> getTomcatVersion() {
			return tomcatVersion;
		}

		public void setTomcatVersion(MetadataUrl<String> tomcatVersion) {
			this.tomcatVersion = tomcatVersion;
		}

	}

	public MetadataUrl<String> getTomcatVersion() {
		return tomcatVersion;
	}

}

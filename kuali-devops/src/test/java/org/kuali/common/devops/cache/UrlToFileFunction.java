package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;

@IdiotProofImmutable
public final class UrlToFileFunction implements Function<String, File> {

	private final File basedir;
	private final String protocol;

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		String token = protocol + "://";
		checkState(url.startsWith(token));
		String fragment = url.substring(token.length());
		return new CanonicalFile(basedir, protocol + File.pathSeparatorChar + fragment);
	}

	private UrlToFileFunction(Builder builder) {
		this.basedir = builder.basedir;
		this.protocol = builder.protocol;
	}

	public static UrlToFileFunction create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<UrlToFileFunction> {

		private File basedir = new CanonicalFile("./target/cache");
		private String protocol = "http";

		public Builder basedir(File basedir) {
			this.basedir = basedir;
			return this;
		}

		public Builder protocol(String protocol) {
			this.protocol = protocol;
			return this;
		}

		@Override
		public UrlToFileFunction getInstance() {
			return new UrlToFileFunction(this);
		}

		public File getBasedir() {
			return basedir;
		}

		public void setBasedir(File basedir) {
			this.basedir = basedir;
		}

		public String getProtocol() {
			return protocol;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}

	}

	public File getBasedir() {
		return basedir;
	}

	public String getProtocol() {
		return protocol;
	}

}

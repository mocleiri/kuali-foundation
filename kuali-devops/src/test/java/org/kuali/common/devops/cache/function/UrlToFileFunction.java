package org.kuali.common.devops.cache.function;

import static com.google.common.base.Preconditions.checkState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;

@IdiotProofImmutable
public final class UrlToFileFunction implements Function<String, File> {

	private final File basedir;
	private final String protocolToken;

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		int pos = url.indexOf(protocolToken);
		checkState(pos != -1, "unable to determine protocol. [%s] does not contain [%s]", url, protocolToken);
		String protocol = url.substring(0, pos); // Typically -> http, https,
		String startsWith = protocol + protocolToken; // Typically -> http:// https://
		checkState(url.startsWith(startsWith));
		String fragment = StringUtils.removeEnd(url.substring(startsWith.length()), "/"); // http://www.yahoo.com/ -> www.yahoo.com
		String path = protocol + File.separatorChar + fragment; // ./target/cache/http/www.yahoo.com
		return new CanonicalFile(basedir, path);
	}

	private UrlToFileFunction(Builder builder) {
		this.basedir = builder.basedir;
		this.protocolToken = builder.protocolToken;
	}

	public static UrlToFileFunction create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<UrlToFileFunction> {

		private File basedir = new CanonicalFile("./target/cache");
		private String protocolToken = "://";

		public Builder basedir(File basedir) {
			this.basedir = basedir;
			return this;
		}

		public Builder protocolToken(String protocolToken) {
			this.protocolToken = protocolToken;
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

		public String getProtocolToken() {
			return protocolToken;
		}

		public void setProtocolToken(String protocolToken) {
			this.protocolToken = protocolToken;
		}

	}

	public File getBasedir() {
		return basedir;
	}

	public String getProtocolToken() {
		return protocolToken;
	}

}

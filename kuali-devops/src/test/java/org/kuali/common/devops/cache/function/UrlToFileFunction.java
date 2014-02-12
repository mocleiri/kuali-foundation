package org.kuali.common.devops.cache.function;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class UrlToFileFunction implements Function<String, File> {

	private final File basedir;
	private final ImmutableList<String> removeTokens;
	private final ImmutableList<String> replaceTokens;
	private final String magicSuffix;

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		String path = getPath(url);
		return new CanonicalFile(basedir, path);
	}

	protected String getPath(String url) {
		// Remove trailing slash (if any)
		String path = StringUtils.removeEnd(url, "/");

		// For each token in the remove list, replace it with empty ("")
		for (String removeToken : removeTokens) {
			path = path.replace(removeToken, "");
		}

		// For each token in the replace list, replace it with the file separator
		for (String replaceToken : replaceTokens) {
			path = path.replace(replaceToken, File.separator);
		}

		// Append a suffix and return
		return path + magicSuffix;
	}

	private UrlToFileFunction(Builder builder) {
		this.basedir = builder.basedir;
		this.removeTokens = ImmutableList.copyOf(builder.removeTokens);
		this.replaceTokens = ImmutableList.copyOf(builder.replaceTokens);
		this.magicSuffix = builder.magicSuffix;
	}

	public static UrlToFileFunction create(File basedir) {
		return builder().basedir(basedir).build();
	}

	public static UrlToFileFunction create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<UrlToFileFunction> {

		private File basedir = new CanonicalFile("./target/cache/urls");
		private List<String> removeTokens = ImmutableList.of();
		// This translates both http://foo.com and classpath:foo.txt correctly
		private List<String> replaceTokens = ImmutableList.of(":", "///", "?", "#", "=");
		private String magicSuffix = ".cached.url.properties";

		public Builder magicSuffix(String magicSuffix) {
			this.magicSuffix = magicSuffix;
			return this;
		}

		public Builder basedir(File basedir) {
			this.basedir = basedir;
			return this;
		}

		public Builder removeTokens(List<String> removeTokens) {
			this.removeTokens = removeTokens;
			return this;
		}

		public Builder replaceTokens(List<String> replaceTokens) {
			this.replaceTokens = replaceTokens;
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

		public List<String> getRemoveTokens() {
			return removeTokens;
		}

		public void setRemoveTokens(List<String> removeTokens) {
			this.removeTokens = removeTokens;
		}

		public List<String> getReplaceTokens() {
			return replaceTokens;
		}

		public void setReplaceTokens(List<String> replaceTokens) {
			this.replaceTokens = replaceTokens;
		}

	}

	public File getBasedir() {
		return basedir;
	}

	public List<String> getRemoveTokens() {
		return removeTokens;
	}

	public List<String> getReplaceTokens() {
		return replaceTokens;
	}

	public String getMagicSuffix() {
		return magicSuffix;
	}

}

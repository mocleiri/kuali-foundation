package org.kuali.common.devops.cache.function;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.leftPad;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.FinalClass;
import org.kuali.common.util.validate.NoNullFields;

import com.google.common.base.Function;

@NoNullFields
@FinalClass
public final class UrlPropertiesFileFunction implements Function<String, File> {

	private int sequence = 1;
	private final Properties urlToFileMapping;
	private final File basedir;
	private final File cacheManager;

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		return new CanonicalFile(basedir, getPath(url));
	}

	protected String getPath(String url) {
		synchronized (urlToFileMapping) {
			String path = urlToFileMapping.getProperty(url);
			if (path == null) {
				path = leftPad(format("%s", sequence++), 3, "0") + ".json";
				urlToFileMapping.put(url, path);
				PropertyUtils.storeSilently(urlToFileMapping, cacheManager);
			}
			return path;
		}
	}

	private UrlPropertiesFileFunction(Builder builder) {
		this.basedir = builder.basedir;
		this.cacheManager = new CanonicalFile(basedir, "cache.properties");
		if (this.cacheManager.exists()) {
			this.urlToFileMapping = PropertyUtils.load(cacheManager);
		} else {
			this.urlToFileMapping = new Properties();
		}
	}

	public static UrlPropertiesFileFunction newUrlPropertiesFileFunction(File basedir) {
		return builder().basedir(basedir).build();
	}

	public static UrlPropertiesFileFunction newUrlPropertiesFileFunction() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<UrlPropertiesFileFunction> {

		private File basedir = new CanonicalFile("./target/cache/urls");

		@Override
		public Set<ConstraintViolation<UrlPropertiesFileFunction>> violations() {
			return violations(make());
		}

		@Override
		public UrlPropertiesFileFunction build() {
			return validate(make());
		}

		private UrlPropertiesFileFunction make() {
			return new UrlPropertiesFileFunction(this);
		}

		public Builder basedir(File basedir) {
			this.basedir = basedir;
			return this;
		}

		public File getBasedir() {
			return basedir;
		}

		public void setBasedir(File basedir) {
			this.basedir = basedir;
		}

	}

	public File getBasedir() {
		return basedir;
	}

	public Properties getUrlToFileMapping() {
		return ImmutableProperties.copyOf(urlToFileMapping);
	}

	public File getCacheManager() {
		return cacheManager;
	}

}

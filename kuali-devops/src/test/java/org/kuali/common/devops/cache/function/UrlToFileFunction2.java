package org.kuali.common.devops.cache.function;

import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.leftPad;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.Counter;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

@IdiotProofImmutable
public final class UrlToFileFunction2 implements Function<String, File> {

	private final Properties urlToFileMapping;
	private final Counter counter = new Counter();
	private final File basedir;
	private final File cacheManager;

	@Override
	public File apply(String url) {
		checkNotBlank(url, "url");
		return getFile(url);
	}

	protected synchronized File getFile(String url) {
		Optional<String> path = fromNullable(urlToFileMapping.getProperty(url));
		if (path.isPresent()) {
			return new File(path.get());
		} else {
			counter.increment();
			File file = new CanonicalFile(basedir, leftPad(counter.getValue() + "", 5, "0") + ".json");
			urlToFileMapping.put(url, file.getPath());
			PropertyUtils.store(urlToFileMapping, cacheManager);
			return file;
		}
	}

	private UrlToFileFunction2(Builder builder) {
		this.basedir = builder.basedir;
		this.cacheManager = builder.cacheManager;
		if (this.cacheManager.exists()) {
			this.urlToFileMapping = PropertyUtils.load(cacheManager);
		} else {
			this.urlToFileMapping = new Properties();
		}
	}

	public static UrlToFileFunction2 newUrlToFileFunction(File basedir) {
		return builder().basedir(basedir).build();
	}

	public static UrlToFileFunction2 create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<UrlToFileFunction2> {

		private File basedir = new CanonicalFile("./target/cache/urls");
		private File cacheManager = new CanonicalFile(basedir, "cache.properties");

		@Override
		public Set<ConstraintViolation<UrlToFileFunction2>> violations() {
			return violations(make());
		}

		@Override
		public UrlToFileFunction2 build() {
			return validate(make());
		}

		private UrlToFileFunction2 make() {
			return new UrlToFileFunction2(this);
		}

		public Builder basedir(File basedir) {
			this.basedir = basedir;
			return this;
		}

		public Builder cacheManager(File cacheManager) {
			this.cacheManager = cacheManager;
			return this;
		}

		public File getBasedir() {
			return basedir;
		}

		public void setBasedir(File basedir) {
			this.basedir = basedir;
		}

		public File getCacheManager() {
			return cacheManager;
		}

		public void setCacheManager(File cacheManager) {
			this.cacheManager = cacheManager;
		}

	}

	public File getBasedir() {
		return basedir;
	}

	public Properties getUrlToFileMapping() {
		return ImmutableProperties.copyOf(urlToFileMapping);
	}

	public Counter getCounter() {
		return counter;
	}

	public File getCacheManager() {
		return cacheManager;
	}

}

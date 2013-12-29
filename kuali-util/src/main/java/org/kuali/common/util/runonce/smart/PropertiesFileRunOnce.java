package org.kuali.common.util.runonce.smart;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Preconditions;

public final class PropertiesFileRunOnce implements RunOnce {

	private static final Logger logger = LoggerUtils.make();

	private final File file;
	private final String encoding;
	private final String key;

	private Properties properties;
	private boolean runonce;
	private boolean initialized = false;

	@Override
	public synchronized void initialize() {
		checkState(!initialized, "Already initialized");
		this.properties = getProperties();
		this.runonce = getBoolean(properties, key);
		showConfig();
		this.initialized = true;
	}

	@Override
	public synchronized boolean isTrue() {
		checkState(initialized, "Not initialized");
		return runonce;
	}

	@Override
	public synchronized void changeState(RunOnceState state) {
		checkState(initialized, "Not initialized");
		checkNotNull(state, "'state' cannot be null");
		properties.setProperty(key, state.name());
		PropertyUtils.store(properties, file, encoding);
		this.properties = PropertyUtils.load(file, encoding);
		this.runonce = getBoolean(properties, key);
		Preconditions.checkState(!isTrue(), "Run once cannot be true");
		logger.info("Transitioned RunOnce to - [{}]", state.name());
	}

	private boolean getBoolean(Properties properties, String key) {
		String value = properties.getProperty(key);
		return Boolean.parseBoolean(value);
	}

	protected void showConfig() {
		logger.info("--- Initializing properties file backed RunOnce ---");
		logger.info("Properties file: [{}]", file);
		logger.info("Properties file exists: {}", file.exists());
		logger.info("Property: [{}]=[{}]", key, properties.get(key));
		logger.info("RunOnce: [{}]", runonce);
	}

	protected Properties getProperties() {
		if (file.exists()) {
			return PropertyUtils.load(file, encoding);
		} else {
			return new Properties();
		}
	}

	private PropertiesFileRunOnce(Builder builder) {
		this.file = builder.file;
		this.encoding = builder.encoding;
		this.key = builder.key;
	}

	public static Builder builder(File file, String encoding, String key) {
		return new Builder(file, encoding, key);
	}

	public static class Builder {

		private final File file;
		private final String key;
		private final String encoding;

		public Builder(File file, String encoding, String key) {
			this.file = new CanonicalFile(file);
			this.encoding = encoding;
			this.key = key;
		}

		public PropertiesFileRunOnce build() {
			PropertiesFileRunOnce instance = new PropertiesFileRunOnce(this);
			validate(instance);
			return instance;
		}

		private void validate(PropertiesFileRunOnce instance) {
			checkNotNull(instance.getFile(), "file cannot be null");
			checkArgument(!StringUtils.isBlank(instance.getEncoding()), "encoding cannot be blank");
			checkArgument(!StringUtils.isBlank(instance.getKey()), "key cannot be blank");
		}
	}

	public File getFile() {
		return file;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getKey() {
		return key;
	}

}

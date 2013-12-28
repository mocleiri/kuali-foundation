package org.kuali.common.util.runonce;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.property.ImmutableProperties;
import org.slf4j.Logger;

import com.google.common.base.Preconditions;

public final class SmartPropertiesFileStateManager implements RunOnceStateManager {

	private static final Logger logger = LoggerUtils.make();

	private final File file;
	private final String encoding;
	private final String key;

	private boolean initialized = false;
	private Properties properties;

	@Override
	public synchronized void initialize() {
		Preconditions.checkState(!initialized, "Already initialized");
		this.properties = getProperties();
		this.initialized = true;
	}

	@Override
	public synchronized boolean isRunOnce() {
		Preconditions.checkState(initialized, "Not initialized");
		logger.info("Examining run once property [{}] from [{}]", key, file);
		String value = properties.getProperty(key);
		boolean runonce = StringUtils.equalsIgnoreCase(Boolean.TRUE.toString(), value);
		Object[] args = { runonce, key, value };
		logger.info("RunOnce={} - [{}={}]", args);
		return runonce;
	}

	@Override
	public synchronized void persistState(RunOnceState state) {
		Preconditions.checkNotNull(state, "'state' cannot be null");
		Preconditions.checkState(initialized, "Not initialized");
		Properties duplicate = PropertyUtils.duplicate(properties);
		duplicate.setProperty(key, state.name());
		PropertyUtils.store(properties, file, encoding);
		this.initialized = false;
		initialize();
	}

	protected Properties getProperties() {
		if (file.exists()) {
			return ImmutableProperties.of(PropertyUtils.load(file, encoding));
		} else {
			return ImmutableProperties.of();
		}
	}

	private SmartPropertiesFileStateManager(Builder builder) {
		this.file = builder.file;
		this.encoding = builder.encoding;
		this.key = builder.key;
	}

	public static class Builder {

		private final File file;
		private final String key;
		private final String encoding;

		public Builder(File file, String encoding, String key) {
			this.file = file;
			this.encoding = encoding;
			this.key = key;
		}

		public SmartPropertiesFileStateManager build() {
			SmartPropertiesFileStateManager instance = new SmartPropertiesFileStateManager(this);
			validate(instance);
			return instance;
		}

		private void validate(SmartPropertiesFileStateManager instance) {
			Preconditions.checkNotNull(instance.getFile(), "file cannot be null");
			Preconditions.checkArgument(!StringUtils.isBlank(instance.getEncoding()), "encoding cannot be blank");
			Preconditions.checkArgument(!StringUtils.isBlank(instance.getKey()), "key cannot be blank");
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

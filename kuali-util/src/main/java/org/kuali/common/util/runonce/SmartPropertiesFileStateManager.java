package org.kuali.common.util.runonce;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.property.ImmutableProperties;
import org.slf4j.Logger;

import com.google.common.base.Preconditions;

public final class SmartPropertiesFileStateManager implements RunOnceStateManager {

	private static final Logger logger = LoggerUtils.make();

	// These are immutable
	private final File file;
	private final String encoding;
	private final String key;

	// These are mutable and changed based on calls to one of the 3 interface methods
	private boolean initialized = false;
	private boolean fileExists = false;
	private Properties properties;
	private long initializedTimestamp;

	@Override
	public synchronized void initialize() {
		Preconditions.checkState(!initialized, "Already initialized");
		this.fileExists = file.exists();
		this.properties = getProperties(fileExists);
		this.initializedTimestamp = System.currentTimeMillis();
		this.initialized = true;
	}

	@Override
	public synchronized boolean isRunOnce() {
		Preconditions.checkState(initialized, "Not initialized");
		Indicator indicator = getIndicator();
		logger.info("RunOnce={} - {}", indicator.isRunOnce(), indicator.getReason());
		return indicator.isRunOnce();
	}

	private Indicator getIndicator() {
		String date = FormatUtils.getDate(initializedTimestamp);
		if (!fileExists) {
			String reason = String.format("[%s] did not exist at initialization - [%s]", file, date);
			return new Indicator(reason, false);
		} else {
			String value = properties.getProperty(key);
			boolean runonce = Boolean.parseBoolean(value);
			String reason = String.format("[%s=%s] in [%s] - [%s]", key, value, file, date);
			return new Indicator(reason, runonce);
		}
	}

	@Override
	public synchronized void persistState(RunOnceState state) {
		Preconditions.checkState(initialized, "Not initialized");
		Preconditions.checkState(getIndicator().isRunOnce(), "Run once must be true before attempting to transition to another state");
		Preconditions.checkNotNull(state, "'state' cannot be null");
		Properties duplicate = PropertyUtils.duplicate(properties);
		duplicate.setProperty(key, state.name());
		PropertyUtils.store(duplicate, file, encoding);
		this.initialized = false;
		initialize();
	}

	protected Properties getProperties(boolean fileExists) {
		if (fileExists) {
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

	public static Builder builder(File file, String encoding, String key) {
		return new Builder(file, encoding, key);
	}

	private class Indicator {

		public Indicator(String reason, boolean runOnce) {
			this.reason = reason;
			this.runOnce = runOnce;
		}

		private final String reason;
		private final boolean runOnce;

		public String getReason() {
			return reason;
		}

		public boolean isRunOnce() {
			return runOnce;
		}

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

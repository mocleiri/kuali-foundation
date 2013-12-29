package org.kuali.common.util.runonce.smart;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;

import com.google.common.base.Preconditions;

public final class PropertiesFileRunOnce implements RunOnce {

	private final File file;
	private final String encoding;
	private final String key;

	private Properties properties;
	private boolean initialized = false;

	@Override
	public synchronized void initialize() {
		Preconditions.checkState(!initialized, "Already initialized");
		this.properties = getProperties();
		this.initialized = true;
	}

	@Override
	public synchronized boolean isTrue() {
		Preconditions.checkState(initialized, "Not initialized");
		String value = properties.getProperty(key);
		return Boolean.parseBoolean(value);
	}

	@Override
	public synchronized void changeState(RunOnceState state) {
		Preconditions.checkState(initialized, "Not initialized");
		Preconditions.checkNotNull(state, "'state' cannot be null");
		properties.setProperty(key, state.name());
		PropertyUtils.store(properties, file, encoding);
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
			this.file = file;
			this.encoding = encoding;
			this.key = key;
		}

		public PropertiesFileRunOnce build() {
			PropertiesFileRunOnce instance = new PropertiesFileRunOnce(this);
			validate(instance);
			return instance;
		}

		private void validate(PropertiesFileRunOnce instance) {
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

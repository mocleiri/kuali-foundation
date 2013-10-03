package org.kuali.common.util.runonce;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFileStateManager implements RunOnceStateManager {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesFileStateManager.class);

	public PropertiesFileStateManager(File propertiesFile, String encoding, String persistentPropertyKey) {
		Assert.noNulls(propertiesFile);
		Assert.noBlanks(persistentPropertyKey, encoding);
		this.propertiesFile = new CanonicalFile(propertiesFile);
		this.encoding = encoding;
		this.persistentPropertyKey = persistentPropertyKey;
	}

	private final String persistentPropertyKey;
	private final File propertiesFile;
	private final String encoding;

	private Properties properties;
	private boolean runonce;
	private boolean initialized;

	@Override
	public synchronized void initialize() {
		Assert.isFalse(initialized, "Already initialized");
		this.properties = getProperties();
		this.runonce = getRunOnce();
		this.initialized = true;
	}

	@Override
	public synchronized boolean isRunOnce() {
		Assert.isTrue(initialized, "Not initialized");
		return runonce;
	}

	@Override
	public synchronized void persistState(RunOnceState state) {
		Assert.isTrue(initialized, "Not initialized");
		Assert.noNulls(state);
		properties.setProperty(persistentPropertyKey, state.name());
		PropertyUtils.store(properties, propertiesFile, encoding);
	}

	public String getPersistentPropertyKey() {
		return persistentPropertyKey;
	}

	public File getPropertiesFile() {
		return propertiesFile;
	}

	protected Properties getProperties() {
		if (propertiesFile.exists()) {
			return PropertyUtils.load(propertiesFile, encoding);
		} else {
			logger.info("Skipping execution. File does not exist - [{}]", propertiesFile);
			return PropertyUtils.EMPTY;
		}
	}

	protected boolean getRunOnce() {
		if (properties == PropertyUtils.EMPTY) {
			return false;
		} else {
			// Log a message indicating we found the properties file and are going to inspect its contents
			logger.info("Examining run once property [{}] in [{}]", persistentPropertyKey, propertiesFile);
			String value = properties.getProperty(persistentPropertyKey);
			boolean runonce = StringUtils.equalsIgnoreCase(Boolean.TRUE.toString(), value);
			if (!runonce) {
				logger.info("Skipping execution - [{}={}]", persistentPropertyKey, value);
			}
			return runonce;
		}
	}

}

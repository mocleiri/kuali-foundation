package org.kuali.common.util.runonce;

import java.io.File;

import org.kuali.common.util.Assert;

public class PropertiesFileStateManager implements PersistentStateManager {

	public PropertiesFileStateManager(File propertiesFile, String persistentPropertyKey) {
		Assert.noNulls(propertiesFile);
		Assert.noBlanks(persistentPropertyKey);
		this.persistentPropertyKey = persistentPropertyKey;
		this.propertiesFile = propertiesFile;
	}

	private final String persistentPropertyKey;
	private final File propertiesFile;

	@Override
	public void updatePersistentState(String state) {
	}

	public String getPersistentPropertyKey() {
		return persistentPropertyKey;
	}

	public File getPropertiesFile() {
		return propertiesFile;
	}

}

package org.kuali.common.util.encrypt.provider;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;
import java.io.IOException;

import org.kuali.common.util.file.CanonicalFile;

public final class MavenEncryptionContextProvider extends AbstractEncryptionContextProvider {

	private static final File USER_SETTINGS = getDefaultSettingsXmlFile();

	public MavenEncryptionContextProvider(String passwordKey, String strengthKey) {
		super(passwordKey, strengthKey);
	}

	@Override
	protected String getValueFromSource(String key) {
		if (!USER_SETTINGS.exists()) {
			return null;
		}
		String content = getContent(USER_SETTINGS);
		String open = "<" + key + ">";
		String close = "</" + key + ">";
		return substringBetween(content, open, close);
	}

	private static String getContent(File file) {
		try {
			return readFileToString(file);
		} catch (IOException e) {
			throw illegalState(e);
		}
	}

	private static File getDefaultSettingsXmlFile() {
		return new CanonicalFile(System.getProperty("user.home") + "/.m2/settings.xml");
	}

}

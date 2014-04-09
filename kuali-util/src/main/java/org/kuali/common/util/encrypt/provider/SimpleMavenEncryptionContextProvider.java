package org.kuali.common.util.encrypt.provider;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;
import java.io.IOException;

public final class SimpleMavenEncryptionContextProvider extends AbstractEncryptionContextProvider {

	private final File USER_SETTINGS = getSettingsFile();

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

	private static File getSettingsFile() {
		String separator = File.separator;
		String fragment = separator + ".m2" + separator + "settings.xml";
		return new File(System.getProperty("user.home"), fragment);
	}

}

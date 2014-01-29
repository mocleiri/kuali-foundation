package org.kuali.common.aws.status;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class GetStatusTest {

	private static final String KEY = "enc.password";
	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			String password = getEncPassword();
			logger.info(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getEncPassword() {
		Optional<String> sys = getSystemPassword();
		if (sys.isPresent()) {
			return sys.get();
		}
		Optional<String> settings = getSettingsXmlPassword();
		if (settings.isPresent()) {
			return settings.get();
		} else {
			throw new IllegalStateException(String.format("[%s] was not found in system properties or [%s]", KEY, getSettingsXmlFile()));
		}
	}

	protected Optional<String> getSystemPassword() {
		if (System.getProperty(KEY) != null) {
			return Optional.of(System.getProperty(KEY).trim());
		} else {
			return Optional.absent();
		}
	}

	protected File getSettingsXmlFile() {
		String path = System.getProperty("user.home") + "/.m2/settings.xml";
		return new CanonicalFile(path);
	}

	protected Optional<String> getSettingsXmlPassword() {
		File file = getSettingsXmlFile();
		if (!file.exists()) {
			return Optional.absent();
		}
		try {
			String contents = FileUtils.readFileToString(file);
			String password = StringUtils.substringBetween(contents, "<" + KEY + ">", "</" + KEY + ">");
			if (!StringUtils.isBlank(password)) {
				return Optional.of(StringUtils.trim(password));
			} else {
				return Optional.absent();
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

}

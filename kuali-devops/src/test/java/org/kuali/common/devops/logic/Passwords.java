package org.kuali.common.devops.logic;

import static org.apache.commons.io.FileUtils.readFileToString;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.Str;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class Passwords {

	private static final String SYS_KEY = "enc.password";
	private static final String ENV_KEY = "ENC_PASSWORD";
	private static final File SETTINGS = getSettingsXmlFile();
	private static final Logger logger = LoggerUtils.make();

	private static String password;

	public synchronized static String getEncPassword() {
		if (password == null) {
			password = initPassword();
		}
		return password;
	}

	protected static String initPassword() {
		Optional<String> sys = getSystemPassword();
		if (sys.isPresent()) {
			logger.info(String.format("Located [%s] in system properties", SYS_KEY));
			return Str.reveal(sys.get());
		}
		Optional<String> env = getEnvPassword();
		if (env.isPresent()) {
			logger.info(String.format("Located [%s] in environment variables", ENV_KEY));
			return Str.reveal(env.get());
		}
		Optional<String> settings = getSettingsXmlPassword();
		if (settings.isPresent()) {
			logger.info(String.format("Located [%s] in [%s]", SYS_KEY, SETTINGS));
			return Str.reveal(settings.get());
		} else {
			throw Exceptions.illegalState("encryption password could not be found in system properties, environment variables, or [%s]", SETTINGS);
		}
	}

	protected static Optional<String> getSystemPassword() {
		if (System.getProperty(SYS_KEY) != null) {
			return Optional.of(System.getProperty(SYS_KEY).trim());
		} else {
			return Optional.absent();
		}
	}

	protected static Optional<String> getEnvPassword() {
		if (System.getenv(ENV_KEY) != null) {
			return Optional.of(System.getenv(ENV_KEY).trim());
		} else {
			return Optional.absent();
		}
	}

	protected static File getSettingsXmlFile() {
		String path = System.getProperty("user.home") + "/.m2/settings.xml";
		return new CanonicalFile(path);
	}

	protected static Optional<String> getSettingsXmlPassword() {
		File file = getSettingsXmlFile();
		if (!file.exists()) {
			return Optional.absent();
		}
		try {
			String contents = readFileToString(file, Encodings.UTF8);
			String password = StringUtils.substringBetween(contents, "<" + SYS_KEY + ">", "</" + SYS_KEY + ">");
			return Optional.fromNullable(StringUtils.trimToNull(password));
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error -> [%s]", file);
		}
	}

}

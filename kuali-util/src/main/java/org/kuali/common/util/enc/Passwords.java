package org.kuali.common.util.enc;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;
import java.io.IOException;

import org.kuali.common.util.Encodings;
import org.kuali.common.util.Str;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class Passwords {

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
			logger.info(format("Located [%s] in system properties", SYS_KEY));
			return Str.reveal(sys.get());
		}
		Optional<String> env = getEnvPassword();
		if (env.isPresent()) {
			logger.info(format("Located [%s] in environment variables", ENV_KEY));
			return Str.reveal(env.get());
		}
		Optional<String> settings = getSettingsXmlPassword();
		if (settings.isPresent()) {
			logger.info(format("Located [%s] in [%s]", SYS_KEY, SETTINGS));
			return Str.reveal(settings.get());
		} else {
			throw illegalState("encryption password could not be found in system properties, environment variables, or [%s]", SETTINGS);
		}
	}

	protected static Optional<String> getSystemPassword() {
		return fromNullable(System.getProperty(SYS_KEY));
	}

	protected static Optional<String> getEnvPassword() {
		return fromNullable(System.getenv(ENV_KEY));
	}

	protected static File getSettingsXmlFile() {
		String path = System.getProperty("user.home") + "/.m2/settings.xml";
		return new CanonicalFile(path);
	}

	protected static Optional<String> getSettingsXmlPassword() {
		if (!SETTINGS.exists()) {
			return absent();
		}
		try {
			String contents = readFileToString(SETTINGS, Encodings.UTF8);
			String password = substringBetween(contents, "<" + SYS_KEY + ">", "</" + SYS_KEY + ">");
			return fromNullable(trimToNull(password));
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error -> [%s]", SETTINGS);
		}
	}

}

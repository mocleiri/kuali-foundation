package org.kuali.common.util.enc;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kuali.common.util.Str;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class Passwords {

	private static final List<String> SYS_KEYS = ImmutableList.of("enc.password", "properties.enc.password");
	private static final String ENV_KEY = "ENC_PASSWORD";
	private static final File SETTINGS = getSettingsXmlFile();
	private static final Logger logger = newLogger();

	private static String password;

	public synchronized static String getEncPassword() {
		if (password == null) {
			password = initPassword();
		}
		return password;
	}

	protected static String initPassword() {
		Optional<Property> sys = getSystemPassword();
		if (sys.isPresent()) {
			logger.info(format("Located [%s] in system properties", sys.get().getKey()));
			return Str.reveal(sys.get().getValue());
		}
		Optional<String> env = getEnvPassword();
		if (env.isPresent()) {
			logger.info(format("Located [%s] in environment variables", ENV_KEY));
			return Str.reveal(env.get());
		}
		Optional<Property> settings = getSettingsXmlPassword();
		if (settings.isPresent()) {
			logger.info(format("Located [%s] in [%s]", settings.get().getKey(), SETTINGS));
			return Str.reveal(settings.get().getValue());
		} else {
			throw illegalState("encryption password could not be found in system properties, environment variables, or [%s]", SETTINGS);
		}
	}

	protected static Optional<Property> getSystemPassword() {
		for (String key : SYS_KEYS) {
			Optional<String> password = fromNullable(trimToNull(System.getProperty(key)));
			if (password.isPresent()) {
				return Optional.of(new Property(key, password.get()));
			}
		}
		return absent();
	}

	protected static Optional<String> getEnvPassword() {
		return fromNullable(System.getenv(ENV_KEY));
	}

	protected static File getSettingsXmlFile() {
		String path = System.getProperty("user.home") + "/.m2/settings.xml";
		return new CanonicalFile(path);
	}

	protected static Optional<Property> getSettingsXmlPassword() {
		if (!SETTINGS.exists()) {
			return absent();
		}
		String content = getSettingsXmlContent();
		for (String key : SYS_KEYS) {
			String nullable = substringBetween(content, "<" + key + ">", "</" + key + ">");
			Optional<String> password = fromNullable(trimToNull(nullable));
			if (password.isPresent()) {
				return Optional.of(new Property(key, password.get()));
			}
		}
		return absent();
	}

	protected static String getSettingsXmlContent() {
		try {
			return readFileToString(SETTINGS, UTF8);
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error -> [%s]", SETTINGS);
		}
	}

	private static final class Property {

		private final String key;
		private final String value;

		public Property(String key, String value) {
			this.key = checkNotBlank(key, "key");
			this.value = checkNotBlank(value, "value");
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}

}

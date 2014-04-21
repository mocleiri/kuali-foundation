package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Iterables.filter;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.encrypt.EncryptionStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.encrypt.provider.DefaultEncryptionContextProviderChain.toEnvKey;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.kuali.common.util.nullify.NullUtils.trimToNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kuali.common.util.encrypt.EncryptionContext;
import org.kuali.common.util.encrypt.EncryptionStrength;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class FileEncryptionContextProvider implements EncryptionContextProvider {

	private static final Logger logger = newLogger();

	private static final String ENC_PASSWORD_FILE_SYS_KEY = "enc.password.file";
	private static final String ENC_PASSWORD_FILE_ENV_KEY = toEnvKey(ENC_PASSWORD_FILE_SYS_KEY);
	private static final File DEFAULT_ENC_PASSWORD_FILE = new CanonicalFile(System.getProperty("user.home") + "/.ssh/enc.password");

	@Override
	public Optional<EncryptionContext> getEncryptionContext() {
		File file = getEncPasswordFile();
		if (!file.exists()) {
			logger.debug(format("[%s] does not exist", file));
			return absent();
		}
		List<String> lines = readConfigFile(file);
		String password = getPassword(lines);
		EncryptionStrength strength = getStrength(lines);
		logger.debug(format("[%s, %s] encryption password located", file, strength));
		return Optional.of(new EncryptionContext(password, strength));
	}

	protected EncryptionStrength getStrength(List<String> lines) {
		if (lines.size() < 2) {
			return DEFAULT_ENCRYPTION_STRENGTH;
		}
		String value = lines.get(1);
		if (isBlank(value)) {
			return DEFAULT_ENCRYPTION_STRENGTH;
		}
		return EncryptionStrength.valueOf(value.toUpperCase());
	}

	protected String getPassword(List<String> lines) {
		checkState(lines.size() > 0, "[%s] must contain at least one non-blank, non-comment line containing the encryption password.");
		String password = lines.get(0);
		checkNotBlank(password, "password");
		return password;
	}

	protected static List<String> readConfigFile(File file) {
		try {
			return copyOf(filter(readLines(file), ConfigFilePredicate.INSTANCE));
		} catch (IOException e) {
			throw illegalState(e);
		}
	}

	protected static File getEncPasswordFile() {
		Optional<String> sys = getOptional(System.getProperty(ENC_PASSWORD_FILE_SYS_KEY));
		if (sys.isPresent()) {
			File file = new CanonicalFile(sys.get());
			logger.debug(format("encryption system property: %s=[%s]", ENC_PASSWORD_FILE_SYS_KEY, file.getPath()));
			return file;
		}
		Optional<String> env = getOptional(System.getenv(ENC_PASSWORD_FILE_ENV_KEY));
		if (env.isPresent()) {
			File file = new CanonicalFile(env.get());
			logger.debug(format("encryption environment variable: %s=[%s]", ENC_PASSWORD_FILE_ENV_KEY, file.getPath()));
			return file;
		}
		return DEFAULT_ENC_PASSWORD_FILE;
	}

	protected static Optional<String> getOptional(String value) {
		return fromNullable(trimToNull(value));
	}

}

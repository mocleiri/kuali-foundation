package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.encrypt.EncryptionContext;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public abstract class AbstractEncryptionContextProvider implements EncryptionContextProvider {

	public AbstractEncryptionContextProvider(String encryptionPasswordKey, String encryptionStrengthKey) {
		this.encryptionPasswordKey = checkNotBlank(encryptionPasswordKey, "encryptionPasswordKey");
		this.encryptionStrengthKey = checkNotBlank(encryptionStrengthKey, "encryptionStrengthKey");
	}

	private static final Logger logger = newLogger();
	private final String encryptionPasswordKey;
	private final String encryptionStrengthKey;

	@Override
	public Optional<EncryptionContext> getEncryptionContext() {
		Optional<String> password = getOptionalString(encryptionPasswordKey);
		if (!password.isPresent()) {
			return absent();
		} else {
			logger.info(format("encryption password located by [%s]", this.getClass().getSimpleName()));
			EncStrength strength = getEncryptionStrength();
			return Optional.of(new EncryptionContext(password.get(), strength));
		}
	}

	protected Optional<String> getOptionalString(String key) {
		return fromNullable(trimToNull(getValueFromSource(key)));
	}

	protected abstract String getValueFromSource(String key);

	protected EncStrength getEncryptionStrength() {
		Optional<String> optional = getOptionalString(encryptionStrengthKey);
		if (optional.isPresent()) {
			return EncStrength.valueOf(optional.get().toUpperCase());
		} else {
			return DEFAULT_ENCRYPTION_STRENGTH;
		}
	}

}

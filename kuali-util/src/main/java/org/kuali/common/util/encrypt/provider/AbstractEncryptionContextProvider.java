package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.encrypt.EncryptionStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.encrypt.EncryptionContext;
import org.kuali.common.util.encrypt.EncryptionStrength;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public abstract class AbstractEncryptionContextProvider implements EncryptionContextProvider {

	private static final Logger logger = newLogger();

	public AbstractEncryptionContextProvider(String passwordKey, String strengthKey) {
		this.passwordKey = checkNotBlank(passwordKey, "passwordKey");
		this.strengthKey = checkNotBlank(strengthKey, "strengthKey");
	}

	private final String passwordKey;
	private final String strengthKey;

	@Override
	public Optional<EncryptionContext> getEncryptionContext() {
		Optional<String> password = getOptionalString(passwordKey);
		if (!password.isPresent()) {
			logger.debug(format("[%s, key=%s] encryption password not found", this.getClass().getSimpleName(), passwordKey));
			return absent();
		} else {
			EncryptionStrength strength = getEncryptionStrength();
			logger.debug(format("[%s, key=%s %s] encryption password located", this.getClass().getSimpleName(), passwordKey, strength));
			return Optional.of(new EncryptionContext(password.get(), strength));
		}
	}

	protected Optional<String> getOptionalString(String key) {
		return fromNullable(trimToNull(getValueFromSource(key)));
	}

	protected abstract String getValueFromSource(String key);

	protected EncryptionStrength getEncryptionStrength() {
		Optional<String> optional = getOptionalString(strengthKey);
		if (optional.isPresent()) {
			return EncryptionStrength.valueOf(optional.get().toUpperCase());
		} else {
			return DEFAULT_ENCRYPTION_STRENGTH;
		}
	}

}

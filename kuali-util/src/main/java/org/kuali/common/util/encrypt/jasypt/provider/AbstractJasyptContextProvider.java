package org.kuali.common.util.encrypt.jasypt.provider;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_PASSWORD_KEY;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_STRENGTH_KEY;

import org.kuali.common.util.enc.EncStrength;
import org.kuali.common.util.encrypt.EncContext;

import com.google.common.base.Optional;

public abstract class AbstractJasyptContextProvider implements JasyptContextProvider {

	@Override
	public Optional<EncContext> getJasyptContext() {
		Optional<String> password = getOptionalString(ENCRYPTION_PASSWORD_KEY);
		if (!password.isPresent()) {
			return absent();
		} else {
			EncStrength strength = getEncryptionStrength();
			return Optional.of(new EncContext(password.get(), strength));
		}
	}

	protected Optional<String> getOptionalString(String key) {
		return fromNullable(trimToNull(getValueFromSource(key)));
	}

	protected abstract String getValueFromSource(String key);

	protected EncStrength getEncryptionStrength() {
		Optional<String> optional = getOptionalString(ENCRYPTION_STRENGTH_KEY);
		if (optional.isPresent()) {
			return EncStrength.valueOf(optional.get().toUpperCase());
		} else {
			return DEFAULT_ENCRYPTION_STRENGTH;
		}
	}

}

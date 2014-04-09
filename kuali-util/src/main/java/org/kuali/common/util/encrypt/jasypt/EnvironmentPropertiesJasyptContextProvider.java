package org.kuali.common.util.encrypt.jasypt;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_PASSWORD_KEY;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_STRENGTH_KEY;

import org.kuali.common.util.enc.EncStrength;

import com.google.common.base.Optional;

public final class EnvironmentPropertiesJasyptContextProvider implements JasyptContextProvider {

	@Override
	public Optional<JasyptContext> getJasyptContext() {
		Optional<String> password = getSystemProperty(ENCRYPTION_PASSWORD_KEY);
		if (!password.isPresent()) {
			return absent();
		} else {
			EncStrength strength = getEncryptionStrength();
			return Optional.of(new JasyptContext(password.get(), strength));
		}
	}

	protected EncStrength getEncryptionStrength() {
		Optional<String> optional = getSystemProperty(ENCRYPTION_STRENGTH_KEY);
		if (optional.isPresent()) {
			return EncStrength.valueOf(optional.get().toUpperCase());
		} else {
			return DEFAULT_ENCRYPTION_STRENGTH;
		}
	}

	protected Optional<String> getSystemProperty(String key) {
		return fromNullable(trimToNull(System.getProperty(checkNotBlank(key, "key"))));
	}

}

package org.kuali.common.util.encrypt;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;


public final class EncryptionContext {

	public EncryptionContext(String password, EncryptionStrength strength) {
		this.password = checkNotBlank(password, "password");
		this.strength = checkNotNull(strength, "strength");
	}

	private final String password;
	private final EncryptionStrength strength;

	public String getPassword() {
		return password;
	}

	public EncryptionStrength getStrength() {
		return strength;
	}

}

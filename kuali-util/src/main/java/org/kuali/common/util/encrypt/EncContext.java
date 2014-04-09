package org.kuali.common.util.encrypt;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.util.enc.EncStrength;

public final class EncContext {

	public EncContext(String password, EncStrength strength) {
		this.password = checkNotBlank(password, "password");
		this.strength = checkNotNull(strength, "strength");
	}

	private final String password;
	private final EncStrength strength;

	public String getPassword() {
		return password;
	}

	public EncStrength getStrength() {
		return strength;
	}

}

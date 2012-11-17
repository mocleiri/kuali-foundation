package org.kuali.common.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

public class EncUtils {

	public static final TextEncryptor getTextEncryptor(EncryptionStrength strength, String password) {
		switch (strength) {
		case BASIC:
			BasicTextEncryptor bte = new BasicTextEncryptor();
			bte.setPassword(password);
			return bte;
		case STRONG:
			StrongTextEncryptor ste = new StrongTextEncryptor();
			ste.setPassword(password);
			return ste;
		default:
			throw new IllegalArgumentException(strength + " is unknown");
		}

	}
}

package org.kuali.common.util.enc;

import java.util.Properties;

public interface EncryptionService {

	/**
	 * Encrypt the text and wrap it inside <code>ENC()</code>
	 * 
	 * <pre>
	 *   foo -> x7UiXya -> ENC(x7UiXya)
	 * </pre>
	 */
	String encrypt(String plainText);

	/**
	 * Unwrap the encrypted text and then decrypt it
	 * 
	 * <pre>
	 *   ENC(x7UiXya) -> x7UiXya -> foo
	 * </pre>
	 */
	String decrypt(String encryptedText);

	/**
	 * Detect any property values that are wrapped inside <code>ENC()</code>, unwrap the value, decrypt it, and update the properties object with the unencrypted value.
	 */
	void decrypt(Properties properties);

	/**
	 * Encrypt any property values that are not already encrypted.
	 */
	void encrypt(Properties properties);

}

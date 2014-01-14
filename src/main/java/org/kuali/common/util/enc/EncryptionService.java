package org.kuali.common.util.enc;

import java.util.Properties;

public interface EncryptionService {

	/**
	 * Encrypt the text and prefix it with <code>enc--</code>. If the text is already encrypted, do nothing.
	 * 
	 * <pre>
	 *   foo -> x7UiXya -> enc--x7UiXya
	 * </pre>
	 */
	String encrypt(String plainText);

	/**
	 * Remove the <code>enc--</code> prefix and then decrypt it. If the text is not encrypted, do nothing.
	 * 
	 * <pre>
	 *   enc--x7UiXya -> x7UiXya -> foo
	 * </pre>
	 */
	String decrypt(String encryptedText);

	/**
	 * Detect any encrypted property values and decrypt them
	 */
	void decrypt(Properties properties);

	/**
	 * Encrypt any property values that are not already encrypted.
	 */
	void encrypt(Properties properties);

}

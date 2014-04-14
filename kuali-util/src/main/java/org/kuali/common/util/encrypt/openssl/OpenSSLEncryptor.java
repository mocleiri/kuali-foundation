package org.kuali.common.util.encrypt.openssl;

import static java.util.Arrays.copyOfRange;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static org.codehaus.plexus.util.Base64.decodeBase64;
import static org.codehaus.plexus.util.Base64.encodeBase64;
import static org.kuali.common.util.Encodings.ASCII;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.Str.getUTF8Bytes;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildDefaultOpenSSLContext;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.buildEncryptedContext;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.checkBase64;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.combineByteArrays;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.createBytesFromChars;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.createSalt;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.toByteArray;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.kuali.common.util.Str;
import org.kuali.common.util.encrypt.Encryptor;

/**
 * 
 * This class encrypts and decrypts strings using the same technique as OpenSSL. It enables java code and OpenSSL to share encrypted strings with each other.
 * 
 * <pre>
 * echo -n "foo" | openssl enc -aes128 -e -base64 -A -k password
 * echo -n "U2FsdGVkX1/WqX83Xtf6SlHhaLeOFhbv4pDo/obzE8Y=" | openssl enc -aes128 -d -base64 -A -k password
 * </pre>
 */
public final class OpenSSLEncryptor implements Encryptor {

	// Immutable and safe to expose via a getter
	private final OpenSSLContext context;

	// Internal only. Do not expose via getters
	private final char[] password;
	private final byte[] prefix;
	private final byte[] passwordBytes;

	public OpenSSLEncryptor(String password) {
		this(buildDefaultOpenSSLContext(), password);
	}

	public OpenSSLEncryptor(OpenSSLContext context, String password) {
		this.password = checkNotBlank(password, "password").toCharArray();
		this.context = checkNotNull(context, "context");
		this.passwordBytes = createBytesFromChars(this.password);
		this.prefix = getUTF8Bytes(context.getSaltPrefix());
	}

	@Override
	public String encrypt(String text) {
		checkNotNull(text, "text");

		byte[] salt = createSalt(context.getSaltSize());
		byte[] plaintext = getUTF8Bytes(text);

		try {
			// specify cipher and digest
			byte[] encrypted = doCipher(ENCRYPT_MODE, salt, plaintext);

			// Combine the prefix, salt, and the encrypted bytes into one array
			byte[] bytes = combineByteArrays(prefix, salt, encrypted);

			// Convert everything into base64
			byte[] base64 = encodeBase64(bytes);

			// Convert the base64 bytes into a string
			return new String(base64, ASCII);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	@Override
	public String decrypt(String text) {
		// Null not allowed
		checkNotNull(text, "text");

		// Decode the base64 text into bytes
		byte[] bytes = decodeBase64(Str.getBytes(checkBase64(text), ASCII));

		// OpenSSL always inserts the prefix "Salted__" followed by the salt itself
		// You have to explicitly use the -nosalt option to turn this off (which OpenSSL strongly advises against)
		int saltOffset = context.getSaltPrefix().length();
		byte[] salt = copyOfRange(bytes, saltOffset, saltOffset + context.getSaltSize());

		try {
			// extract the portion of the array containing the encrypted bytes
			int encryptedBytesOffset = saltOffset + context.getSaltSize();
			byte[] encrypted = copyOfRange(bytes, encryptedBytesOffset, bytes.length);

			// specify cipher and digest
			byte[] decrypted = doCipher(DECRYPT_MODE, salt, encrypted);

			// Construct a string from the decrypted bytes
			return new String(decrypted, UTF8);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected byte[] doCipher(int mode, byte[] salt, byte[] bytes) {
		try {
			// specify cipher and digest
			Cipher cipher = Cipher.getInstance(context.getTransformation());

			// the IV length is driven by the cipher block size
			int initVectorLength = cipher.getBlockSize();

			// Calculate the iv + key bytes using the exact same technique OpenSSL does
			OpenSSLEncryptedContext oec = buildEncryptedContext(context, initVectorLength, salt, passwordBytes);

			// Create java objects from the raw bytes
			SecretKeySpec key = new SecretKeySpec(toByteArray(oec.getKey()), context.getAlgorithm());
			IvParameterSpec iv = new IvParameterSpec(toByteArray(oec.getInitVector()));

			// initialize the cipher instance
			cipher.init(mode, key, iv);

			// Return the initialized cipher
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	public OpenSSLContext getContext() {
		return context;
	}

}
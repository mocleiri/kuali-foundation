package org.kuali.common.util.encrypt.openssl;

import static java.util.Arrays.copyOfRange;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static org.apache.commons.io.FileUtils.openInputStream;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.toByteArray;
import static org.codehaus.plexus.util.Base64.decodeBase64;
import static org.codehaus.plexus.util.Base64.encodeBase64;
import static org.kuali.common.util.Str.getAsciiBytes;
import static org.kuali.common.util.Str.getAsciiString;
import static org.kuali.common.util.Str.getUTF8Bytes;
import static org.kuali.common.util.Str.getUTF8String;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildDefaultOpenSSLContext;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.buildEncryptedContext;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.checkBase64;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.combineByteArrays;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.createSalt;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.toByteArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.kuali.common.util.encrypt.Encryptor;

/**
 * 
 * Encrypt/decrypt using the same technique as OpenSSL. This enables java code to work with data encrypted by OpenSSL (and vice versa)
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
	private final byte[] password;
	private final byte[] prefix;

	public OpenSSLEncryptor(String password) {
		this(buildDefaultOpenSSLContext(), password);
	}

	public OpenSSLEncryptor(OpenSSLContext context, String password) {
		this.password = getUTF8Bytes(checkNotNull(password, "password"));
		this.context = checkNotNull(context, "context");
		this.prefix = getUTF8Bytes(context.getSaltPrefix());
	}

	public void encrypt(File src, File dst) {
		checkNotNull(src, "src");
		checkNotNull(dst, "dst");
		InputStream in = null;
		OutputStream out = null;
		try {
			in = openInputStream(src);
			out = openOutputStream(dst);
			encrypt(in, out);
		} catch (IOException e) {
			throw illegalState(e);
		} finally {
			closeQuietly(in);
			closeQuietly(out);
		}
	}

	public void encrypt(InputStream in, OutputStream out) throws IOException {
		byte[] bytes = toByteArray(in);
		byte[] encrypted = encrypt(bytes);
		byte[] base64 = encodeBase64(encrypted, true);
		out.write(base64);
	}

	public byte[] encrypt(byte[] bytes) {
		// Null not allowed
		checkNotNull(bytes, "bytes");

		// Generate a random salt
		byte[] salt = createSalt(context.getSaltSize());

		// encrypt the bytes using the salt
		byte[] encrypted = doCipher(context, ENCRYPT_MODE, salt, bytes, password);

		// Combine the prefix, salt, and encrypted bytes into one array
		return combineByteArrays(prefix, salt, encrypted);
	}

	@Override
	public String encrypt(String text) {
		// Null not allowed
		checkNotNull(text, "text");

		// Convert the text into bytes
		byte[] bytes = getUTF8Bytes(text);

		// Encrypt the bytes
		byte[] encrypted = encrypt(bytes);

		// Encode as base 64
		byte[] base64 = encodeBase64(encrypted);

		// Convert the base64 bytes into a string
		return getAsciiString(base64);
	}

	@Override
	public String decrypt(String text) {
		// Null not allowed
		checkNotNull(text, "text");

		// Decode the base64 text into bytes
		byte[] bytes = decodeBase64(getAsciiBytes(checkBase64(text)));

		// OpenSSL always inserts the prefix "Salted__" followed by the salt itself
		// You have to explicitly use the -nosalt option to turn this off (which OpenSSL strongly advises against)
		int saltOffset = context.getSaltPrefix().length();
		byte[] salt = copyOfRange(bytes, saltOffset, saltOffset + context.getSaltSize());

		// encrypted bytes come after the prefix and the salt
		int encryptedBytesOffset = saltOffset + context.getSaltSize();

		// extract the portion of the array containing the encrypted bytes
		byte[] encrypted = copyOfRange(bytes, encryptedBytesOffset, bytes.length);

		// decrypt the bytes using the salt that was embedded in the text
		byte[] decrypted = doCipher(context, DECRYPT_MODE, salt, encrypted, password);

		// Construct a string from the decrypted bytes
		return getUTF8String(decrypted);
	}

	protected static byte[] doCipher(OpenSSLContext context, int mode, byte[] salt, byte[] bytes, byte[] password) {
		try {
			// specify cipher and digest
			Cipher cipher = Cipher.getInstance(context.getTransformation());

			// the IV length is driven by the cipher block size
			int initVectorLength = cipher.getBlockSize();

			// Calculate the iv + key bytes using the exact same technique OpenSSL does
			OpenSSLEncryptedContext oec = buildEncryptedContext(context, initVectorLength, salt, password);

			// Create java objects from the raw bytes
			SecretKeySpec key = new SecretKeySpec(toByteArray(oec.getKey()), context.getAlgorithm());
			IvParameterSpec iv = new IvParameterSpec(toByteArray(oec.getInitVector()));

			// initialize the cipher instance
			cipher.init(mode, key, iv);

			// Return the encrypted/decrypted bytes
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	public OpenSSLContext getContext() {
		return context;
	}

}
package org.kuali.common.util.encrypt.openssl;

import static java.util.Arrays.copyOfRange;
import static org.codehaus.plexus.util.Base64.decodeBase64;
import static org.kuali.common.util.Encodings.ASCII;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildDefaultOpenSSLContext;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.buildEncryptedContext;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.checkBase64;
import static org.kuali.common.util.encrypt.openssl.OpenSSLUtils.createBytesFromChars;

import java.security.MessageDigest;

import javax.crypto.Cipher;

import org.kuali.common.util.Str;
import org.kuali.common.util.encrypt.Encryptor;

public final class OpenSSLEncryptor implements Encryptor {

	// Not exposed via a getter
	private final char[] password;
	private final OpenSSLContext context;

	public OpenSSLEncryptor(String password) {
		this(buildDefaultOpenSSLContext(), password);
	}

	public OpenSSLEncryptor(OpenSSLContext context, String password) {
		this.password = checkNotBlank(password, "password").toCharArray();
		this.context = checkNotNull(context, "context");
	}

	@Override
	public String encrypt(String text) {
		checkNotBlank(text, "text");
		return null;
	}

	@Override
	public String decrypt(String text) {
		checkNotBlank(text, "text");
		return null;
	}

	protected OpenSSLEncryptedContext getEncryptedContext(String text) {
		byte[] bytes = decodeBase64(Str.getBytes(checkBase64(text), ASCII));

		// OpenSSL inserts the prefix "Salted__" if salt is being used (the default)
		int saltOffset = context.getSaltPrefix().length();
		byte[] salt = copyOfRange(bytes, saltOffset, saltOffset + context.getSaltSize());

		// Everything after the salt prefix and salt are the encrypted bytes
		int ciphertextOffset = saltOffset + context.getSaltSize();
		byte[] encrypted = copyOfRange(bytes, ciphertextOffset, bytes.length);

		try {
			// --- specify cipher and digest for EVP_BytesToKey method ---
			Cipher cipher = Cipher.getInstance(context.getTransformation());
			MessageDigest messageDigest = MessageDigest.getInstance(context.getDigest());

			int initVectorLength = cipher.getBlockSize();
			byte[] data = createBytesFromChars(password);
			OpenSSLEncryptedContext encryptedContext = buildEncryptedContext(context, initVectorLength, messageDigest, salt, data);

			return null;
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	public OpenSSLContext getContext() {
		return context;
	}

}
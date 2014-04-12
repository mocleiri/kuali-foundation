package org.kuali.common.util.encrypt;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;
import static java.lang.System.arraycopy;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64.encodeBase64;
import static org.kuali.common.util.Encodings.ASCII;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.kuali.common.util.Str;
import org.slf4j.Logger;

public class AES2Test {

	private static final Logger logger = newLogger();

	// OpenSSL hard codes this prefix if a salt is used
	private static final String OPENSSL_PREFIX = "Salted__";
	private static final byte[] OPENSSL_PREFIX_BYTES = Str.getBytes(OPENSSL_PREFIX, UTF8);

	String cipherTransformation = "AES/CBC/PKCS5Padding";
	String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";
	String secretKeySpecAlgorithm = "AES";
	int bits = 128;
	int initializationVectorLength = 16;
	int saltLength = 8;
	int iterationCount = 1024 * 64;

	@Test
	public void test() {
		try {
			String plaintext = "hello world";
			String password = "password";

			String encrypted = encrypt(plaintext, password);
			String decrypted = decrypt(encrypted, password);
			BasicTextEncryptor encryptor = new BasicTextEncryptor();
			encryptor.setPassword(password);
			// info("password         -> %s", password);
			// info("salt=%s", salt);
			// info("key=%s", key);
			// info("iv =%s", result.getInitializationVector());
			// info("data=%s", result.getEncryptedText());
			// info("decrypted text   -> %s", decrypted);
			// info("jasypt encrypted -> %s", encryptor.encrypt(plaintext));
			// info("jasypt decrypted -> %s", encryptor.decrypt(encryptor.encrypt(plaintext)));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String decrypt(String encrypted, String password) {
		try {
			// byte[] iv = getBytesFromHexString(encrypted.getInitializationVector());
			// byte[] ciphertext = decodeBase64(encrypted.getEncryptedText().getBytes(UTF8));
			// SecretKey secret = getSecretKey(password, salt);
			// Cipher cipher = Cipher.getInstance(cipherTransformation);
			// cipher.init(DECRYPT_MODE, secret, new IvParameterSpec(iv));
			// return new String(cipher.doFinal(ciphertext), UTF8);
			return "";
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected SecretKey getSecretKey(String password, byte[] salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, bits);
			SecretKey tmp = factory.generateSecret(spec);
			return new SecretKeySpec(tmp.getEncoded(), secretKeySpecAlgorithm);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected String encrypt(String plaintext, String password) {
		try {
			byte[] prefix = OPENSSL_PREFIX_BYTES;
			byte[] salt = getSalt(saltLength);
			checkState(salt.length == saltLength, "salt must be %s bytes", saltLength);
			SecretKey secret = getSecretKey(password, salt);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(ENCRYPT_MODE, secret);
			AlgorithmParameters params = cipher.getParameters();
			byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			checkState(iv.length == initializationVectorLength, "initialization vector must be %s bytes", initializationVectorLength);
			byte[] ciphertext = cipher.doFinal(plaintext.getBytes(UTF8));
			byte[] bytes = allocate(prefix, salt, ciphertext, iv);
			int offset = 0;
			offset = add(bytes, prefix, offset);
			offset = add(bytes, salt, offset);
			offset = add(bytes, ciphertext, offset);
			offset = add(bytes, iv, offset);
			return new String(encodeBase64(bytes), ASCII);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected byte[] allocate(byte[]... arrays) {
		int length = 0;
		for (byte[] array : arrays) {
			length += array.length;
		}
		return new byte[length];
	}

	protected int add(byte[] dst, byte[] src, int offset) {
		arraycopy(src, 0, dst, offset, src.length);
		return offset + src.length;
	}

	protected byte[] getSalt(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
		return salt;
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

}

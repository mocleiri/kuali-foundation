package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static org.codehaus.plexus.util.Base64.decodeBase64;
import static org.codehaus.plexus.util.Base64.encodeBase64;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.HexUtils.getBytesFromHexString;
import static org.kuali.common.util.HexUtils.toHexStringLower;
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
import org.slf4j.Logger;

public class AESTest {

	private static final Logger logger = newLogger();

	String cipherTransformation = "AES/CBC/PKCS5Padding";
	String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";
	String secretKeySpecAlgorithm = "AES";
	int bits = 128;
	int saltLength = 8;
	int iterationCount = 1024 * 64;

	@Test
	public void test() {
		try {
			String plaintext = "hello world";
			String password = "password";
			String salt = toHexStringLower(getSalt(saltLength));
			String key = toHexStringLower(getSecretKey(password, salt).getEncoded());

			EncryptionResult result = encrypt(plaintext, password, salt);
			String decrypted = decrypt(result, password, salt);
			BasicTextEncryptor encryptor = new BasicTextEncryptor();
			encryptor.setPassword(password);
			// info("password         -> %s", password);
			info("salt=%s", salt);
			info("key=%s", key);
			info("iv =%s", result.getInitializationVector());
			info("data=%s", result.getEncryptedText());
			// info("decrypted text   -> %s", decrypted);
			// info("jasypt encrypted -> %s", encryptor.encrypt(plaintext));
			// info("jasypt decrypted -> %s", encryptor.decrypt(encryptor.encrypt(plaintext)));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String decrypt(EncryptionResult encrypted, String password, String salt) {
		try {
			byte[] iv = getBytesFromHexString(encrypted.getInitializationVector());
			byte[] ciphertext = decodeBase64(encrypted.getEncryptedText().getBytes(UTF8));
			SecretKey secret = getSecretKey(password, salt);
			secret.getEncoded();
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(DECRYPT_MODE, secret, new IvParameterSpec(iv));
			return new String(cipher.doFinal(ciphertext), UTF8);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected SecretKey getSecretKey(String password, String salt) {
		try {
			byte[] saltBytes = getBytesFromHexString(salt);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterationCount, bits);
			SecretKey tmp = factory.generateSecret(spec);
			return new SecretKeySpec(tmp.getEncoded(), secretKeySpecAlgorithm);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected EncryptionResult encrypt(String plaintext, String password, String salt) {
		try {
			SecretKey secret = getSecretKey(password, salt);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(ENCRYPT_MODE, secret);
			AlgorithmParameters params = cipher.getParameters();
			byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			byte[] ciphertext = cipher.doFinal(plaintext.getBytes(UTF8));
			return new EncryptionResult(new String(encodeBase64(ciphertext), UTF8).replace("\n", "").replace("\r", ""), toHexStringLower(iv));
		} catch (Exception e) {
			throw illegalState(e);
		}
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

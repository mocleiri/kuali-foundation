package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.HexUtils.getBytesFromHexString;
import static org.kuali.common.util.HexUtils.toHexString;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;
import org.slf4j.Logger;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class OpenSSLTest {

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
			byte[] secret = getBytesFromHexString("5f4dcc3b5aa765d61d8327deb882cf99");
			byte[] iv = getBytesFromHexString("2b95990a9151374abd8ff8c5a7a0fe08");
			byte[] data = Base64.decode("16GRjUXjW5vCqs9bQrAEEQ==");

			Cipher cipher = Cipher.getInstance(cipherTransformation);
			Key key = new SecretKeySpec(secret, secretKeySpecAlgorithm);
			cipher.init(DECRYPT_MODE, key, new IvParameterSpec(iv));
			String decrypted = new String(cipher.doFinal(data), UTF8);
			info("decrypted -> %s", decrypted);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

	protected String decrypt(EncryptionResult encrypted, String password, String salt) {
		try {
			byte[] iv = getBytesFromHexString(encrypted.getInitializationVector());
			byte[] ciphertext = Base64.decode(encrypted.getEncryptedText());
			SecretKey secret = getSecretKey(password, salt);
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
			return new EncryptionResult(Base64.encode(ciphertext).replace("\n", "").replace("\r", ""), toHexString(iv).toLowerCase());
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected byte[] getSalt(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
		return salt;
	}
}

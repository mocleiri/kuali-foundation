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

public class AES256WithSaltTest {

	private static final Logger logger = newLogger();

	String cipherTransformation = "AES/CBC/PKCS5Padding";
	String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";
	String secretKeySpecAlgorithm = "AES";
	int bits = 256;
	int saltLength = 8;
	int iterationCount = 1024 * 64;

	@Test
	public void test() {
		try {
			String plaintext = "hello world";
			String password = "password";
			String salt = toHexString(getSalt(saltLength)).toLowerCase();
			EncryptionResult result = encrypt(plaintext, password, salt);
			String decrypted = decrypt(result, password, salt);
			info("plaintext      -> %s", plaintext);
			info("password       -> %s", password);
			info("salt           -> %s", salt);
			info("encrypted text -> %s", result.getEncryptedText());
			info("init vector    -> %s", result.getInitializationVector());
			info("decrypted text -> %s", decrypted);
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
			byte[] iv = Base64.decode(encrypted.getInitializationVector());
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
			return new EncryptionResult(Base64.encode(ciphertext), Base64.encode(iv));
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

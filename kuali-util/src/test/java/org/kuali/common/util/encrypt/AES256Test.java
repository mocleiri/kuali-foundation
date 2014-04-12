package org.kuali.common.util.encrypt;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;

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

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class AES256Test {

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
			String salt = Base64.encode(getSalt(saltLength));
			EncryptionResult result = encrypt(plaintext, password, salt);
			System.out.println(result.getInitializationVector());
			System.out.println(result.getEncryptedText());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String decrypt(EncryptionResult encrypted, String password, String salt) {
		try {
			byte[] iv = Base64.decode(encrypted.getInitializationVector());
			byte[] ciphertext = Base64.decode(encrypted.getEncryptedText());
			SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.decode(salt), iterationCount, bits);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), secretKeySpecAlgorithm);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(DECRYPT_MODE, secret, new IvParameterSpec(iv));
			return new String(cipher.doFinal(ciphertext), UTF8);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	protected EncryptionResult encrypt(String plaintext, String password, String salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), Base64.decode(salt), iterationCount, bits);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), secretKeySpecAlgorithm);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			cipher.init(ENCRYPT_MODE, secret);
			AlgorithmParameters params = cipher.getParameters();
			byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			byte[] ciphertext = cipher.doFinal(plaintext.getBytes(UTF8));
			String initializationVector = Base64.encode(iv);
			String encryptedText = Base64.encode(ciphertext);
			return new EncryptionResult(initializationVector, encryptedText);
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

package org.kuali.common.util.encrypt;

import static org.kuali.common.util.Encodings.UTF8;

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

	@Test
	public void test() {
		try {
			String plaintext = "hello world";
			char[] password = "password".toCharArray();
			byte[] salt = getSalt(8);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secret);
			AlgorithmParameters params = cipher.getParameters();
			byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
			byte[] ciphertext = cipher.doFinal(plaintext.getBytes(UTF8));
			System.out.println(Base64.encode(iv));
			System.out.println(Base64.encode(ciphertext));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected byte[] getSalt(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
		return salt;
	}
}

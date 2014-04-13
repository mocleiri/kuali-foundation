package org.kuali.common.util.encrypt.openssl;

import static java.util.Arrays.copyOfRange;
import static org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64.decodeBase64;
import static org.kuali.common.util.Encodings.ASCII;
import static org.kuali.common.util.HexUtils.toHexStringLower;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class OpenSSLDecryptor {

	private static final int INDEX_KEY = 0;
	private static final int INDEX_IV = 1;
	private static final int ITERATIONS = 1;

	private static final int SALT_OFFSET = 8;
	private static final int SALT_SIZE = 8;
	private static final int CIPHERTEXT_OFFSET = SALT_OFFSET + SALT_SIZE;

	private static final int KEY_SIZE_BITS = 128;

	public static void main(String[] args) {
		try {
			String password = "password";

			// --- base 64 data ---
			String base64String = "U2FsdGVkX19WFuCfjQ37hzy75W/8e/XMIFdjswnwjn8=";
			byte[] headerSaltAndCipherText = decodeBase64(base64String.getBytes(ASCII));

			// --- extract salt
			// header is "Salted__", ASCII encoded, if salt is being used (the default)
			byte[] salt = copyOfRange(headerSaltAndCipherText, SALT_OFFSET, SALT_OFFSET + SALT_SIZE);
			System.out.println("salt=" + toHexStringLower(salt));

			// --- extract encrypted
			byte[] encrypted = copyOfRange(headerSaltAndCipherText, CIPHERTEXT_OFFSET, headerSaltAndCipherText.length);

			// --- specify cipher and digest for EVP_BytesToKey method ---
			Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			// --- create key and IV ---
			// the IV is useless, OpenSSL might as well have used zero's
			final byte[][] keyAndIV = EVP_BytesToKey(KEY_SIZE_BITS / Byte.SIZE, aesCBC.getBlockSize(), md5, salt, password.getBytes(ASCII), ITERATIONS);

			byte[] keyBytes = keyAndIV[INDEX_KEY];
			byte[] ivBytes = keyAndIV[INDEX_IV];
			System.out.println("key=" + toHexStringLower(keyBytes));
			System.out.println("iv=" + toHexStringLower(ivBytes));

			SecretKeySpec key = new SecretKeySpec(keyAndIV[INDEX_KEY], "AES");
			IvParameterSpec iv = new IvParameterSpec(keyAndIV[INDEX_IV]);

			// --- initialize cipher instance and decrypt ---
			aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] decrypted = aesCBC.doFinal(encrypted);

			String answer = new String(decrypted, ASCII);
			System.out.println(answer);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static byte[][] EVP_BytesToKey(int keyLength, int initVectorLength, MessageDigest md, byte[] salt, byte[] data, int count) {
		byte[][] both = new byte[2][];
		byte[] key = new byte[keyLength];
		int key_ix = 0;
		byte[] iv = new byte[initVectorLength];
		int iv_ix = 0;
		both[0] = key;
		both[1] = iv;
		byte[] md_buf = null;
		int nkey = keyLength;
		int niv = initVectorLength;
		int i = 0;
		if (data == null) {
			return both;
		}
		int addmd = 0;
		for (;;) {
			md.reset();
			if (addmd++ > 0) {
				md.update(md_buf);
			}
			md.update(data);
			if (null != salt) {
				md.update(salt, 0, 8);
			}
			md_buf = md.digest();
			for (i = 1; i < count; i++) {
				md.reset();
				md.update(md_buf);
				md_buf = md.digest();
			}
			i = 0;
			if (nkey > 0) {
				for (;;) {
					if (nkey == 0)
						break;
					if (i == md_buf.length)
						break;
					key[key_ix++] = md_buf[i];
					nkey--;
					i++;
				}
			}
			if (niv > 0 && i != md_buf.length) {
				for (;;) {
					if (niv == 0)
						break;
					if (i == md_buf.length)
						break;
					iv[iv_ix++] = md_buf[i];
					niv--;
					i++;
				}
			}
			if (nkey == 0 && niv == 0) {
				break;
			}
		}
		for (i = 0; i < md_buf.length; i++) {
			md_buf[i] = 0;
		}
		return both;
	}

}
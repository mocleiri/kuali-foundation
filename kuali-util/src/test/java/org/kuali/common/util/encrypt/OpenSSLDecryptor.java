package org.kuali.common.util.encrypt;

import static org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64.decodeBase64;
import static org.kuali.common.util.Encodings.ASCII;
import static org.kuali.common.util.HexUtils.toHexStringLower;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class OpenSSLDecryptor {

	private static final int INDEX_KEY = 0;
	private static final int INDEX_IV = 1;
	private static final int ITERATIONS = 1;

	private static final int SALT_OFFSET = 8;
	private static final int SALT_SIZE = 8;
	private static final int CIPHERTEXT_OFFSET = SALT_OFFSET + SALT_SIZE;

	private static final int KEY_SIZE_BITS = 256;

	public static byte[][] EVP_BytesToKey(int key_len, int iv_len, MessageDigest md, byte[] salt, byte[] data, int count) {
		byte[][] both = new byte[2][];
		byte[] key = new byte[key_len];
		int key_ix = 0;
		byte[] iv = new byte[iv_len];
		int iv_ix = 0;
		both[0] = key;
		both[1] = iv;
		byte[] md_buf = null;
		int nkey = key_len;
		int niv = iv_len;
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

	public static void main(String[] args) {
		try {
			// --- base 64 data ---
			String password = "password";
			String base64Encrypted = "U2FsdGVkX18MLZ5tlJIDThNk7SGkw+cDxA/ynRtDWEc=";
			byte[] headerSaltAndCipherText = decodeBase64(base64Encrypted.getBytes(ASCII));

			// --- extract salt & encrypted ---

			// header is "Salted__", ASCII encoded, if salt is being used (the default)
			byte[] salt = Arrays.copyOfRange(headerSaltAndCipherText, SALT_OFFSET, SALT_OFFSET + SALT_SIZE);
			System.out.println("salt=" + toHexStringLower(salt));
			byte[] encrypted = Arrays.copyOfRange(headerSaltAndCipherText, CIPHERTEXT_OFFSET, headerSaltAndCipherText.length);

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
		} catch (BadPaddingException e) {
			// AKA "something went wrong"
			throw new IllegalStateException("Bad password, algorithm, mode or padding; no salt, wrong number of iterations or corrupted ciphertext.", e);
		} catch (IllegalBlockSizeException e) {
			throw new IllegalStateException("Bad algorithm, mode or corrupted (resized) ciphertext.", e);
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException(e);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
package org.kuali.common.util.encrypt.openssl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.arraycopy;
import static org.kuali.common.util.Ascii.isDigit;
import static org.kuali.common.util.Ascii.isLetter;
import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.encrypt.openssl.OpenSSLContext.buildOpenSSLContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.kuali.common.util.encrypt.EncryptionContext;

import com.google.common.collect.ImmutableList;

public class OpenSSL {

	private static final Random RANDOM = new SecureRandom();
	private static final int DEFAULT_SALT_SIZE = 8;
	// When using all 62 alphanumeric characters, 22 is the minimum length required for producing more combinations than what is possible with 128 bits
	// In other words, 62^22 is greater than 2^128
	private static final int DEFAULT_PASSWORD_LENGTH = 22;
	private static final List<Character> DEFAULT_PASSWORD_CHARS = getAlphaNumericCharacters();

	/**
	 * Uses SecureRandom to generate a random 22 character alphanumeric string
	 */
	public static String generatePassword() {
		return generatePassword(DEFAULT_PASSWORD_LENGTH);
	}

	public static String generatePassword(int length) {
		return generatePassword(DEFAULT_PASSWORD_CHARS, length);
	}

	public static String generatePassword(List<Character> chars, int length) {
		return generatePassword(chars, length, RANDOM);
	}

	public static String generatePassword(List<Character> chars, int length, Random random) {
		char[] buffer = new char[length];
		int size = chars.size();
		for (int i = 0; i < length; i++) {
			buffer[i] = chars.get(random.nextInt(size));
		}
		return new String(buffer);
	}

	public static OpenSSLEncryptor buildOpenSSLEncryptor(EncryptionContext context) {
		OpenSSLContext osc = buildOpenSSLContext(context.getStrength());
		return new OpenSSLEncryptor(osc, context.getPassword());
	}

	public static byte[] combineByteArrays(byte[]... arrays) {
		byte[] bytes = allocateByteArray(arrays);
		int offset = 0;
		for (byte[] array : arrays) {
			offset = addByteArray(bytes, array, offset);
		}
		return bytes;
	}

	public static int addByteArray(byte[] dst, byte[] src, int offset) {
		arraycopy(src, 0, dst, offset, src.length);
		return offset + src.length;
	}

	public static byte[] allocateByteArray(byte[]... arrays) {
		int length = 0;
		for (byte[] array : arrays) {
			length += array.length;
		}
		return new byte[length];
	}

	/**
	 * Creates an 8 byte salt
	 */
	public static byte[] createSalt() {
		return createSalt(DEFAULT_SALT_SIZE);
	}

	/**
	 * Creates a salt of the indicated length
	 */
	public static byte[] createSalt(int length) {
		byte[] salt = new byte[length];
		RANDOM.nextBytes(salt);
		return salt;
	}

	public static final byte[] toByteArray(List<Byte> bytes) {
		byte[] array = new byte[bytes.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = bytes.get(i);
		}
		return array;
	}

	public static ImmutableList<Byte> toByteList(byte[] original) {
		return toByteList(original, 0, original.length);
	}

	public static ImmutableList<Byte> toByteList(byte[] original, int offset, int length) {
		List<Byte> list = newArrayList();
		for (int i = offset; i < length; i++) {
			list.add(original[i]);
		}
		return ImmutableList.copyOf(list);
	}

	public static String checkBase64(String text) {
		checkNotNull(text, "text");
		for (char c : text.toCharArray()) {
			checkArgument(isBase64(c), "'%s' is not a base 64 character", c);
		}
		return text;
	}

	public static boolean isBase64(char c) {
		if (isLetter(c) || isDigit(c)) {
			return true;
		} else {
			return c == '/' || c == '+' || c == '=';
		}
	}

	public static MessageDigest getMessageDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw illegalArgument(e);
		}

	}

	public static OpenSSLEncryptedContext buildEncryptedContext(OpenSSLContext context, int initVectorLength, byte[] salt, byte[] data) {
		MessageDigest md = getMessageDigest(context.getDigestAlgorithm());
		int keyLength = context.getKeySizeBits() / Byte.SIZE;
		byte[] key = new byte[keyLength];
		int keyIndex = 0;
		byte[] initVector = new byte[initVectorLength];
		int initVectorIndex = 0;
		byte[] md_buf = null;
		int nkey = keyLength;
		int niv = initVectorLength;
		int i = 0;
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
			for (i = 1; i < context.getIterations(); i++) {
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
					key[keyIndex++] = md_buf[i];
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
					initVector[initVectorIndex++] = md_buf[i];
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

		OpenSSLEncryptedContext.Builder builder = OpenSSLEncryptedContext.builder();
		builder.withSalt(toByteList(salt));
		builder.withKey(toByteList(key));
		builder.withInitVector(toByteList(initVector));
		return builder.build();
	}

	protected static List<Character> getAlphaNumericCharacters() {
		List<Character> chars = newArrayList();
		for (char c = 'A'; c < 'Z'; c++) {
			chars.add(c);
		}
		for (char c = 'a'; c < 'z'; c++) {
			chars.add(c);
		}
		for (char c = '0'; c < '9'; c++) {
			chars.add(c);
		}
		return ImmutableList.copyOf(chars);
	}

}

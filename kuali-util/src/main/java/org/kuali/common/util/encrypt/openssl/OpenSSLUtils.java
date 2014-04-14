package org.kuali.common.util.encrypt.openssl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.Ascii.isDigit;
import static org.kuali.common.util.Ascii.isLetter;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.kuali.common.util.Str;

import com.google.common.collect.ImmutableList;

public class OpenSSLUtils {

	public static byte[] getSalt(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
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
		checkNotBlank(text, "text");
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

	public static byte[] createBytesFromChars(char[] chars) {
		return Str.getBytes(new String(chars), UTF8);
	}

	public static OpenSSLEncryptedContext buildEncryptedContext(OpenSSLContext context, int initVectorLength, byte[] salt, byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance(context.getDigest());
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
		} catch (NoSuchAlgorithmException e) {
			throw illegalState(e);
		}
	}
}

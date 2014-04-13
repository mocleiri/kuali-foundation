package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.HexUtils.getBytesFromHexString;
import static org.kuali.common.util.HexUtils.toHexStringLower;
import static org.kuali.common.util.HexUtils.toStringFromHex;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.junit.Test;
import org.slf4j.Logger;

public class OpenSSLTest {

	private static final Logger logger = newLogger();

	// openssl enc -a -k password -in plaintext -aes-128-cbc -p -out plaintext.enc | tr '[:upper:]' '[:lower:]'; echo "data=$(base64 test.enc)"

	String cipherTransformation = "AES/CBC/PKCS5Padding";
	String secretKeySpecAlgorithm = "AES";

	@Test
	public void test() {
		try {
			byte[] secret = getBytesFromHexString("5f4dcc3b5aa765d61d8327deb882cf99");
			byte[] iv = getBytesFromHexString("2b95990a9151374abd8ff8c5a7a0fe08");
			byte[] data = decodeBase64("16GRjUXjW5vCqs9bQrAEEQ==");
			String hex = toHexStringLower(data);
			System.out.println(hex);
			String plain = toStringFromHex(hex, UTF8);
			System.out.println(plain);
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			Key key = new SecretKeySpec(secret, secretKeySpecAlgorithm);
			cipher.init(DECRYPT_MODE, key, new IvParameterSpec(iv));
			String decrypted = new String(cipher.doFinal(data), UTF8);
			info("decrypted -> %s", decrypted);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static byte[] decodeBase64(String s) throws IOException {
		return Base64.decodeBase64(s.getBytes(UTF8));
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}
}

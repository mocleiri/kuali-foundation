package org.kuali.common.util.encrypt;

import static java.lang.String.format;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.HexUtils.getBytesFromHexString;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;
import org.slf4j.Logger;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class OpenSSLTest {

	private static final Logger logger = newLogger();

	// openssl enc -nosalt -k password -in test -aes-128-cbc -p -out test.enc | tr '[:upper:]' '[:lower:]'; echo "data=$(base64 test.enc)"

	String cipherTransformation = "AES/CBC/PKCS5Padding";
	String secretKeySpecAlgorithm = "AES";

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
}

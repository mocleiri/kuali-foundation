package org.kuali.nexus.plugins;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class S3Get {
	private static final String UTF8 = "UTF-8";
	private static final String HMACSHA1 = "HmacSHA1";

	@Test
	public void test1() {
		try {
			String host1 = "http://maven.kuali.org.s3.amazonaws.com";
			String host2 = "http://maven.kuali.org";

			String bucket = "maven.kuali.org";

			String item = "/private/com/oracle/ojdbc14/10.2.0.3.0/ojdbc14-10.2.0.3.0.jar";

			System.out.println(getURL(host1, bucket, item));
			System.out.println(getURL(host2, bucket, item));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	protected String getURL(String host, String bucket, String item) throws Exception {
		String awsAccessKeyId = "AKIAJFD5IM7IPVVUEBNA";
		String awsSecretAccessKey = System.getProperty("aws.secret");
		if (awsSecretAccessKey == null) {
			throw new Exception("Please specify AWS Secret Key using -Daws.secret=XYZ");
		}
		Mac mac = getMac(awsSecretAccessKey);
		long expires = getExpiresTimestamp();
		String data = getData(bucket, item, expires);
		String signature = getSignature(mac, data);
		Map<String, String> params = new HashMap<String, String>();
		params.put("AWSAccessKeyId", awsAccessKeyId);
		params.put("Signature", signature);
		params.put("Expires", expires + "");
		return getURL(host, item, params);
	}

	protected String getURL(String host, String item, Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append(host);
		sb.append(item);
		int count = 0;
		for (Map.Entry<String, String> pair : params.entrySet()) {
			if (count == 0) {
				sb.append("?");
			} else {
				sb.append("&");
			}
			sb.append(pair.getKey() + "=" + URLEncoder.encode(pair.getValue(), UTF8));
			count++;
		}
		return sb.toString();
	}

	protected String getData(String bucket, String item, long expires) {
		StringBuilder sb = new StringBuilder();
		sb.append("GET\n");
		sb.append("\n");
		sb.append("\n");
		sb.append(expires + "\n");
		sb.append("/" + bucket + item);
		return sb.toString();
	}

	protected long getExpiresTimestamp() {
		long millis = System.currentTimeMillis();
		long seconds = millis / 1000;
		return seconds + (60 * 60);
	}

	protected Mac getMac(String awsSecretAccessKey) throws UnsupportedEncodingException, InvalidKeyException,
			NoSuchAlgorithmException {
		SecretKeySpec signingKey = getSigningKey(awsSecretAccessKey);
		Mac mac = Mac.getInstance(HMACSHA1);
		mac.init(signingKey);
		return mac;

	}

	protected SecretKeySpec getSigningKey(String awsSecretKey) throws UnsupportedEncodingException {
		byte[] keyBytes = awsSecretKey.getBytes(UTF8);
		return new SecretKeySpec(keyBytes, HMACSHA1);
	}

	protected String getSignature(Mac mac, String data) throws UnsupportedEncodingException {
		byte[] signBytes = mac.doFinal(data.getBytes(UTF8));
		byte[] b64 = Base64.encodeBase64(signBytes);
		return new String(b64);
	}
}

package org.kuali.common.devops.presigned;

import static com.amazonaws.auth.SigningAlgorithm.HmacSHA1;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.net.URL;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;
import org.kuali.common.devops.logic.Passwords;
import org.kuali.common.util.enc.EncUtils;
import org.slf4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class GeneratePreSignedURL {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			String secretKey = getSecretKey();
			// long expiration = System.currentTimeMillis() + 1000 * 60 * 60 * 72;
			long expiration = 1396678108436L;
			String stringToSign = "GET\n\n\n1396678108\n/maven.kuali.org/private/com/oracle/jdk6/1.6.0-u43/jdk6-1.6.0-u43.pom";
			// 189e671278ba818abbe4f38f09a3961184471ddb - wrong
			// fe2688cee03761c7f541c04ce245531fa106f129 - right
			String hmacsha1hex = hmacsha1hex(stringToSign, secretKey);
			logger.info(hmacsha1hex);
			System.out.println("expiration=" + expiration);
			String bucket = "maven.kuali.org";
			String key = "private/com/oracle/jdk6/1.6.0-u43/jdk6-1.6.0-u43.pom";
			GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key);
			request.setExpiration(new java.util.Date(expiration));
			AWSCredentials foundation = getFoundationCreds(secretKey);
			AmazonS3Client client = new AmazonS3Client(foundation);
			URL url = client.generatePresignedUrl(request);
			logger.info(url.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected String hmacsha1hex(String data, String key) throws Exception {
		return hex(hmacsha1bytes(data, key));
	}

	protected byte[] hmacsha1bytes(String data, String key) throws Exception {
		return sign(data.getBytes(UTF8), key.getBytes(UTF8), HmacSHA1);
	}

	protected byte[] sign(byte[] data, byte[] key, SigningAlgorithm algorithm) throws AmazonClientException {
		try {
			Mac mac = Mac.getInstance(algorithm.toString());
			mac.init(new SecretKeySpec(key, algorithm.toString()));
			return mac.doFinal(data);
		} catch (Exception e) {
			throw new AmazonClientException("Unable to calculate a request signature: " + e.getMessage(), e);
		}
	}

	private String getSecretKey() {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		return enc.decrypt("uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=");
	}

	private AWSCredentials getFoundationCreds(String secretKey) {
		String accessKey = "AKIAJFD5IM7IPVVUEBNA";
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	protected String hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02X", b).toLowerCase());
		}
		return sb.toString();
	}

}

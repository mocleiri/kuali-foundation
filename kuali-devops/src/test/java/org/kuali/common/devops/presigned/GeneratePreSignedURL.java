package org.kuali.common.devops.presigned;

import static org.kuali.common.util.log.Loggers.newLogger;

import java.net.URL;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;
import org.kuali.common.devops.logic.Passwords;
import org.kuali.common.util.enc.EncUtils;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class GeneratePreSignedURL {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			// long expiration = System.currentTimeMillis() + 1000 * 60 * 120;
			long expiration = 1396391061646L;
			System.out.println("expiration=" + expiration);
			String bucket = "maven.kuali.org";
			String key = "private/com/oracle/jdk6/1.6.0-u43/jdk6-1.6.0-u43.pom";
			GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key);
			request.setExpiration(new java.util.Date(expiration));
			AWSCredentials foundation = getFoundationCreds();
			AmazonS3Client client = new AmazonS3Client(foundation);
			URL url = client.generatePresignedUrl(request);
			logger.info(url.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private AWSCredentials getFoundationCreds() {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		String accessKey = "AKIAJFD5IM7IPVVUEBNA";
		String secretKey = enc.decrypt("uXNCzc6efcKz1zvp4t5Fj4wyR9oGw2GZ2VOB3SXZaoXaV1BA1Gao2d2vWXnjqUA1oKzg+0s9NAM=");
		return new BasicAWSCredentials(accessKey, secretKey);
	}

}

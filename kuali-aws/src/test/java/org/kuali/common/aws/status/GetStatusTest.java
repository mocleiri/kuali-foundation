package org.kuali.common.aws.status;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.collect.Lists;

public class GetStatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			String password = Passwords.getEncPassword();
			List<AWSCredentials> creds = getCredentials(password);
			logger.info(String.format("Located %s sets of credentials", creds.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<AWSCredentials> getCredentials(String password) {
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		List<AWSCredentials> list = Lists.newArrayList();
		for (AWSCredentials credentials : Credentials.values()) {
			String accessKey = credentials.getAWSAccessKeyId();
			String secretKey = credentials.getAWSSecretKey();
			if (EncUtils.isEncrypted(secretKey)) {
				secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
			}
			list.add(new BasicAWSCredentials(accessKey, secretKey));
		}
		return list;
	}

}

package org.kuali.common.aws.status;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.aws.Credentials;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.collect.Lists;

public class Auth {

	public static List<AWSCredentials> getCredentials() {
		String password = Passwords.getEncPassword();
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

package org.kuali.common.devops.util;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.devops.aws.Credentials;
import org.kuali.common.devops.dnsme.DNSMadeEasyCreds;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.collect.Lists;

public class Auth {

	public static DNSMadeEasyCredentials getDnsmeCredentials() {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		String apiKey = DNSMadeEasyCreds.PRODUCTION.getCredentials().getApiKey();
		String secretKey = DNSMadeEasyCreds.PRODUCTION.getCredentials().getSecretKey();
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
		}
		return DNSMadeEasyCredentials.create(apiKey, secretKey);
	}

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

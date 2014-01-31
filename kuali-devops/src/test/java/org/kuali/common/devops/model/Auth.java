package org.kuali.common.devops.model;

import java.util.Map;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.devops.aws.Credentials;
import org.kuali.common.devops.dnsme.DNSMadeEasyCreds;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.collect.Maps;

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

	public static Map<String, AWSCredentials> getAwsCredentials() {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		Map<String, AWSCredentials> map = Maps.newTreeMap();
		for (Credentials credentials : Credentials.values()) {
			String accessKey = credentials.getAWSAccessKeyId();
			String secretKey = credentials.getAWSSecretKey();
			if (EncUtils.isEncrypted(secretKey)) {
				secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
			}
			map.put(credentials.name().toLowerCase(), new BasicAWSCredentials(accessKey, secretKey));
		}
		return map;
	}

}

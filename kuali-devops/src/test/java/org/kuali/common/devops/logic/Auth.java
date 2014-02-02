package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;

import java.util.Map;
import java.util.SortedSet;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.devops.aws.Credentials;
import org.kuali.common.devops.dnsme.DNSMadeEasyCreds;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
			map.put(getName(credentials), new BasicAWSCredentials(accessKey, secretKey));
		}
		return map;
	}

	protected static String getName(Credentials credentials) {
		return credentials.name().toLowerCase();
	}

	public static SortedSet<String> getAwsAccounts() {
		SortedSet<String> accounts = Sets.newTreeSet();
		for (Credentials credentials : Credentials.values()) {
			String account = getName(credentials);
			boolean added = accounts.add(account);
			checkState(added, "duplicate account name -> %s", account);
		}
		return accounts;
	}

}

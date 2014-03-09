package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.enc.EncUtils.unwrap;

import java.util.SortedSet;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.devops.aws.Credentials;
import org.kuali.common.devops.dnsme.DNSMadeEasyCreds;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class Auth {

	public static KeyPair getKeyPair(KeyPair.Builder builder) {
		return builder.withPrivateKey(decrypt(builder.getPrivateKey())).build();
	}

	protected static Optional<String> decrypt(Optional<String> string) {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		return Optional.of(enc.decrypt(EncUtils.unwrap(string.get())));
	}

	public static DNSMadeEasyCredentials getDnsmeCredentials() {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		String apiKey = DNSMadeEasyCreds.PRODUCTION.getCredentials().getApiKey();
		String secretKey = DNSMadeEasyCreds.PRODUCTION.getCredentials().getSecretKey();
		secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
		return DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
	}

	public static SortedSet<String> getAwsAccountNames() {
		SortedSet<String> accounts = Sets.newTreeSet();
		for (Credentials credentials : Credentials.values()) {
			String account = getAccount(credentials);
			boolean added = accounts.add(account);
			checkState(added, "duplicate account name -> [%s]", account);
		}
		return accounts;
	}

	public static AWSCredentials getAwsCredentials(String account) {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		Credentials credentials = getCredentials(account);
		String accessKey = credentials.getAWSAccessKeyId();
		String secretKey = enc.decrypt(unwrap(credentials.getAWSSecretKey()));
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	protected static Credentials getCredentials(String account) {
		for (Credentials credentials : Credentials.values()) {
			if (account.equals(getAccount(credentials))) {
				return credentials;
			}
		}
		throw illegalState("unknown account -> [%s]", account);
	}

	protected static String getAccount(Credentials credentials) {
		return credentials.name().toLowerCase();
	}

}

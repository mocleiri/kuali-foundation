package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.enc.EncUtils.isEncrypted;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.devops.aws.EncryptedAwsCredentials;
import org.kuali.common.devops.dnsme.EncryptedDNSMECredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public class Auth {

	public static KeyPair getKeyPair(String account) {
		for (EncryptedAwsCredentials credentials : EncryptedAwsCredentials.values()) {
			if (credentials.name().equalsIgnoreCase(account)) {
				return getKeyPair(kpb.getBuilder());
			}
		}
		throw illegalArgument("unknown account -> %s", account);
	}

	public static KeyPair getKeyPair(EncryptedAwsCredentials credentials) {
		return builder.withPrivateKey(decrypt(builder.getPrivateKey())).build();
	}

	public static String encrypt(String string) {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		return enc.encrypt(string);
	}

	public static String decrypt(String string) {
		return decrypt(Optional.of(string)).get();
	}

	public static Optional<String> decrypt(Optional<String> string) {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		String text = string.get();
		if (isEncrypted(text)) {
			text = EncUtils.unwrap(text);
		}
		return Optional.of(enc.decrypt(text));
	}

	public static DNSMadeEasyCredentials getDnsmeCredentials() {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		String apiKey = EncryptedDNSMECredentials.PRODUCTION.getCredentials().getApiKey();
		String secretKey = EncryptedDNSMECredentials.PRODUCTION.getCredentials().getSecretKey();
		secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
		return DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
	}

	public static AWSCredentials getAwsCredentials(String account) {
		for (EncryptedAwsCredentials credentials : EncryptedAwsCredentials.values()) {
			if (credentials.name().equalsIgnoreCase(account)) {
				return getKeyPair(kpb.getBuilder());
			}
		}
	}


}

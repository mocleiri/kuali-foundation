package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.encrypt.Encryption.buildDefaultEncryptor;

import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.devops.aws.EncryptedAwsCredentials;
import org.kuali.common.devops.aws.EncryptedKeyPair;
import org.kuali.common.devops.dnsme.EncryptedDNSMECredentials;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.encrypt.Encryptor;

import com.amazonaws.auth.AWSCredentials;

public class Auth {

	public static KeyPair getKeyPair(String account) {
		for (EncryptedKeyPair keyPair : EncryptedKeyPair.values()) {
			if (keyPair.name().equalsIgnoreCase(account)) {
				return getKeyPair(keyPair);
			}
		}
		throw illegalArgument("unknown account -> %s", account);
	}

	public static KeyPair getKeyPair(EncryptedKeyPair encrypted) {
		Encryptor encryptor = buildDefaultEncryptor();
		KeyPair keyPair = encrypted.getKeyPair();
		String publicKey = encryptor.decrypt(keyPair.getPublicKey());
		String privateKey = encryptor.decrypt(keyPair.getPrivateKey());
		return KeyPair.builder(keyPair.getName()).withPublicKey(publicKey).withPrivateKey(privateKey).build();
	}

	public static DNSMadeEasyCredentials getDNSMECredentials() {
		DNSMadeEasyCredentials creds = EncryptedDNSMECredentials.PRODUCTION.getCredentials();
		Encryptor encryptor = buildDefaultEncryptor();
		String apiKey = encryptor.decrypt(creds.getApiKey());
		String secretKey = encryptor.decrypt(creds.getSecretKey());
		return DNSMadeEasyCredentials.builder().withApiKey(apiKey).withSecretKey(secretKey).build();
	}

	public static AWSCredentials getAwsCredentials(String account) {
		for (EncryptedAwsCredentials credentials : EncryptedAwsCredentials.values()) {
			if (credentials.name().equalsIgnoreCase(account)) {
				return getKeyPair(credentials);
			}
		}
	}
	public static AWSCredentials getAwsCredentials(EncryptedAwsCredentials encrypted) {
		Encryptor encryptor = buildDefaultEncryptor();
		String accessKey = encryptor.decrypt(encrypted.getAWSAccessKeyId());
		String secretKey = encryptor.decrypt(encrypted.getAWSSecretKey());
		return new ImmutableAWSCredentials
		for (EncryptedAwsCredentials credentials : EncryptedAwsCredentials.values()) {
			if (credentials.name().equalsIgnoreCase(account)) {
				return getKeyPair(credentials);
			}
		}
	}

}

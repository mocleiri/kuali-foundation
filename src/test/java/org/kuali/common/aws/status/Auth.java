package org.kuali.common.aws.status;

import static org.kuali.common.util.enc.EncUtils.isEncrypted;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.KeyPairBuilders;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.util.enc.EncUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.common.collect.Lists;

public class Auth {

	public static KeyPair getKeyPair(KeyPairBuilders kpb) {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		KeyPair.Builder builder = kpb.getBuilder();
		String privateKey = builder.getPrivateKey().get();
		if (isEncrypted(privateKey)) {
			privateKey = enc.decrypt(EncUtils.unwrap(privateKey));
		}
		return KeyPair.builder(builder.getName()).withPrivateKey(privateKey).withPublicKey(builder.getPublicKey()).build();
	}

	public static AWSCredentials getCredentials(Credentials credentials) {
		String password = Passwords.getEncPassword();
		TextEncryptor enc = EncUtils.getTextEncryptor(password);
		String accessKey = credentials.getAWSAccessKeyId();
		String secretKey = credentials.getAWSSecretKey();
		if (EncUtils.isEncrypted(secretKey)) {
			secretKey = enc.decrypt(EncUtils.unwrap(secretKey));
		}
		return new BasicAWSCredentials(accessKey, secretKey);
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

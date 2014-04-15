/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.status;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.encrypt.Encryption.getDefaultEncryptor;

import java.util.List;

import org.kuali.common.aws.EncryptedAwsCredentials;
import org.kuali.common.aws.EncryptedKeyPair;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.util.encrypt.Encryptor;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class Auth {

	public static KeyPair getKeyPair(EncryptedKeyPair encrypted) {
		Encryptor encryptor = getDefaultEncryptor();
		KeyPair keyPair = encrypted.getKeyPair();
		String publicKey = encryptor.decrypt(keyPair.getPublicKey());
		String privateKey = encryptor.decrypt(keyPair.getPrivateKey());
		return KeyPair.builder(keyPair.getName()).withPublicKey(publicKey).withPrivateKey(privateKey).build();
	}

	public static AWSCredentials getCredentials(EncryptedAwsCredentials encrypted) {
		Encryptor encryptor = getDefaultEncryptor();
		String accessKey = encryptor.decrypt(encrypted.getAWSAccessKeyId());
		String secretKey = encryptor.decrypt(encrypted.getAWSSecretKey());
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	public static List<AWSCredentials> getCredentials() {
		List<AWSCredentials> list = newArrayList();
		for (EncryptedAwsCredentials credentials : EncryptedAwsCredentials.values()) {
			AWSCredentials decrypted = getCredentials(credentials);
			list.add(decrypted);
		}
		return list;

	}

}

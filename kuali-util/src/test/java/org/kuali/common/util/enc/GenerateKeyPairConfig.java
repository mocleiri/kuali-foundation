/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.enc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.kuali.common.util.enc.spring.DefaultEncryptionServiceConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jcraft.jsch.JSch;

@Configuration
@Import({ SpringServiceConfig.class, DefaultEncryptionServiceConfig.class })
public class GenerateKeyPairConfig {

	private static final String DEFAULT_NAME = "my.keypair";
	private static final int DEFAULT_SIZE = 2048;

	@Autowired
	EnvironmentService env;

	@Autowired
	EncryptionService enc;

	// This method generates a public key / private key pair suitable for use with AWS
	@Bean
	public Object execute() {
		try {
			KeyPair keyPair = getKeyPair();
			System.out.println();
			System.out.println("       name: [" + keyPair.getName() + "]");
			System.out.println(" public key: [" + keyPair.getPublicKey().get() + "]");
			System.out.println("private key: [" + keyPair.getPrivateKey().get() + "]");
			System.out.println("fingerprint: [" + keyPair.getFingerprint().get() + "]");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected KeyPair getKeyPair() throws Exception {
		String name = env.getString("key.name", DEFAULT_NAME);
		int size = env.getInteger("key.size", DEFAULT_SIZE);
		int type = com.jcraft.jsch.KeyPair.RSA;
		JSch jsch = new JSch();
		com.jcraft.jsch.KeyPair keyPair = com.jcraft.jsch.KeyPair.genKeyPair(jsch, type, size);
		String publicKey = getPublicKey(keyPair, name).trim();
		String privateKey = getPrivateKey(keyPair);
		String privateKeyEncrypted = enc.encrypt(privateKey);
		String fingerprint = keyPair.getFingerPrint();
		return new KeyPair.Builder(name).publicKey(publicKey).privateKey(privateKeyEncrypted).fingerprint(fingerprint).build();
	}

	protected String getPrivateKey(com.jcraft.jsch.KeyPair keyPair) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePrivateKey(out);
		return out.toString("UTF-8");
	}

	protected String getPublicKey(com.jcraft.jsch.KeyPair keyPair, String name) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePublicKey(out, name);
		return out.toString("UTF-8");
	}
}

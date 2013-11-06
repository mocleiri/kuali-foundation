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

import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;

@Configuration
@Import({ SpringServiceConfig.class })
public class GenerateKeyPairConfig {

	@Autowired
	EnvironmentService env;

	// The keys that Amazon EC2 uses are 1024-bit SSH-2 RSA keys. You can have up to five thousand key pairs per region.
	@Bean
	public Object execute() {
		try {
			int type = KeyPair.RSA;
			JSch jsch = new JSch();
			KeyPair keyPair = KeyPair.genKeyPair(jsch, type);
			String name = "winnie-the-pooh";
			String publicKey = getPublicKey(keyPair, name);
			String privateKey = getPrivateKey(keyPair);
			System.out.println(publicKey);
			System.out.println(privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String getPrivateKey(KeyPair keyPair) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePrivateKey(out);
		return out.toString("UTF-8");
	}

	protected String getPublicKey(KeyPair keyPair, String name) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePublicKey(out, name);
		return out.toString("UTF-8");
	}
}

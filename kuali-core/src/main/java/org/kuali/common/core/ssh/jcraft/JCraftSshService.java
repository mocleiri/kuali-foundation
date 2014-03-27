/**
 * Copyright 2014-2014 The Kuali Foundation
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
package org.kuali.common.core.ssh.jcraft;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.kuali.common.core.ssh.Algorithm;
import org.kuali.common.core.ssh.GenerateKeyPairContext;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.core.ssh.SshService;
import org.kuali.common.util.Encodings;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

public class JCraftSshService implements SshService {

	private static final String UTF8 = Encodings.UTF8;
	private static final int DSA = com.jcraft.jsch.KeyPair.DSA;
	private static final int RSA = com.jcraft.jsch.KeyPair.RSA;

	@Override
	public KeyPair generateKeyPair(GenerateKeyPairContext context) {
		checkNotNull(context, "context");
		int algorithm = (Algorithm.DSA == context.getAlgorithm()) ? DSA : RSA;
		JSch jsch = new JSch();
		com.jcraft.jsch.KeyPair keyPair = getKeyPair(jsch, algorithm, context.getSize());
		String publicKey = getPublicKey(keyPair, context.getName()).trim();
		String privateKey = getPrivateKey(keyPair);
		String fingerprint = keyPair.getFingerPrint();
		return KeyPair.builder(context.getName()).withPublicKey(publicKey).withPrivateKey(privateKey).withFingerprint(fingerprint).build();
	}

	protected com.jcraft.jsch.KeyPair getKeyPair(JSch jsch, int type, int size) {
		try {
			return com.jcraft.jsch.KeyPair.genKeyPair(jsch, type, size);
		} catch (JSchException e) {
			throw new IllegalStateException(e);
		}
	}

	protected String getPrivateKey(com.jcraft.jsch.KeyPair keyPair) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePrivateKey(out);
		return toUTF8String(out);
	}

	protected String getPublicKey(com.jcraft.jsch.KeyPair keyPair, String name) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePublicKey(out, name);
		return toUTF8String(out);
	}

	protected String toUTF8String(ByteArrayOutputStream out) {
		try {
			return out.toString(UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
}

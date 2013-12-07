package org.kuali.common.util.ssh.api.impl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.ssh.api.SshService;
import org.kuali.common.util.ssh.model.Algorithm;
import org.kuali.common.util.ssh.model.GenerateKeyPairContext;
import org.kuali.common.util.ssh.model.KeyPair;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

public class DefaultSshService implements SshService {

	private static final String UTF8 = Encodings.UTF8;

	@Override
	public KeyPair generateKeyPair(GenerateKeyPairContext context) {
		Assert.noNulls(context);
		int type = (Algorithm.DSA == context.getAlgorithm()) ? com.jcraft.jsch.KeyPair.DSA : com.jcraft.jsch.KeyPair.RSA;
		JSch jsch = new JSch();
		com.jcraft.jsch.KeyPair keyPair = getKeyPair(jsch, type, context.getSize());
		String publicKey = getPublicKey(keyPair, context.getName()).trim();
		String privateKey = getPrivateKey(keyPair);
		String fingerprint = keyPair.getFingerPrint();
		return new KeyPair.Builder(context.getName()).publicKey(publicKey).privateKey(privateKey).fingerprint(fingerprint).build();
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

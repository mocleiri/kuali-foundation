package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CheckSumUtils;

public final class KeyPair {

	public KeyPair(String name, String publicKey, String privateKey) {
		Assert.noBlanks(name, publicKey, privateKey);
		this.name = name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.fingerprint = CheckSumUtils.getSHA1Checksum(privateKey);
	}

	private final String name;
	private final String publicKey;
	private final String privateKey;
	private final String fingerprint;

	public String getName() {
		return name;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public String getFingerprint() {
		return fingerprint;
	}

}

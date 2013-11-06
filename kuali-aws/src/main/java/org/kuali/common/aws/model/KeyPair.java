package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class KeyPair {

	public KeyPair(String name, String publicKey) {
		this(name, publicKey, Optional.<String> absent());
	}

	public KeyPair(String name, String publicKey, String privateKey) {
		this(name, publicKey, Optional.of(privateKey));
	}

	public KeyPair(String name, String publicKey, Optional<String> privateKey) {
		Assert.noBlanks(name, publicKey);
		Assert.noNulls(privateKey);
		if (privateKey.isPresent()) {
			Assert.noBlanks(privateKey.get());
		}
		this.name = name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	private final String name;
	private final String publicKey;
	private final Optional<String> privateKey;

	public String getName() {
		return name;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public Optional<String> getPrivateKey() {
		return privateKey;
	}

}

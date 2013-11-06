package org.kuali.common.aws.model;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class KeyPair {

	public KeyPair(String name) {
		this(name, Optional.<String> absent());
	}

	public KeyPair(String name, String publicKey) {
		this(name, Optional.of(publicKey));
	}

	public KeyPair(String name, Optional<String> publicKey) {
		this(name, publicKey, Optional.<String> absent());
	}

	public KeyPair(String name, String publicKey, String privateKey) {
		this(name, Optional.of(publicKey), Optional.of(privateKey));
	}

	public KeyPair(String name, Optional<String> publicKey, Optional<String> privateKey) {
		Assert.noBlanks(name);
		Assert.noNulls(publicKey, privateKey);
		if (publicKey.isPresent()) {
			Assert.noBlanks(publicKey.get());
		}
		if (privateKey.isPresent()) {
			Assert.noBlanks(privateKey.get());
		}
		this.name = name;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	private final String name;
	private final Optional<String> publicKey;
	private final Optional<String> privateKey;

	public String getName() {
		return name;
	}

	public Optional<String> getPublicKey() {
		return publicKey;
	}

	public Optional<String> getPrivateKey() {
		return privateKey;
	}

}

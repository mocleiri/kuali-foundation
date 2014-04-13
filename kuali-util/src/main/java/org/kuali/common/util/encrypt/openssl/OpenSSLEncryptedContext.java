package org.kuali.common.util.encrypt.openssl;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class OpenSSLEncryptedContext {

	private final ImmutableList<Byte> salt;
	private final ImmutableList<Byte> key;
	private final ImmutableList<Byte> initVector;
	private final ImmutableList<Byte> encrypted;

	private OpenSSLEncryptedContext(Builder builder) {
		this.salt = ImmutableList.copyOf(builder.salt);
		this.encrypted = ImmutableList.copyOf(builder.encrypted);
		this.key = ImmutableList.copyOf(builder.key);
		this.initVector = ImmutableList.copyOf(builder.initVector);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<OpenSSLEncryptedContext> {

		private List<Byte> salt = newArrayList();
		private List<Byte> key = newArrayList();
		private List<Byte> initVector = newArrayList();
		private List<Byte> encrypted = newArrayList();

		public Builder withSalt(List<Byte> salt) {
			this.salt = salt;
			return this;
		}

		public Builder withEncrypted(List<Byte> encrypted) {
			this.encrypted = encrypted;
			return this;
		}

		public Builder withKey(List<Byte> key) {
			this.key = key;
			return this;
		}

		public Builder withInitVector(List<Byte> initVector) {
			this.initVector = initVector;
			return this;
		}

		@Override
		public OpenSSLEncryptedContext build() {
			return validate(new OpenSSLEncryptedContext(this));
		}

		private static OpenSSLEncryptedContext validate(OpenSSLEncryptedContext instance) {
			checkNotNull(instance.salt, "salt");
			checkNotNull(instance.key, "key");
			checkNotNull(instance.initVector, "initVector");
			checkNotNull(instance.encrypted, "encrypted");
			return instance;
		}

	}

	public ImmutableList<Byte> getSalt() {
		return salt;
	}

	public ImmutableList<Byte> getEncrypted() {
		return encrypted;
	}

	public ImmutableList<Byte> getKey() {
		return key;
	}

	public ImmutableList<Byte> getInitVector() {
		return initVector;
	}
}

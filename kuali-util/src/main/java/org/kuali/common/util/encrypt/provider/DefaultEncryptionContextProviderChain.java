package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.encrypt.Encryption.ENCRYPTION_PASSWORD_KEY;

import java.util.List;

import org.kuali.common.util.encrypt.EncryptionContext;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultEncryptionContextProviderChain implements EncryptionContextProvider {

	public DefaultEncryptionContextProviderChain() {
		this(ENCRYPTION_PASSWORD_KEY);
	}

	public DefaultEncryptionContextProviderChain(String encryptionPasswordKey) {
		this(new SystemPropertiesEncryptionContextProvider(encryptionPasswordKey), new EnvironmentVariableEncryptionContextProvider(encryptionPasswordKey),
				new SimpleMavenEncryptionContextProvider(encryptionPasswordKey));
	}

	public DefaultEncryptionContextProviderChain(EncryptionContextProvider... providers) {
		this(ImmutableList.copyOf(providers));
	}

	public DefaultEncryptionContextProviderChain(List<EncryptionContextProvider> providers) {
		this.providers = ImmutableList.copyOf(providers);
	}

	private final List<EncryptionContextProvider> providers;

	public List<EncryptionContextProvider> getProviders() {
		return providers;
	}

	@Override
	public Optional<EncryptionContext> getEncryptionContext() {
		for (EncryptionContextProvider provider : providers) {
			Optional<EncryptionContext> context = provider.getEncryptionContext();
			if (context.isPresent()) {
				return context;
			}
		}
		return absent();
	}

}

package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;

import java.util.List;

import org.kuali.common.util.encrypt.EncContext;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultEncryptionContextProviderChain implements EncryptionContextProvider {

	public DefaultEncryptionContextProviderChain() {
		this(new SystemPropertiesEncryptionContextProvider(), new EnvironmentVariableEncryptionContextProvider(), new SimpleMavenEncryptionContextProvider());
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
	public Optional<EncContext> getEncryptionContext() {
		for (EncryptionContextProvider provider : providers) {
			Optional<EncContext> context = provider.getEncryptionContext();
			if (context.isPresent()) {
				return context;
			}
		}
		return absent();
	}

}

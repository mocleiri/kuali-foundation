package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.kuali.common.util.encrypt.EncryptionContext;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultEncryptionContextProviderChain implements EncryptionContextProvider {

	public DefaultEncryptionContextProviderChain(String passwordKey, String strengthKey) {
		List<EncryptionContextProvider> providers = newArrayList();
		providers.add(new SystemPropertiesEncryptionContextProvider(passwordKey, strengthKey));
		providers.add(new EnvironmentVariableEncryptionContextProvider(passwordKey, strengthKey));
		providers.add(new SimpleMavenEncryptionContextProvider(passwordKey, strengthKey));
		this.providers = ImmutableList.copyOf(providers);
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

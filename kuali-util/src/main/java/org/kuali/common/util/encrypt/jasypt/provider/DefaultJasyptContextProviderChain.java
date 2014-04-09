package org.kuali.common.util.encrypt.jasypt.provider;

import static com.google.common.base.Optional.absent;

import java.util.List;

import org.kuali.common.util.encrypt.EncContext;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultJasyptContextProviderChain implements JasyptContextProvider {

	public DefaultJasyptContextProviderChain() {
		this(new SystemPropertiesJasyptContextProvider(), new EnvironmentVariableJasyptContextProvider(), new SimpleMavenJasyptContextProvider());
	}

	public DefaultJasyptContextProviderChain(JasyptContextProvider... providers) {
		this(ImmutableList.copyOf(providers));
	}

	public DefaultJasyptContextProviderChain(List<JasyptContextProvider> providers) {
		this.providers = ImmutableList.copyOf(providers);
	}

	private final List<JasyptContextProvider> providers;

	public List<JasyptContextProvider> getProviders() {
		return providers;
	}

	@Override
	public Optional<EncContext> getEncryptionContext() {
		for (JasyptContextProvider provider : providers) {
			Optional<EncContext> context = provider.getEncryptionContext();
			if (context.isPresent()) {
				return context;
			}
		}
		return absent();
	}

}

package org.kuali.common.util.encrypt.jasypt;

import static com.google.common.base.Optional.absent;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultJasyptContextProviderChain implements JasyptContextProvider {

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
	public Optional<JasyptContext> getJasyptContext() {
		for (JasyptContextProvider provider : providers) {
			Optional<JasyptContext> context = provider.getJasyptContext();
			if (context.isPresent()) {
				return context;
			}
		}
		return absent();
	}

}

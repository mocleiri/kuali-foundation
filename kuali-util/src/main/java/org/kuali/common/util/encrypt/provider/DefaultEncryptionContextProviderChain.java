package org.kuali.common.util.encrypt.provider;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.kuali.common.util.encrypt.EncryptionContext;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class DefaultEncryptionContextProviderChain implements EncryptionContextProvider {

	private static final String ENC_PASSWORD_KEY = "enc.password";
	private static final String ENC_STRENGTH_KEY = "enc.strength";
	private static final String ENC_MAVEN_ALTERNATE_PASSWORD_KEY = "enc.pwd";

	public DefaultEncryptionContextProviderChain() {
		List<EncryptionContextProvider> providers = newArrayList();
		providers.add(new SystemPropertiesEncryptionContextProvider(ENC_PASSWORD_KEY, ENC_STRENGTH_KEY));
		providers.add(new EnvironmentVariableEncryptionContextProvider(toEnvKey(ENC_PASSWORD_KEY), toEnvKey(ENC_STRENGTH_KEY)));
		providers.add(new FileEncryptionContextProvider());
		providers.add(new MavenEncryptionContextProvider(ENC_MAVEN_ALTERNATE_PASSWORD_KEY, ENC_STRENGTH_KEY));
		providers.add(new MavenEncryptionContextProvider(ENC_PASSWORD_KEY, ENC_STRENGTH_KEY));
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

	protected static String toEnvKey(String key) {
		return key.replace('.', '_').toUpperCase();
	}

}

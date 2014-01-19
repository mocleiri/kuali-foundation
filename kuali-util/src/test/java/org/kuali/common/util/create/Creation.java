package org.kuali.common.util.create;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.kuali.common.util.create.bootstrap.GenericBootstrap;
import org.kuali.common.util.create.spi.BootstrapState;
import org.kuali.common.util.create.spi.CreationProvider;
import org.kuali.common.util.serviceloader.ServiceProvider;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class Creation {

	private static Creator instance;

	public synchronized static Creator getDefaultCreator() {
		if (instance == null) {
			CreatorFactory factory = buildDefaultCreatorFactory();
			instance = factory.getCreator();
		}
		return instance;
	}

	public static CreatorFactory buildDefaultCreatorFactory() {
		return byDefaultProvider().configure().buildCreatorFactory();
	}

	public static GenericBootstrap byDefaultProvider() {
		return new GenericBootstrapImpl();
	}

	private static class DefaultCreationProviderResolver implements CreationProviderResolver {

		@Override
		public List<CreationProvider<?>> getCreationProviders() {
			List<CreationProvider<?>> providers = Lists.newArrayList();
			for (CreationProvider<?> element : ServiceProvider.getAll(CreationProvider.class)) {
				providers.add(element);
			}
			return providers;
		}
	}

	private static class GenericBootstrapImpl implements GenericBootstrap, BootstrapState {

		private Optional<CreationProviderResolver> resolver;
		private CreationProviderResolver defaultResolver;

		@Override
		public GenericBootstrap providerResolver(CreationProviderResolver resolver) {
			this.resolver = Optional.of(resolver);
			return this;
		}

		@Override
		public Optional<CreationProviderResolver> getCreationProviderResolver() {
			return resolver;
		}

		@Override
		public CreationProviderResolver getDefaultCreationProviderResolver() {
			if (defaultResolver == null) {
				defaultResolver = new DefaultCreationProviderResolver();
			}
			return defaultResolver;
		}

		@Override
		public Configuration<?> configure() {
			CreationProviderResolver resolver = this.resolver.isPresent() ? this.resolver.get() : getDefaultCreationProviderResolver();
			List<CreationProvider<?>> providers = resolver.getCreationProviders();
			checkState(providers.size() > 0, "Unable to create a Configuration. No creation provider was found.  Add a provider to your classpath.");
			CreationProvider<?> provider = providers.get(0);
			return provider.createGenericConfiguration(this);
		}
	}
}

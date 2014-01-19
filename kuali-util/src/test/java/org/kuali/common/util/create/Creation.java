package org.kuali.common.util.create;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.kuali.common.util.create.bootstrap.GenericBootstrap;
import org.kuali.common.util.create.spi.BootstrapState;
import org.kuali.common.util.create.spi.CreationProvider;
import org.kuali.common.util.serviceloader.ServiceProvider;

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

	private static class GenericBootstrapImpl implements GenericBootstrap, BootstrapState {

		private CreationProviderResolver resolver = new DefaultCreationProviderResolver();

		@Override
		public CreationProviderResolver getCreationProviderResolver() {
			return resolver;
		}

		@Override
		public GenericBootstrap providerResolver(CreationProviderResolver resolver) {
			this.resolver = resolver;
			return this;
		}

		@Override
		public Configuration<?> configure() {
			checkNotNull(resolver, "'resolver' cannot be null");
			List<CreationProvider<?>> providers = getCreationProviderResolver().getCreationProviders();
			checkState(providers.size() > 0, "Unable to create a Configuration. No creation provider was found.  Add a provider to your classpath.");
			CreationProvider<?> provider = providers.get(0);
			return provider.createGenericConfiguration(this);
		}

	}

	private static class DefaultCreationProviderResolver implements CreationProviderResolver {

		private List<CreationProvider<?>> providers;

		@Override
		public synchronized List<CreationProvider<?>> getCreationProviders() {
			if (this.providers == null) {
				List<CreationProvider<?>> providers = Lists.newArrayList();
				for (CreationProvider<?> provider : ServiceProvider.getAll(CreationProvider.class)) {
					providers.add(provider);
				}
				this.providers = providers;
			}
			return this.providers;
		}
	}

}

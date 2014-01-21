package org.kuali.common.util.create.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Binding;
import org.kuali.common.util.create.CreationProviderResolver;
import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.spi.BootstrapState;
import org.kuali.common.util.create.spi.ConfigurationState;
import org.kuali.common.util.create.spi.CreationProvider;
import org.kuali.common.util.validate.Validation;

public final class ConfigurationImpl implements KualiCreationConfiguration, ConfigurationState {

	private final Validator validator;
	private final BinderService binder;
	private final CreationProviderResolver providerResolver;

	@Override
	public CreatorFactory buildCreatorFactory() {
		List<CreationProvider<?>> providers = providerResolver.getCreationProviders();
		checkState(providers.size() > 0, "No providers were found");
		CreationProvider<?> provider = providers.get(0);
		return provider.buildCreatorFactory(this);
	}

	@Override
	public Validator getValidator() {
		return validator;
	}

	@Override
	public BinderService getBinderService() {
		return binder;
	}

	private ConfigurationImpl(Builder builder) {
		this.validator = builder.validator;
		this.binder = builder.binder;
		this.providerResolver = builder.providerResolver;
	}

	public static ConfigurationImpl create(BootstrapState state) {
		return builder(state).build();
	}

	public static Builder builder(BootstrapState state) {
		return new Builder(state);
	}

	public static class Builder implements org.kuali.common.util.build.Builder<ConfigurationImpl> {

		private final CreationProviderResolver providerResolver;
		private Validator validator = Validation.getDefaultValidator();
		private BinderService binder = Binding.getDefaultBinderService();

		public Builder(BootstrapState state) {
			if (state.getCreationProviderResolver().isPresent()) {
				this.providerResolver = state.getCreationProviderResolver().get();
			} else {
				this.providerResolver = state.getDefaultCreationProviderResolver();
			}
		}

		public Builder validator(Validator validator) {
			this.validator = validator;
			return this;
		}

		public Builder binder(BinderService binder) {
			this.binder = binder;
			return this;
		}

		@Override
		public ConfigurationImpl build() {
			ConfigurationImpl instance = new ConfigurationImpl(this);
			validate(instance);
			return instance;
		}

		private static void validate(ConfigurationImpl instance) {
			checkNotNull(instance.validator, "'validator' cannot be null");
			checkNotNull(instance.binder, "'binder' cannot be null");
		}

		public Validator getValidator() {
			return validator;
		}

		public void setValidator(Validator validator) {
			this.validator = validator;
		}

		public BinderService getBinder() {
			return binder;
		}

		public void setBinder(BinderService binder) {
			this.binder = binder;
		}

		public CreationProviderResolver getProviderResolver() {
			return providerResolver;
		}
	}

	public BinderService getBinder() {
		return binder;
	}

	public CreationProviderResolver getProviderResolver() {
		return providerResolver;
	}

}

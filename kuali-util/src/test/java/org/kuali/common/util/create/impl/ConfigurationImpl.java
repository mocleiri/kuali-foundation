package org.kuali.common.util.create.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Binding;
import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.spi.ConfigurationState;
import org.kuali.common.util.validate.Validation;

public final class ConfigurationImpl implements KualiCreationConfiguration, ConfigurationState {

	private final Validator validator;
	private final BinderService binder;

	@Override
	public CreatorFactory buildCreatorFactory() {
		return null;
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
	}

	public static class Builder implements org.kuali.common.util.build.Builder<ConfigurationImpl> {

		private Validator validator = Validation.getDefaultValidator();
		private BinderService binder = Binding.getDefaultBinderService();

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
	}

}

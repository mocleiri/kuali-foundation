package org.kuali.common.util.create.impl;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.spi.ConfigurationState;
import org.kuali.common.util.validate.Validation;

public class ConfigurationImpl implements KualiCreationConfiguration, ConfigurationState {

	@Override
	public CreatorFactory buildCreatorFactory() {
		return null;
	}

	@Override
	public Validator getValidator() {
		return Validation.getDefaultValidator();
	}

	@Override
	public BinderService getBinderService() {
		return null;
	}

}

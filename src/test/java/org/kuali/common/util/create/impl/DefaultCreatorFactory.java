package org.kuali.common.util.create.impl;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.Binder;
import org.kuali.common.util.create.Creator;
import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.spi.ConfigurationState;

public class DefaultCreatorFactory implements CreatorFactory {

	private final Validator validator;
	private final Binder binder;

	public DefaultCreatorFactory(ConfigurationState state) {
		this.validator = state.getValidator();
		this.binder = state.getBinderService();
	}

	@Override
	public Creator getCreator() {
		return DefaultCreator.create(validator, binder);
	}

}

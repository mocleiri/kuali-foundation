package org.kuali.common.util.create.spi;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.BinderService;

public interface ConfigurationState {

	Validator getValidator();

	BinderService getBinderService();

}

package org.kuali.common.util.create.spi;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.Binder;

public interface ConfigurationState {

	Validator getValidator();

	Binder getBinderService();

}

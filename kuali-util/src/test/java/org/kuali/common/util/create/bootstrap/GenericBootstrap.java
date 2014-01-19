package org.kuali.common.util.create.bootstrap;

import javax.validation.Configuration;

import org.kuali.common.util.create.CreationProviderResolver;

public interface GenericBootstrap {

	GenericBootstrap providerResolver(CreationProviderResolver resolver);

	Configuration<?> configure();
}

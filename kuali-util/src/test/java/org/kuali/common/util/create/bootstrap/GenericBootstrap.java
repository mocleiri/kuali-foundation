package org.kuali.common.util.create.bootstrap;

import org.kuali.common.util.create.Configuration;
import org.kuali.common.util.create.CreationProviderResolver;

public interface GenericBootstrap {

	GenericBootstrap providerResolver(CreationProviderResolver resolver);

	Configuration<?> configure();

}

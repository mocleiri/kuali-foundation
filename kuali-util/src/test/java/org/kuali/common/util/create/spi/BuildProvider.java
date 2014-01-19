package org.kuali.common.util.create.spi;

import org.kuali.common.util.create.BuildServiceFactory;
import org.kuali.common.util.create.Configuration;

public interface BuildProvider<T extends Configuration<T>> {

	T createSpecializedConfiguration(BootstrapState state);

	Configuration<?> createGenericConfiguration(BootstrapState state);

	BuildServiceFactory buildBuilderFactory(ConfigurationState configurationState);
}

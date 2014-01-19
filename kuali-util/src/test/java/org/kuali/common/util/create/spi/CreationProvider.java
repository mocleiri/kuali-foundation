package org.kuali.common.util.create.spi;

import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.Configuration;

public interface CreationProvider<T extends Configuration<T>> {

	T createSpecializedConfiguration(BootstrapState state);

	Configuration<?> createGenericConfiguration(BootstrapState state);

	CreatorFactory buildBuilderFactory(ConfigurationState configurationState);
}

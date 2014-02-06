package org.kuali.common.util.create.spi;

import org.kuali.common.util.create.Configuration;
import org.kuali.common.util.create.CreatorFactory;

public interface CreationProvider<T extends Configuration<T>> {

	T createSpecializedConfiguration(BootstrapState state);

	Configuration<?> createGenericConfiguration(BootstrapState state);

	CreatorFactory buildCreatorFactory(ConfigurationState state);

}

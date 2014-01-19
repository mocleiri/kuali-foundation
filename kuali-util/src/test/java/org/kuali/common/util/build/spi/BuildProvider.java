package org.kuali.common.util.build.spi;

import org.kuali.common.util.build.BuildServiceFactory;
import org.kuali.common.util.build.Configuration;

public interface BuildProvider<T extends Configuration<T>> {

	T createSpecializedConfiguration(BootstrapState state);

	Configuration<?> createGenericConfiguration(BootstrapState state);

	BuildServiceFactory buildBuilderFactory(ConfigurationState configurationState);
}

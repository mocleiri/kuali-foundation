package org.kuali.common.util.build.spi;

import org.kuali.common.util.build.BuilderFactory;
import org.kuali.common.util.build.Configuration;

public interface BuildProvider<T extends Configuration<T>> {

	T createSpecializedConfiguration(BootstrapState state);

	Configuration<?> createGenericConfiguration(BootstrapState state);

	BuilderFactory buildBuilderFactory(ConfigurationState configurationState);
}

package org.kuali.common.util.create.impl;

import org.kuali.common.util.create.Configuration;
import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.spi.BootstrapState;
import org.kuali.common.util.create.spi.ConfigurationState;
import org.kuali.common.util.create.spi.CreationProvider;

public class KualiCreator implements CreationProvider<KualiCreationConfiguration> {

	@Override
	public KualiCreationConfiguration createSpecializedConfiguration(BootstrapState state) {
		return null; // KualiCreationConfiguration.class.cast(new ConfigurationImpl(this));
	}

	@Override
	public Configuration<?> createGenericConfiguration(BootstrapState state) {
		return null;
	}

	@Override
	public CreatorFactory buildCreatorFactory(ConfigurationState state) {
		return null;
	}

}

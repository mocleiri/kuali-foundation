package org.kuali.common.util.create.impl;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.create.Configuration;
import org.kuali.common.util.create.CreatorFactory;
import org.kuali.common.util.create.spi.BootstrapState;
import org.kuali.common.util.create.spi.ConfigurationState;
import org.kuali.common.util.create.spi.CreationProvider;

@MetaInfServices
public class KualiCreator implements CreationProvider<KualiCreationConfiguration> {

	@Override
	public KualiCreationConfiguration createSpecializedConfiguration(BootstrapState state) {
		return null; // KualiCreationConfiguration.class.cast(new ConfigurationImpl(this));
	}

	@Override
	public Configuration<?> createGenericConfiguration(BootstrapState state) {
		return ConfigurationImpl.create(state);
	}

	@Override
	public CreatorFactory buildCreatorFactory(ConfigurationState state) {
		return null;
	}

}

package org.kuali.common.util.create.spi;

import org.kuali.common.util.create.CreationProviderResolver;

import com.google.common.base.Optional;

public interface BootstrapState {

	Optional<CreationProviderResolver> getCreationProviderResolver();

	CreationProviderResolver getDefaultCreationProviderResolver();

}

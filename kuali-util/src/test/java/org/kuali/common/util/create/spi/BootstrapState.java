package org.kuali.common.util.create.spi;

import org.kuali.common.util.create.CreationProviderResolver;

import com.google.common.base.Optional;

public interface BootstrapState {

	/**
	 * Optional user defined {@code CreationProviderResolver} strategy instance.
	 */
	Optional<CreationProviderResolver> getCreationProviderResolver();

	/**
	 * Specification default {@code CreationProviderResolver} strategy instance.
	 */
	CreationProviderResolver getDefaultCreationProviderResolver();

}

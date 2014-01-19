package org.kuali.common.util.create;

import java.util.List;

import org.kuali.common.util.create.spi.CreationProvider;

public interface CreationProviderResolver {

	List<CreationProvider<?>> getCreationProviders();

}

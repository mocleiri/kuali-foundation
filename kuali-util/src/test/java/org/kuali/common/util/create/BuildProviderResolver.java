package org.kuali.common.util.create;

import java.util.List;

import org.kuali.common.util.create.spi.BuildProvider;

public interface BuildProviderResolver {

	List<BuildProvider<?>> getBuildProviders();

}

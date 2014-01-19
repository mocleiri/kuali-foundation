package org.kuali.common.util.build;

import java.util.List;

import org.kuali.common.util.build.spi.BuildProvider;

public interface BuildProviderResolver {

	List<BuildProvider<?>> getBuildProviders();

}

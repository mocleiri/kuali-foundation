package org.kuali.common.devops.metadata.logic;

import java.util.List;

import org.kuali.common.devops.metadata.model.EnvironmentMetadata;

public interface EnvironmentMetadataService {

	List<EnvironmentMetadata> getMetadata(List<String> fqdns);

}

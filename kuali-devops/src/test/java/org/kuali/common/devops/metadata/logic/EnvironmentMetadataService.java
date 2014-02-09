package org.kuali.common.devops.metadata.logic;

import java.util.List;

import org.kuali.common.devops.metadata.model.EnvironmentMetadata;

public interface EnvironmentMetadataService {

	EnvironmentMetadata getMetadata(String fqdn);

	List<EnvironmentMetadata> getMetadata(List<String> fqdns);

}

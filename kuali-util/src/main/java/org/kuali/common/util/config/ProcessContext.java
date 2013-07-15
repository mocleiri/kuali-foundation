package org.kuali.common.util.config;

import java.util.List;

public interface ProcessContext {

	String getName();

	String getGroupId();

	String getArtifactId();

	List<String> getConfigIds();

}

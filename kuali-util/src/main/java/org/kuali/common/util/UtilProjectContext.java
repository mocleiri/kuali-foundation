package org.kuali.common.util;

import java.util.Collections;
import java.util.List;

public class UtilProjectContext implements ProjectContext {

	private static ProjectContext instance = new UtilProjectContext();

	private UtilProjectContext() {
		super();
	}

	public static ProjectContext getInstance() {
		return instance;
	}

	@Override
	public String getGroupId() {
		return ProjectUtils.KUALI_COMMON_GROUP_ID;
	}

	@Override
	public String getArtifactId() {
		return "kuali-util";
	}

	@Override
	public List<String> getPropertyLocations() {
		return Collections.emptyList();
	}

}
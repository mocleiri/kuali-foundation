package org.kuali.common.util;

import java.util.Collections;
import java.util.List;

public abstract class AbstractProjectContext implements ProjectContext {

	@Override
	public String getGroupId() {
		return ProjectUtils.KUALI_COMMON_GROUP_ID;
	}

	@Override
	public List<String> getPropertyLocations() {
		return Collections.emptyList();
	}

}

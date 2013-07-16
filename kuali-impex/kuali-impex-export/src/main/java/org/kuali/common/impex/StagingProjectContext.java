package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class StagingProjectContext extends DefaultProjectContext {

	public StagingProjectContext() {
		super(ExportProjectConstants.ARTIFACT_ID, getLocations());
	}

	protected static List<String> getLocations() {
		String prefix = ProjectUtils.getCommonClassPathPrefix(ExportProjectConstants.ARTIFACT_ID);
		List<String> locations = new ArrayList<String>();
		locations.add(prefix + "/common.properties");
		locations.add(prefix + "/dump.properties");
		locations.add(prefix + "/staging.properties");
		return locations;
	}

}

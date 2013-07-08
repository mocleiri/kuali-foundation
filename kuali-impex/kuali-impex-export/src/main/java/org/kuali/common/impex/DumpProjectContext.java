package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class DumpProjectContext extends DefaultProjectContext {

	private static final List<String> LOCATIONS = getLocations();

	public DumpProjectContext() {
		super(Constants.ARTIFACT_ID, LOCATIONS);
	}

	private static final List<String> getLocations() {
		String prefix = ProjectUtils.getCommonClassPathPrefix(Constants.ARTIFACT_ID);
		List<String> locations = new ArrayList<String>();
		locations.add(prefix + "/common.properties");
		locations.add(prefix + "/dump.properties");
		return locations;
	}

}

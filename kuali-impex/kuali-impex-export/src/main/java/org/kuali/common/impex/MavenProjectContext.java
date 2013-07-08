package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class MavenProjectContext extends DefaultProjectContext {

	public MavenProjectContext() {
		super(Constants.ARTIFACT_ID, getLocations());
	}

	protected static List<String> getLocations() {
		String prefix = ProjectUtils.getCommonClassPathPrefix(Constants.ARTIFACT_ID);
		List<String> locations = new ArrayList<String>();
		locations.add(prefix + "/maven.properties");
		return locations;
	}

}

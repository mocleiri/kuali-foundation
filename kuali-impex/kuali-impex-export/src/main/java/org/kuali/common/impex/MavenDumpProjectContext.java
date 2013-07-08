package org.kuali.common.impex;

import java.util.Arrays;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class MavenDumpProjectContext extends DefaultProjectContext {

	private static final String LOCATION = ProjectUtils.getCommonClassPathPrefix(Constants.ARTIFACT_ID) + "/mavendump.properties";

	public MavenDumpProjectContext() {
		super(Constants.ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}

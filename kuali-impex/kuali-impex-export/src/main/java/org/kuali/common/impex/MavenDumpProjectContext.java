package org.kuali.common.impex;

import java.util.Arrays;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class MavenDumpProjectContext extends DefaultProjectContext {

	public static final String ARTIFACT_ID = Constants.ARTIFACT_ID;
	private static final String LOCATION = ProjectUtils.getCommonClassPathPrefix(ARTIFACT_ID) + "/maven.properties";

	public MavenDumpProjectContext() {
		super(ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}

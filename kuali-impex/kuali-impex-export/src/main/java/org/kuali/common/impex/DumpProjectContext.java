package org.kuali.common.impex;

import java.util.Arrays;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class DumpProjectContext extends DefaultProjectContext {

	private static final String LOCATION = ProjectUtils.getCommonClassPathPrefix(Constants.ARTIFACT_ID) + "/dump.properties";

	public DumpProjectContext() {
		super(Constants.ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}

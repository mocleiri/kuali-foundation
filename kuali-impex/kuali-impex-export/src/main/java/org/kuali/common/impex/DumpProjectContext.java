package org.kuali.common.impex;

import java.util.Arrays;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectUtils;

public class DumpProjectContext extends DefaultProjectContext {

	public static final String ARTIFACT_ID = "kuali-impex-export";
	private static final String LOCATION = ProjectUtils.getCommonClassPathPrefix(ARTIFACT_ID) + "/dump.properties";

	public DumpProjectContext() {
		super(ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}

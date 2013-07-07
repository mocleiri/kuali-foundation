package org.kuali.common.impex;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.MavenConstants;
import org.kuali.common.util.Str;

public class DumpProjectContext extends DefaultProjectContext {

	private static final String ARTIFACT_ID = "kuali-impex-export";
	private static final String LOCATION = "classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + ARTIFACT_ID + "/dump.properties";

	public DumpProjectContext() {
		super(ARTIFACT_ID, LOCATION);
	}

}

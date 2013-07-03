package org.kuali.common.util;

import java.util.Arrays;

public class MpxProjectContext extends DefaultProjectContext {

	private static final String ARTIFACT_ID = ProjectUtils.KUALI_UTIL_ARTIFACT_ID;
	private static final String LOCATION = "classpath:org/kuali/common/kuali-util/mpx/metainf.properties";

	public MpxProjectContext() {
		super(ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}

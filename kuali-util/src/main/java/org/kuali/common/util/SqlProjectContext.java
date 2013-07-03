package org.kuali.common.util;

import java.util.Arrays;

public class SqlProjectContext extends DefaultProjectContext {

	private static final String ARTIFACT_ID = ProjectUtils.KUALI_UTIL_ARTIFACT_ID;
	private static final String LOCATION = "classpath:org/kuali/common/kuali-util/sql/metainf.properties";

	public SqlProjectContext() {
		super(ARTIFACT_ID, Arrays.asList(LOCATION));
	}

}

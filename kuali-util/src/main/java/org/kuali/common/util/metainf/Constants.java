package org.kuali.common.util.metainf;

import org.kuali.common.util.ProjectUtils;

public final class Constants {

	private Constants() {
		super();
	}

	public static final String ARTIFACT_ID = ProjectUtils.KUALI_UTIL_ARTIFACT_ID;
	public static final String FEATURE_ID = "metainf";
	
	public static final String PROPERTIES_LOCATION_PREFIX = ProjectUtils.getCommonClassPathPrefix(ARTIFACT_ID) + "/" + FEATURE_ID;
	public static final String COMMON_PROPERTIES_LOCATION = PROPERTIES_LOCATION_PREFIX + "/common.properties";

}

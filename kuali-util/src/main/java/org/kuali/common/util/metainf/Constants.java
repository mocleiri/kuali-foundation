package org.kuali.common.util.metainf;

import org.kuali.common.util.ProjectUtils;

public final class Constants {

	private Constants() {
		super();
	}

	public static final String ARTIFACT_ID = ProjectUtils.KUALI_UTIL_ARTIFACT_ID;

	public static final String METAINF = "metainf";
	public static final String LOCATION_PREFIX = ProjectUtils.getCommonClassPathPrefix(ARTIFACT_ID) + "/" + METAINF;

	/**
	 * These default values help ensure backwards compatibility with deprecated config classes using MetaInfContext
	 */
	public static final String DEFAULT_PREFIX = "classpath:";
	public static final boolean DEFAULT_SORT = true;

}

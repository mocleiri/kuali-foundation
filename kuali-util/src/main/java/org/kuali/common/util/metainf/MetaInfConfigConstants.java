package org.kuali.common.util.metainf;

import java.util.List;

import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.config.ConfigConstants;
import org.kuali.common.util.config.ConfigUtils;

public class MetaInfConfigConstants {

	// Shorthand for GroupId + ArtifactId
	private static final String GA = ConfigUtils.getId(KualiProjectConstants.COMMON_GROUP_ID, KualiProjectConstants.UTIL_ARTIFACT_ID);

	public static final String METAINF = "metainf";
	public static final String SQL = "sql";
	public static final String MPX = "mpx";

	public static final String SQL_CONTEXT_ID = ConfigUtils.getId(METAINF, SQL);
	public static final String SQL_BUILD_CONTEXT_ID = ConfigUtils.getId(SQL_CONTEXT_ID, ConfigConstants.BUILD);
	public static final List<String> SQL_CONFIG_IDS = ConfigUtils.unmodifiableSingleElementList(GA, SQL_CONTEXT_ID);
	public static final List<String> SQL_BUILD_CONFIG_IDS = ConfigUtils.unmodifiableSingleElementList(GA, SQL_BUILD_CONTEXT_ID);

	public static final String MPX_CONTEXT_ID = ConfigUtils.getId(METAINF, MPX);
	public static final String MPX_BUILD_CONTEXT_ID = ConfigUtils.getId(MPX_CONTEXT_ID, ConfigConstants.BUILD);
	public static final List<String> MPX_CONFIG_IDS = ConfigUtils.unmodifiableSingleElementList(GA, MPX_CONTEXT_ID);
	public static final List<String> MPX_BUILD_CONFIG_IDS = ConfigUtils.unmodifiableSingleElementList(GA, MPX_BUILD_CONTEXT_ID);

}

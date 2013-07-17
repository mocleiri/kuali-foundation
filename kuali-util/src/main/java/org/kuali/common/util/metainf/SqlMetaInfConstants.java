package org.kuali.common.util.metainf;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.Str;
import org.kuali.common.util.config.ConfigConstants;

public class SqlMetaInfConstants {

	// Shorthand for GroupId + ArtifactId
	private static final String GA = Str.getId(KualiProjectConstants.COMMON_GROUP_ID, KualiProjectConstants.UTIL_ARTIFACT_ID);

	public static final String METAINF = "metainf";
	public static final String SQL = "sql";
	public static final String MPX = "mpx";

	public static final String CONTEXT_ID = Str.getId(METAINF, SQL);
	public static final String CONFIG_ID = Str.getId(GA, CONTEXT_ID);
	public static final String BUILD_CONTEXT_ID = Str.getId(CONTEXT_ID, ConfigConstants.BUILD);
	public static final String BUILD_CONFIG_ID = Str.getId(GA, BUILD_CONTEXT_ID);
	public static final List<String> CONFIG_IDS = CollectionUtils.unmodifiableList(CONFIG_ID);
	public static final List<String> BUILD_CONFIG_IDS = CollectionUtils.unmodifiableList(BUILD_CONFIG_ID);

}

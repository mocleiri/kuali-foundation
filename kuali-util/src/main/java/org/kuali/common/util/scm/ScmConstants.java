package org.kuali.common.util.scm;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.Str;

public class ScmConstants {

	// Shorthand for GroupId + ArtifactId
	private static final String GA = Str.getId(KualiProjectConstants.COMMON_GROUP_ID, KualiProjectConstants.UTIL_ARTIFACT_ID);

	public static final String SCM = "scm";

	public static final String CONTEXT_ID = SCM;
	public static final String CONFIG_ID = Str.getId(GA, CONTEXT_ID);
	public static final List<String> CONFIG_IDS = CollectionUtils.unmodifiableList(CONFIG_ID);

}

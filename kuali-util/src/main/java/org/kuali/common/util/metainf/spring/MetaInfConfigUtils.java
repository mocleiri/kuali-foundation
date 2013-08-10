package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.metainf.model.ScanContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.project.model.Build;

public class MetaInfConfigUtils {

	public static ScanContext getScanContext(Build build) {
		return null;
	}

	public static String getKey(MetaInfGroup group, String prefix, String suffix) {
		return MetaInfUtils.PROPERTY_PREFIX + "." + prefix + "." + group.name().toLowerCase() + "." + suffix;
	}

	public static String getIncludesKey(MetaInfGroup group, String prefix) {
		return getKey(group, prefix, "includes");
	}

	public static String getExcludesKey(MetaInfGroup group, String prefix) {
		return getKey(group, prefix, "excludes");
	}

}

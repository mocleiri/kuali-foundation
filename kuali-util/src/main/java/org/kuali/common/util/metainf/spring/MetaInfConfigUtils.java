package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.Assert;
import org.kuali.common.util.metainf.model.ScanContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.project.model.Build;

public class MetaInfConfigUtils {

	private static final String INCLUDES = "includes";
	private static final String EXCLUDES = "includes";

	public static ScanContext getScanContext(Build build) {
		return null;
	}

	/**
	 * <code>metainf.[prefix].[group].includes</code>
	 */
	public static String getKey(MetaInfGroup group, String prefix, String suffix) {
		Assert.noNulls(prefix, suffix);
		StringBuilder sb = new StringBuilder();
		sb.append(MetaInfUtils.PROPERTY_PREFIX);
		sb.append(".");
		sb.append(prefix);
		if (group != null) {
			sb.append(".");
			sb.append(group.name().toLowerCase());
		}
		sb.append(".");
		sb.append(suffix);
		return sb.toString();
	}

	/**
	 * <code>metainf.[prefix].includes</code>
	 */
	public static String getIncludesKey(String prefix) {
		return getKey(null, prefix, INCLUDES);
	}

	/**
	 * <code>metainf.[prefix].excludes</code>
	 */
	public static String getExcludesKey(String prefix) {
		return getKey(null, prefix, EXCLUDES);
	}

	/**
	 * <code>metainf.[prefix].[group].includes</code>
	 */
	public static String getIncludesKey(MetaInfGroup group, String prefix) {
		return getKey(group, prefix, INCLUDES);
	}

	/**
	 * <code>metainf.[prefix].[group].excludes</code>
	 */
	public static String getExcludesKey(MetaInfGroup group, String prefix) {
		return getKey(group, prefix, EXCLUDES);
	}

}

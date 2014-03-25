package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.Assert;
import org.kuali.common.util.metainf.service.MetaInfUtils;

public class MetaInfConfigUtils {

	private static final String INCLUDES = "includes";
	private static final String EXCLUDES = "excludes";

	/**
	 * <code>group</code> is optional
	 * 
	 * <pre>
	 * metainf.[prefix].[group].[suffix]
	 * 
	 * metainf.[prefix].[suffix]
	 * </pre>
	 */
	public static String getKey(String prefix, MetaInfGroup group, String suffix) {
		Assert.noNullsWithMsg("prefix and suffix are required", prefix, suffix);
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
		return getKey(prefix, null, INCLUDES);
	}

	/**
	 * <code>metainf.[prefix].excludes</code>
	 */
	public static String getExcludesKey(String prefix) {
		return getKey(prefix, null, EXCLUDES);
	}

	/**
	 * <code>metainf.[prefix].[group].includes</code>
	 */
	public static String getIncludesKey(MetaInfGroup group, String prefix) {
		return getKey(prefix, group, INCLUDES);
	}

	/**
	 * <code>metainf.[prefix].[group].excludes</code>
	 */
	public static String getExcludesKey(MetaInfGroup group, String prefix) {
		return getKey(prefix, group, EXCLUDES);
	}

}

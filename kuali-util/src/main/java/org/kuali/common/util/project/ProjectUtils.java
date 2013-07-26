package org.kuali.common.util.project;

import org.kuali.common.util.Str;

public class ProjectUtils {

	private static final String ENCODING_KEY = "project.encoding";
	private static final String CLASSPATH = "classpath:";

	public static String getEncoding(Project project) {
		return project.getProperties().getProperty(ENCODING_KEY);
	}

	/**
	 * Given a project, return a resource friendly prefix.
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util = org/kuali/common/kuali-util
	 * </pre>
	 */
	public static String getResourcePath(Project project) {
		return Str.getPath(project.getGroupId()) + "/" + project.getArtifactId();
	}

	/**
	 * Given a project, return a classpath prefix
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util = classpath:org/kuali/common/kuali-util
	 * </pre>
	 */
	public static String getClassPathPrefix(Project project) {
		return CLASSPATH + getResourcePath(project);
	}

}

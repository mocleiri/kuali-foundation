package org.kuali.common.util.metainf.service;

import org.kuali.common.util.Str;
import org.kuali.common.util.project.Project;
import org.springframework.util.ResourceUtils;

public class MetaInfUtils {

	public static final String RESOURCES = "resources";
	public static final String CLASSPATH = ResourceUtils.CLASSPATH_URL_PREFIX;
	public static final String METAINF = "META-INF";

	/**
	 * <code>META-INF/org/kuali/util</code>
	 */
	protected String getGroupPrefix(Project project) {
		return METAINF + "/" + Str.getPath(project.getGroupId());
	}

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	protected String getResourcePrefix(Project project) {
		return getGroupPrefix(project) + "/" + project.getArtifactId();
	}

	/**
	 * <code>classpath:META-INF</code>
	 */
	protected String getClasspathPrefix() {
		return CLASSPATH + METAINF;
	}

}

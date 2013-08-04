package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.Str;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ResourceUtils;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class MetaInfCommonConfig {

	public static final String RESOURCES = "resources";
	public static final String CLASSPATH = ResourceUtils.CLASSPATH_URL_PREFIX;
	public static final String METAINF = "META-INF";
	public static final String DATA_FILENAME = "data." + RESOURCES;
	public static final String SCHEMA_FILENAME = "schema." + RESOURCES;
	public static final String CONSTRAINTS_FILENAME = "constraints." + RESOURCES;
	public static final String OTHER_FILENAME = "other." + RESOURCES;

	@Autowired
	Project project;

	/**
	 * <code>META-INF/org/kuali/util</code>
	 */
	protected String getGroupPrefix() {
		return METAINF + "/" + Str.getPath(project.getGroupId());
	}

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	protected String getResourcePrefix() {
		return getGroupPrefix() + "/" + project.getArtifactId();
	}

	/**
	 * <code>classpath:META-INF</code>
	 */
	protected String getClasspathPrefix() {
		return CLASSPATH + METAINF;
	}

}

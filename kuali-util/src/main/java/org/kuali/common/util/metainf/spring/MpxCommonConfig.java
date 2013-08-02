package org.kuali.common.util.metainf.spring;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Str;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class MpxCommonConfig {

	public static final String RESOURCES = "resources";
	public static final String CLASSPATH = "classpath:";
	public static final String METAINF = "META-INF";

	@Autowired
	Project project;

	public static final List<String> INCLUDES = Arrays.asList("**/*.mpx");

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
	protected String getMetaInfClasspathPrefix() {
		return CLASSPATH + METAINF;
	}

}

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

	private static final String CLASSPATH = "classpath:";
	private static final String METAINF = "META-INF";

	@Autowired
	Project project;

	public static final List<String> INCLUDES = Arrays.asList("**/*.mpx");

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	protected String getResourcePrefix() {
		return METAINF + "/" + Str.getPath(project.getGroupId()) + "/" + project.getArtifactId();
	}

	/**
	 * <code>classpath:META-INF</code>
	 */
	protected String getMetaInfClasspathPrefix() {
		return CLASSPATH + METAINF;
	}

}

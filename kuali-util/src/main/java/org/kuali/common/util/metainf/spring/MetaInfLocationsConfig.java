package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.properties.ImmutableLocation;
import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class MetaInfLocationsConfig {

	protected static String METAINF = "metainf";

	@Autowired
	Project project;

	protected Location getMetaInfCommon() {
		return getLocation("/common.properties");
	}

	protected Location getMetaInfCommonBuild() {
		return getLocation("/build/common.properties");
	}

	protected Location getLocation(String suffix) {
		String encoding = ProjectUtils.getEncoding(project);
		String classpathPrefix = ProjectUtils.getClasspathPrefix(project.getGroupId(), project.getArtifactId());
		String prefix = classpathPrefix + "/" + METAINF;
		return new ImmutableLocation(prefix + suffix, encoding);
	}
}

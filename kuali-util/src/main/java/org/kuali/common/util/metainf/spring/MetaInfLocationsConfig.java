package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ProjectServiceConfig.class })
public class MetaInfLocationsConfig {

	protected static String METAINF = "metainf";

	@Autowired
	ProjectServiceConfig projectServiceConfig;

	@Bean
	public Project kualiUtilProject() {
		ProjectService service = projectServiceConfig.projectService();
		return service.getProject(KualiUtilProjectConstants.PROJECT_IDENTIFIER);
	}

	public Location getMetaInfCommon() {
		return getLocation("/common.properties");
	}

	public Location getMetaInfCommonBuild() {
		return getLocation("/build/common.properties");
	}

	public Location getLocation(String suffix) {
		Project project = kualiUtilProject();
		String encoding = ProjectUtils.getEncoding(project);
		String classpathPrefix = ProjectUtils.getClasspathPrefix(project.getGroupId(), project.getArtifactId());
		String prefix = classpathPrefix + "/" + METAINF;
		return new Location(prefix + suffix, encoding, true);
	}
}

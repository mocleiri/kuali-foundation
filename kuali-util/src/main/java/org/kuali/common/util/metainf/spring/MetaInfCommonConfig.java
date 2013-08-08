package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.Str;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectService;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.FeatureIdentifier;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.project.spring.ProjectServiceConfig;
import org.kuali.common.util.properties.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ResourceUtils;

@Configuration
@Import({ AutowiredProjectConfig.class, ProjectServiceConfig.class })
public class MetaInfCommonConfig {

	public static final String RESOURCES_FILENAME_EXTENSION = "resources";
	public static final String METAINF_DIRECTORY_NAME = "META-INF";
	public static final FeatureIdentifier FEATURE_ID = new FeatureIdentifier(KualiUtilProjectConstants.PROJECT_ID, "metainf");

	public static final String DATA_FILENAME = "data." + RESOURCES_FILENAME_EXTENSION;
	public static final String SCHEMA_FILENAME = "schema." + RESOURCES_FILENAME_EXTENSION;
	public static final String CONSTRAINTS_FILENAME = "constraints." + RESOURCES_FILENAME_EXTENSION;
	public static final String OTHER_FILENAME = "other." + RESOURCES_FILENAME_EXTENSION;

	public static final String PROPERTY_PREFIX = FEATURE_ID.getFeatureId();

	@Autowired
	ProjectService projectService;

	/**
	 * <code>META-INF/org/kuali/util</code>
	 */
	public static String getGroupPrefix(Project project) {
		return METAINF_DIRECTORY_NAME + "/" + Str.getPath(project.getGroupId());
	}

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getResourcePrefix(Project project) {
		return getGroupPrefix(project) + "/" + project.getArtifactId();
	}

	/**
	 * <code>classpath:META-INF</code>
	 */
	public static String getClasspathPrefix() {
		return ResourceUtils.CLASSPATH_URL_PREFIX + METAINF_DIRECTORY_NAME;
	}

	/**
	 */
	public Location getLocation(FeatureIdentifier identifier, String filename) {
		Project project = projectService.getProject(identifier.getGroupId(), identifier.getArtifactId());
		String value = ProjectUtils.getClasspathPrefix(MetaInfCommonConfig.FEATURE_ID) + "/" + filename;
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, true);
	}

}

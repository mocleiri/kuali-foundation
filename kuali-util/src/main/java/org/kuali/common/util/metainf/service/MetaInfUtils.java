package org.kuali.common.util.metainf.service;

import java.io.File;

import org.kuali.common.util.Str;
import org.kuali.common.util.metainf.spring.MetaInfGroup;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.FeatureIdentifier;
import org.kuali.common.util.project.model.Project;
import org.springframework.util.ResourceUtils;

public class MetaInfUtils {

	public static final String RESOURCES_FILENAME_EXTENSION = "resources";
	public static final String METAINF_DIRECTORY_NAME = "META-INF";
	public static final FeatureIdentifier FEATURE_ID = new FeatureIdentifier(KualiUtilProjectConstants.PROJECT_ID, "metainf");

	public static final String PROPERTY_PREFIX = FEATURE_ID.getFeatureId();

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/data.resources</code>
	 */
	public static File getOutputFile(Project project, Build build, MetaInfGroup group) {
		String outputPath = getResourcePrefix(project) + "/" + getFilename(group);
		return new File(build.getOutputDir(), outputPath);
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/oracle/data.resources</code>
	 */
	public static File getOutputFile(Project project, Build build, String databaseVendor, MetaInfGroup group) {
		String outputPath = getResourcePrefix(project) + "/" + databaseVendor + "/" + getFilename(group);
		return new File(build.getOutputDir(), outputPath);
	}

	/**
	 * Convert to lowercase and append <code>.resources</code>
	 */
	public static String getFilename(MetaInfGroup group) {
		return group.name().toLowerCase() + "." + RESOURCES_FILENAME_EXTENSION;
	}

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
}

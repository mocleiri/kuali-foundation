package org.kuali.common.util.metainf.service;

import java.io.File;

import org.kuali.common.util.Str;
import org.kuali.common.util.metainf.spring.MetaInfGroup;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.springframework.util.ResourceUtils;

import com.google.common.base.Optional;

public class MetaInfUtils {

	public static final String RESOURCES_FILENAME_EXTENSION = "resources";
	public static final String METAINF_DIRECTORY_NAME = "META-INF";

	/**
	 * @deprecated
	 */
	@Deprecated
	public static final org.kuali.common.util.project.model.FeatureIdentifier FEATURE_ID = new org.kuali.common.util.project.model.FeatureIdentifier(
			KualiUtilProjectConstants.PROJECT_ID, "metainf");

	public static final String PROPERTY_PREFIX = "metainf";

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[filename].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, String filename) {
		return getOutputFile(project, build, Optional.<String> absent(), filename);
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[grouping]/[filename].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, Optional<String> grouping, String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(getResourcePrefix(project));
		if (grouping.isPresent()) {
			sb.append("/");
			sb.append(grouping.get());
		}
		sb.append("/");
		sb.append(getFilename(filename));
		return new File(build.getOutputDir(), sb.toString());
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, MetaInfGroup group) {
		return getOutputFile(project, build, group.name().toLowerCase());
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[vendor]/[group].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, String databaseVendor, MetaInfGroup group) {
		return getOutputFile(project, build, Optional.of(databaseVendor), group.name().toLowerCase());
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static String getClasspathResource(ProjectIdentifier project, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), group);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[filenamePrefix].resources</code>
	 */
	public static String getClasspathResource(ProjectIdentifier project, String filenamePrefix) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), Optional.<String> absent(), filenamePrefix);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getClasspathResourcePrefix(ProjectIdentifier project) {
		StringBuilder sb = new StringBuilder();
		sb.append(ResourceUtils.CLASSPATH_URL_PREFIX);
		sb.append(getResourcePrefix(project.getGroupId(), project.getArtifactId()));
		return sb.toString();
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static String getClasspathResource(Project project, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), group);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static String getClasspathResource(String groupId, String artifactId, MetaInfGroup group) {
		return getClasspathResource(groupId, artifactId, Optional.<String> absent(), group.name().toLowerCase());
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[grouping]/[group].resources</code>
	 */
	public static String getClasspathResource(Project project, String grouping, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), Optional.of(grouping), group.name().toLowerCase());
	}

	/**
	 * <pre>
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[grouping]/[filename].resources</code>
	 * 
	 * OR
	 * 
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code>
	 * </pre>
	 */
	public static String getClasspathResource(String groupId, String artifactId, Optional<String> grouping, String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(ResourceUtils.CLASSPATH_URL_PREFIX);
		sb.append(getResourcePrefix(groupId, artifactId));
		if (grouping.isPresent()) {
			sb.append("/");
			sb.append(grouping.get());
		}
		sb.append("/");
		sb.append(getFilename(filename));
		return sb.toString();
	}

	/**
	 * Returns <code>[group].resources</code> (always lowercase)
	 */
	public static String getFilename(MetaInfGroup group) {
		return getFilename(group.name().toLowerCase());
	}

	/**
	 * Returns <code>[filename].resources</code>
	 */
	public static String getFilename(String filename) {
		return filename + "." + RESOURCES_FILENAME_EXTENSION;
	}

	/**
	 * <code>META-INF/org/kuali/util</code>
	 */
	public static String getGroupPrefix(Project project) {
		return getGroupPrefix(project.getGroupId());
	}

	/**
	 * <code>META-INF/org/kuali/util</code>
	 */
	public static String getGroupPrefix(String groupId) {
		return METAINF_DIRECTORY_NAME + "/" + Str.getPath(groupId);
	}

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getResourcePrefix(Project project) {
		return getResourcePrefix(project.getGroupId(), project.getArtifactId());
	}

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getResourcePrefix(String groupId, String artifactId) {
		return getGroupPrefix(groupId) + "/" + artifactId;
	}

	/**
	 * <code>META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getResourcePrefix(ProjectIdentifier project) {
		return getResourcePrefix(project.getGroupId(), project.getArtifactId());
	}

	/**
	 * <code>classpath:META-INF</code>
	 */
	public static String getClasspathPrefix() {
		return ResourceUtils.CLASSPATH_URL_PREFIX + METAINF_DIRECTORY_NAME;
	}
}

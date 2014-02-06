package org.kuali.common.util.metainf.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.common.util.Str;
import org.kuali.common.util.metainf.model.PathComparator;
import org.kuali.common.util.metainf.spring.MetaInfDataLocation;
import org.kuali.common.util.metainf.spring.MetaInfDataType;
import org.kuali.common.util.metainf.spring.MetaInfGroup;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

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
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, MetaInfGroup group) {
		return getOutputFile(project, build, group.name().toLowerCase());
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[filename].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, String filename) {
		return getOutputFile(project, build, Optional.<String> absent(), filename);
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[qualifier]/[group].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, String qualifier, MetaInfGroup group) {
		return getOutputFile(project, build, Optional.of(qualifier), group.name().toLowerCase());
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code>
	 */
	public static File getOutputFile(Project project, Build build, String qualifier, String filename) {
		return getOutputFile(project, build, Optional.of(qualifier), filename);
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code> is optional
	 */
	public static File getOutputFile(Project project, Build build, Optional<String> qualifier, String filename) {
		return getOutputFile(project, build, qualifier, Optional.<MetaInfDataLocation> absent(), Optional.<MetaInfDataType> absent(), filename);
	}

	/**
	 * <code>${project.build.outputDirectory}/META-INF/org/kuali/util/kuali-util/[qualifier]/[location]/[type]/[filename].resources</code> where <code>[qualifier]</code>,
	 * <code>[location]</code>, and <type>[type]</type> are optional
	 */
	public static File getOutputFile(Project project, Build build, Optional<String> qualifier, Optional<MetaInfDataLocation> location, Optional<MetaInfDataType> type,
			String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(getResourcePrefix(project));
		if (qualifier.isPresent()) {
			sb.append("/");
			sb.append(qualifier.get());
		}
		if (location.isPresent()) {
			sb.append("/");
			sb.append(location.get().name().toLowerCase());
		}
		if (type.isPresent()) {
			sb.append("/");
			sb.append(type.get().name().toLowerCase());
		}
		sb.append("/");
		sb.append(getFilename(filename));
		return new File(build.getOutputDir(), sb.toString());
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static String getClasspathResource(ProjectIdentifier project, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), group);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code>
	 */
	public static String getClasspathResource(ProjectIdentifier project, String filename) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[group].resources</code>
	 */
	public static String getClasspathResource(ProjectIdentifier project, String qualifier, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), group.name().toLowerCase());
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code>
	 */
	public static String getClasspathResource(ProjectIdentifier project, String qualifier, String filename) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code>, <code>[location]</code>, and <type>[type]</type>
	 * are optional
	 */
	public static String getClasspathResource(ProjectIdentifier project, Optional<String> qualifier, Optional<MetaInfDataLocation> location, Optional<MetaInfDataType> type,
			String filename) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), qualifier, location, type, filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static String getClasspathResource(Project project, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), group);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code>
	 */
	public static String getClasspathResource(Project project, String filename) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[group].resources</code>
	 */
	public static String getClasspathResource(Project project, String qualifier, MetaInfGroup group) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), group.name().toLowerCase());
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code>
	 */
	public static String getClasspathResource(Project project, String qualifier, String filename) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code>, <code>[location]</code>, and <type>[type]</type>
	 * are optional
	 */
	public static String getClasspathResource(Project project, Optional<String> qualifier, Optional<MetaInfDataLocation> location, Optional<MetaInfDataType> type, String filename) {
		return getClasspathResource(project.getGroupId(), project.getArtifactId(), qualifier, location, type, filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[group].resources</code>
	 */
	public static String getClasspathResource(String groupId, String artifactId, MetaInfGroup group) {
		return getClasspathResource(groupId, artifactId, Optional.<String> absent(), group.name().toLowerCase());
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code>
	 */
	public static String getClasspathResource(String groupId, String artifactId, String filename) {
		return getClasspathResource(groupId, artifactId, Optional.<String> absent(), filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code> is optional
	 */
	public static String getClasspathResource(String groupId, String artifactId, Optional<String> qualifier, String filename) {
		return getClasspathResource(groupId, artifactId, qualifier, Optional.<MetaInfDataLocation> absent(), Optional.<MetaInfDataType> absent(), filename);
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code>, <code>[location]</code>, and <type>[type]</type>
	 * are optional
	 */
	public static String getClasspathResource(String groupId, String artifactId, Optional<String> qualifier, Optional<MetaInfDataLocation> location,
			Optional<MetaInfDataType> type, String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(ResourceUtils.CLASSPATH_URL_PREFIX);
		sb.append(getResourcePrefix(groupId, artifactId));
		if (qualifier.isPresent()) {
			sb.append("/");
			sb.append(qualifier.get());
		}
		if (location.isPresent()) {
			sb.append("/");
			sb.append(location.get().name().toLowerCase());
		}
		if (type.isPresent()) {
			sb.append("/");
			sb.append(type.get().name().toLowerCase());
		}
		sb.append("/");
		sb.append(getFilename(filename));
		return sb.toString();
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code> where <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(ProjectIdentifier project, String filename) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[group].resources</code> where <code>[qualifier]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(ProjectIdentifier project, String qualifier, MetaInfGroup group) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), group.name().toLowerCase());
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code> and <code>[filename]</code> can contain an
	 * Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(ProjectIdentifier project, String qualifier, String filename) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[location]/[type]/[filename].resources</code> where <code>[qualifier]</code>, <code>[location]</code>,
	 * and <type>[type]</type> are optional and <code>[qualifier]</code> and <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(ProjectIdentifier project, Optional<String> qualifier, Optional<MetaInfDataLocation> location,
			Optional<MetaInfDataType> type, String filename) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), qualifier, location, type, filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code> where <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(Project project, String filename) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[group].resources</code> where <code>[qualifier]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(Project project, String qualifier, MetaInfGroup group) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), group.name().toLowerCase());
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code> and <code>[filename]</code> can contain an
	 * Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(Project project, String qualifier, String filename) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), Optional.of(qualifier), filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[location]/[type]/[filename].resources</code> where <code>[qualifier]</code>, <code>[location]</code>,
	 * and <type>[type]</type> are optional and <code>[qualifier]</code> and <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(Project project, Optional<String> qualifier, Optional<MetaInfDataLocation> location, Optional<MetaInfDataType> type,
			String filename) {
		return getPatternedClasspathResources(project.getGroupId(), project.getArtifactId(), qualifier, location, type, filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[filename].resources</code> where <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(String groupId, String artifactId, String filename) {
		return getPatternedClasspathResources(groupId, artifactId, Optional.<String> absent(), filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code> is optional * and <code>[qualifier]</code>
	 * and <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(String groupId, String artifactId, Optional<String> qualifier, String filename) {
		return getPatternedClasspathResources(groupId, artifactId, qualifier, Optional.<MetaInfDataLocation> absent(), Optional.<MetaInfDataType> absent(), filename);
	}

	/**
	 * List of <code>classpath:META-INF/org/kuali/util/kuali-util/[qualifier]/[filename].resources</code> where <code>[qualifier]</code>, <code>[location]</code>, and
	 * <type>[type]</type> are optional and <code>[qualifier]</code> and <code>[filename]</code> can contain an Ant-style pattern
	 */
	public static List<String> getPatternedClasspathResources(String groupId, String artifactId, Optional<String> qualifier, Optional<MetaInfDataLocation> location,
			Optional<MetaInfDataType> type, String filename) {
		List<String> patterenedClasspathResources = new ArrayList<String>();
		String classpathResource = getClasspathResource(groupId, artifactId, qualifier, location, type, filename);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resolver.getResources(classpathResource);
			if (ArrayUtils.isNotEmpty(resources)) {
				for (Resource resource : resources) {
					if (resource instanceof ClassPathResource) {
						ClassPathResource classPathResource = (ClassPathResource) resource;
						patterenedClasspathResources.add(ResourceUtils.CLASSPATH_URL_PREFIX + classPathResource.getPath());
					}
				}
			}
		} catch (IOException ioe) {
			throw new IllegalStateException("Unexpected IO error", ioe);
		}
		return patterenedClasspathResources;
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
	 * <code>classpath:META-INF</code>
	 */
	public static String getClasspathPrefix() {
		return ResourceUtils.CLASSPATH_URL_PREFIX + METAINF_DIRECTORY_NAME;
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
	 * <code>classpath:META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getClasspathResourcePrefix(ProjectIdentifier project) {
		StringBuilder sb = new StringBuilder();
		sb.append(ResourceUtils.CLASSPATH_URL_PREFIX);
		sb.append(getResourcePrefix(project.getGroupId(), project.getArtifactId()));
		return sb.toString();
	}

	/**
	 * <code>classpath:META-INF/org/kuali/util/kuali-util</code>
	 */
	public static String getClasspathResourcePrefix(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append(ResourceUtils.CLASSPATH_URL_PREFIX);
		sb.append(getResourcePrefix(project.getGroupId(), project.getArtifactId()));
		return sb.toString();
	}

	public static List<String> getQualifiers(File baseDirectory, ProjectIdentifier project, List<String> includes, List<String> excludes) {
		String resourcePath = ProjectUtils.getResourcePath(project.getGroupId(), project.getArtifactId());
		File resourceDirectory = FileUtils.getFile(baseDirectory, resourcePath);
		return getQualifiers(resourceDirectory, includes, excludes);
	}

	public static List<String> getQualifiers(File baseDirectory, Project project, List<String> includes, List<String> excludes) {
		String resourcePath = ProjectUtils.getResourcePath(project.getGroupId(), project.getArtifactId());
		File resourceDirectory = FileUtils.getFile(baseDirectory, resourcePath);
		return getQualifiers(resourceDirectory, includes, excludes);
	}

	public static List<String> getQualifiers(File baseDirectory, List<String> includes, List<String> excludes) {
		List<String> qualifiers = Lists.newArrayList();
		SimpleScanner scanner = new SimpleScanner(baseDirectory, includes, excludes);
		List<String> directories = scanner.getDirectories();
		Collections.sort(directories, new PathComparator());
		for (String directory : directories) {
			if (qualifiers.isEmpty()) {
				qualifiers.add(directory);
			} else {
				boolean matches = false;
				for (String qualifier : qualifiers) {
					matches |= StringUtils.startsWith(directory, qualifier);
				}
				if (!matches) {
					qualifiers.add(directory);
				}
			}
		}
		return qualifiers;
	}

}

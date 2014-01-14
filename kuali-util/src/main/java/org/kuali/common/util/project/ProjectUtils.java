/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.FeatureIdentifier;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.model.ProjectResource;
import org.springframework.util.ResourceUtils;

public class ProjectUtils {

	private static final String CLASSPATH = ResourceUtils.CLASSPATH_URL_PREFIX;

	/**
	 * Get a <code>Project</code> from a <code>Properties</code>
	 */
	public static Project getProject(Properties properties) {
		String groupId = properties.getProperty(MavenConstants.GROUP_ID_KEY);
		String artifactId = properties.getProperty(MavenConstants.ARTIFACT_ID_KEY);
		String version = properties.getProperty(MavenConstants.VERSION_KEY);
		return new ImmutableProject(groupId, artifactId, version, properties);
	}

	/**
	 * Convert a project id into a <code>ProjectIdentifier's</code>
	 * 
	 * Example project id:
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util
	 * </pre>
	 */
	public static ProjectIdentifier getIdentifier(String projectId) {

		// Project id can't be blank
		Assert.noBlanks("projectId is blank", projectId);

		// Split up the id
		String[] tokens = Str.split(projectId, ":", true);

		// Must always have exactly 2 tokens
		Assert.isTrue(tokens.length == 2, "tokens.length != 2");

		// 1st token is groupId, 2nd token is artifactId
		String groupId = tokens[0];
		String artifactId = tokens[1];

		// Create a project identifier from the strings
		return new ProjectIdentifier(groupId, artifactId);
	}

	/**
	 * Convert a list of project ids into a list of <code>ProjectIdentifier's</code>
	 * 
	 * Example project id:
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util
	 * </pre>
	 */
	public static List<ProjectIdentifier> getIdentifiers(List<String> projectIds) {
		List<ProjectIdentifier> list = new ArrayList<ProjectIdentifier>();
		for (String projectId : projectIds) {
			ProjectIdentifier element = getIdentifier(projectId);
			list.add(element);
		}
		return list;
	}

	/**
	 * Get a build object with local file system directories filled in.
	 * 
	 * The typical directory structure looks like this:
	 * 
	 * <pre>
	 *  kuali-util/
	 *  kuali-util/target
	 *  kuali-util/target/classes
	 * </pre>
	 */
	public static Build getBuild(Project project) {
		File projectDir = getBasedir(project);
		String encoding = getEncoding(project);
		File buildDir = getBuildDirectory(project);
		File outputDir = getBuildOutputDirectory(project);
		File sourceDirectory = new CanonicalFile(project.getProperties().getProperty(MavenConstants.SOURCE_DIRECTORY_KEY));
		File testOutputDir = new CanonicalFile(project.getProperties().getProperty(MavenConstants.TEST_OUTPUT_DIRECTORY_KEY));
		File testSourceDir = new CanonicalFile(project.getProperties().getProperty(MavenConstants.TEST_SOURCE_DIRECTORY_KEY));
		File scriptSourceDirectory = new CanonicalFile(project.getProperties().getProperty(MavenConstants.SCRIPT_SOURCE_DIRECTORY_KEY));
		return new Build(project, encoding, projectDir, buildDir, outputDir, sourceDirectory, scriptSourceDirectory, testOutputDir, testSourceDir);
	}

	/**
	 * Convenience method for extracting the value of the property <code>project.build.directory</code>
	 */
	public static File getBuildDirectory(Project project) {
		return new CanonicalFile(project.getProperties().getProperty(MavenConstants.BUILD_DIRECTORY_KEY));
	}

	/**
	 * Convenience method for extracting the value of the property <code>project.basedir</code>
	 */
	public static File getBasedir(Project project) {
		return new CanonicalFile(project.getProperties().getProperty(MavenConstants.BASEDIR_KEY));
	}

	/**
	 * Convenience method for extracting the value of the property <code>project.build.outputDirectory</code>
	 */
	public static File getBuildOutputDirectory(Project project) {
		return new CanonicalFile(project.getProperties().getProperty(MavenConstants.BUILD_OUTPUT_DIRECTORY_KEY));
	}

	/**
	 * Convenience method for extracting the value of the property <code>project.encoding</code>
	 */
	public static String getEncoding(Project project) {
		Assert.noNulls(project);
		return project.getProperties().getProperty(MavenConstants.ENCODING_KEY);
	}

	/**
	 * Return a resource directory relative to <code>directory</code>
	 * 
	 * <pre>
	 *   /tmp/x/y/z + org.kuali.common:kuali-util  ->  /tmp/x/y/z/org/kuali/common/kuali-util
	 * </pre>
	 */
	public static File getResourceDirectory(File directory, String groupId, String artifactId) {
		String path = getResourcePath(groupId, artifactId);
		return new File(directory, path);
	}

	/**
	 * Return a resource friendly prefix.
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util  ->  org/kuali/common/kuali-util
	 * </pre>
	 */
	public static String getResourcePath(String groupId, String artifactId) {
		Assert.noBlanks(groupId, artifactId);
		return Str.getPath(groupId) + "/" + artifactId;
	}

	/**
	 * Use <code>getClasspathPrefix()</code> instead. (lowercase "p" in the word classpath)
	 * 
	 * @deprecated
	 */
	@Deprecated
	public static String getClassPathPrefix(String groupId, String artifactId) {
		return getClasspathPrefix(groupId, artifactId);
	}

	/**
	 * Return a classpath prefix.
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util  ->  classpath:org/kuali/common/kuali-util
	 * </pre>
	 */
	public static String getClasspathPrefix(String groupId, String artifactId) {
		return CLASSPATH + getResourcePath(groupId, artifactId);
	}

	/**
	 * Return a classpath prefix.
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util  ->  classpath:org/kuali/common/kuali-util
	 * </pre>
	 */
	public static String getClasspathPrefix(ProjectIdentifier project) {
		return getClasspathPrefix(project.getGroupId(), project.getArtifactId());
	}

	/**
	 * Return a classpath prefix.
	 * 
	 * <pre>
	 *   org.kuali.common  ->  classpath:org/kuali/common
	 * </pre>
	 */
	public static String getClasspathPrefix(String groupId) {
		return CLASSPATH + Str.getPath(groupId);
	}

	/**
	 * Return a classpath prefix.
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-util:metainf  ->  classpath:org/kuali/common/kuali-util/metainf
	 * </pre>
	 */
	public static String getClasspathPrefix(FeatureIdentifier feature) {
		return getClasspathPrefix(feature.getProject()) + "/" + feature.getFeatureId();
	}

	/**
	 * <pre>
	 *   classpath:org/kuali/common/kuali-util/myfile.txt
	 * </pre>
	 */
	public static String getClasspathResource(ProjectIdentifier project, String filename) {
		return getClasspathPrefix(project.getGroupId(), project.getArtifactId()) + "/" + filename;
	}

	/**
	 * <pre>
	 *   classpath:org/kuali/common/kuali-util/myfile.txt
	 * </pre>
	 */
	public static String getClasspathResource(ProjectResource resource) {
		return getClasspathResource(resource.getProject(), resource.getFilename());
	}

}

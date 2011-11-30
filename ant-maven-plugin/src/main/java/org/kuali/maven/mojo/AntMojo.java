package org.kuali.maven.mojo;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Typedef;
import org.apache.tools.ant.types.Path;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.configuration.PlexusConfigurationException;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.ResourceUtils;

/**
 * Maven Ant Mojo. This plugin allows Maven to invoke Ant tasks. Specify any Ant build.xml file available on the file
 * system, classpath (or any other URL location supported by Spring style resource syntax) and specify a target inside
 * the file to invoke
 * 
 * @threadSafe
 * @requiresDependencyResolution test
 * @goal run
 */
public class AntMojo extends AbstractMojo {
	ResourceUtils resourceUtils = new ResourceUtils();
	/**
	 * The refid used to store the Maven project object in the Ant build.
	 */
	public final static String DEFAULT_MAVEN_PROJECT_REFID = "maven.project";

	/**
	 * The refid used to store the Maven project object in the Ant build.
	 */
	public final static String DEFAULT_MAVEN_PROJECT_HELPER_REFID = "maven.project.helper";

	/**
	 * The default target name.
	 */
	public final static String DEFAULT_ANT_TARGET_NAME = "main";

	/**
	 * The default encoding to use for the generated Ant build.
	 */
	public final static String UTF_8 = "UTF-8";

	public final static String LOCAL_FILE = "build-local.xml";
	public final static String BUILD_WRAPPER = "build-wrapper.xml";
	public final static String BUILD_WRAPPER_TARGET = "main";

	/**
	 * The name used for the ant target
	 */
	private String antTargetName;

	/**
	 * The path to The XML file containing the definition of the Maven tasks.
	 */
	public final static String ANTLIB = "org/apache/maven/ant/tasks/antlib.xml";

	/**
	 * The URI which defines the built in Ant tasks
	 */
	public final static String TASK_URI = "antlib:org.apache.maven.ant.tasks";

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The Maven project helper object
	 * 
	 * @component
	 */
	private MavenProjectHelper projectHelper;

	/**
	 * The plugin dependencies.
	 * 
	 * @parameter expression="${plugin.artifacts}"
	 * @required
	 * @readonly
	 */
	private List<?> pluginArtifacts;

	/**
	 * The local Maven repository
	 * 
	 * @parameter expression="${localRepository}"
	 * @readonly
	 */
	protected ArtifactRepository localRepository;

	/**
	 * String to prepend to project and dependency property names.
	 * 
	 * @parameter expression="${ant.propertyPrefix}" default-value=""
	 */
	private String propertyPrefix = "";

	/**
	 * The xml tag prefix to use for the built in Ant tasks. This prefix needs to be prepended to each task referenced
	 * in the antrun target config. For example, a prefix of "mvn" means that the attachartifact task is referenced by
	 * "&lt;mvn:attachartifact&gt;" The default value of an empty string means that no prefix is used for the tasks.
	 * 
	 * @parameter expression="${ant.customTaskPrefix}" default-value=""
	 */
	private String customTaskPrefix = "";

	/**
	 * The name of a property containing the list of all dependency versions. This is used for the removing the versions
	 * from the filenames.
	 * 
	 * @parameter expression="${ant.versionsPropertyName}" default-value="maven.project.dependencies.versions"
	 */
	private String versionsPropertyName;

	/**
	 * Specifies whether the Ant execution should be skipped.
	 * 
	 * @parameter expression="${ant.skip}" default-value="false"
	 */
	private boolean skip;

	/**
	 * Specifies whether the Ant properties should be propagated to the Maven properties.
	 * 
	 * @parameter expression="${ant.exportAntProperties}" default-value="false"
	 */
	private boolean exportAntProperties;

	/**
	 * Specifies whether a failure in the ant build leads to a failure of the Maven build.
	 * 
	 * If this value is 'true', the Maven build will proceed even if the ant build fails. If it is 'false', then the
	 * Maven build fails if the ant build fails.
	 * 
	 * @parameter expression="${ant.failOnError}" default-value="true"
	 */
	private boolean failOnError;

	/**
	 * @parameter expression="${ant.target}"
	 */
	private String target;

	/**
	 * @parameter expression="${ant.location}" default-value="classpath:build.xml"
	 */
	private String location;

	/**
	 * @parameter expression="${ant.tmpDir}" default-value="${project.build.directory}/ant"
	 */
	private String tmpDir;

	private File localFile;

	@Override
	public void execute() throws MojoExecutionException {
		if (skip) {
			getLog().info("Skipping execution");
			return;
		}

		try {
			localFile = createLocalFile();
			Project antProject = getAntProject();

			BuildLogger antLogger = getBuildLogger();
			antProject.addBuildListener(antLogger);
			antProject.setBaseDir(project.getBasedir());

			addReferences(antProject);

			// Ant project needs actual properties vs. using expression evaluator when calling an external build file.
			copyProperties(project, antProject);

			getLog().info("Executing tasks");
			antProject.executeTarget(antTargetName);
			getLog().info("Executed tasks");

			copyProperties(antProject, project);
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("DependencyResolutionRequiredException: " + e.getMessage(), e);
		} catch (BuildException e) {
			handleBuildException(e);
		} catch (Throwable e) {
			throw new MojoExecutionException("Error executing ant tasks: " + e.getMessage(), e);
		}
	}

	protected void addReferences(Project antProject) throws BuildException, DependencyResolutionRequiredException {
		Path p = new Path(antProject);
		p.setPath(StringUtils.join(project.getCompileClasspathElements().iterator(), File.pathSeparator));

		/* maven.dependency.classpath it's deprecated as it's equal to maven.compile.classpath */
		antProject.addReference("maven.dependency.classpath", p);
		antProject.addReference("maven.compile.classpath", p);

		p = new Path(antProject);
		p.setPath(StringUtils.join(project.getRuntimeClasspathElements().iterator(), File.pathSeparator));
		antProject.addReference("maven.runtime.classpath", p);

		p = new Path(antProject);
		p.setPath(StringUtils.join(project.getTestClasspathElements().iterator(), File.pathSeparator));
		antProject.addReference("maven.test.classpath", p);

		/* set maven.plugin.classpath with plugin dependencies */
		antProject.addReference("maven.plugin.classpath", getPathFromArtifacts(pluginArtifacts, antProject));

		antProject.addReference(DEFAULT_MAVEN_PROJECT_REFID, getMavenProject());
		antProject.addReference(DEFAULT_MAVEN_PROJECT_HELPER_REFID, projectHelper);
		antProject.addReference("maven.local.repository", localRepository);
		initMavenTasks(antProject);
	}

	protected File createLocalFile() throws IOException {
		String filename = tmpDir + "/" + LOCAL_FILE;
		resourceUtils.copy(location, filename);
		return new File(filename);
	}

	protected Project getAntProject() throws IOException {
		Project antProject = new Project();
		File antBuildFile = createBuildWrapper();
		ProjectHelper.configureProject(antProject, antBuildFile);
		antProject.init();
		return antProject;
	}

	protected BuildLogger getBuildLogger() {
		DefaultLogger antLogger = new DefaultLogger();
		antLogger.setOutputPrintStream(System.out);
		antLogger.setErrorPrintStream(System.err);

		if (getLog().isDebugEnabled()) {
			antLogger.setMessageOutputLevel(Project.MSG_DEBUG);
		} else if (getLog().isInfoEnabled()) {
			antLogger.setMessageOutputLevel(Project.MSG_INFO);
		} else if (getLog().isWarnEnabled()) {
			antLogger.setMessageOutputLevel(Project.MSG_WARN);
		} else if (getLog().isErrorEnabled()) {
			antLogger.setMessageOutputLevel(Project.MSG_ERR);
		} else {
			antLogger.setMessageOutputLevel(Project.MSG_VERBOSE);
		}
		return antLogger;
	}

	protected void handleBuildException(BuildException e) throws MojoExecutionException {
		StringBuffer sb = new StringBuffer();
		sb.append("An Ant BuildException has occured: " + e.getMessage());
		String fragment = findFragment(e);
		if (fragment != null) {
			sb.append("\n").append(fragment);
		}
		if (!failOnError) {
			getLog().info(sb.toString(), e);
			return; // do not register roots.
		} else {
			throw new MojoExecutionException(sb.toString(), e);
		}
	}

	/**
	 * @param artifacts
	 * @param antProject
	 * @return a path
	 * @throws DependencyResolutionRequiredException
	 */
	public Path getPathFromArtifacts(Collection<?> artifacts, Project antProject)
			throws DependencyResolutionRequiredException {
		if (artifacts == null) {
			return new Path(antProject);
		}

		List<String> list = new ArrayList<String>(artifacts.size());
		for (Iterator<?> i = artifacts.iterator(); i.hasNext();) {
			Artifact a = (Artifact) i.next();
			File file = a.getFile();
			if (file == null) {
				throw new DependencyResolutionRequiredException(a);
			}
			list.add(file.getPath());
		}

		Path p = new Path(antProject);
		p.setPath(StringUtils.join(list.iterator(), File.pathSeparator));

		return p;
	}

	/**
	 * Copy properties from the maven project to the ant project.
	 * 
	 * @param mavenProject
	 * @param antProject
	 */
	public void copyProperties(MavenProject mavenProject, Project antProject) {
		Properties mavenProps = mavenProject.getProperties();
		Iterator<?> iter = mavenProps.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			antProject.setProperty(key, mavenProps.getProperty(key));
		}

		// Set the POM file as the ant.file for the tasks run directly in Maven.
		antProject.setProperty("ant.file", mavenProject.getFile().getAbsolutePath());

		// Add some of the common maven properties
		getLog().debug("Setting properties with prefix: " + propertyPrefix);
		antProject.setProperty((propertyPrefix + "project.groupId"), mavenProject.getGroupId());
		antProject.setProperty((propertyPrefix + "project.artifactId"), mavenProject.getArtifactId());
		antProject.setProperty((propertyPrefix + "project.name"), mavenProject.getName());
		if (mavenProject.getDescription() != null) {
			antProject.setProperty((propertyPrefix + "project.description"), mavenProject.getDescription());
		}
		antProject.setProperty((propertyPrefix + "project.version"), mavenProject.getVersion());
		antProject.setProperty((propertyPrefix + "project.packaging"), mavenProject.getPackaging());
		antProject.setProperty((propertyPrefix + "project.build.directory"), mavenProject.getBuild().getDirectory());
		antProject.setProperty((propertyPrefix + "project.build.outputDirectory"), mavenProject.getBuild()
				.getOutputDirectory());
		antProject.setProperty((propertyPrefix + "project.build.testOutputDirectory"), mavenProject.getBuild()
				.getTestOutputDirectory());
		antProject.setProperty((propertyPrefix + "project.build.sourceDirectory"), mavenProject.getBuild()
				.getSourceDirectory());
		antProject.setProperty((propertyPrefix + "project.build.testSourceDirectory"), mavenProject.getBuild()
				.getTestSourceDirectory());
		antProject.setProperty((propertyPrefix + "localRepository"), localRepository.toString());
		antProject.setProperty((propertyPrefix + "settings.localRepository"), localRepository.getBasedir());

		// Add properties for depenedency artifacts
		Set<?> depArtifacts = mavenProject.getArtifacts();
		for (Iterator<?> it = depArtifacts.iterator(); it.hasNext();) {
			Artifact artifact = (Artifact) it.next();

			String propName = artifact.getDependencyConflictId();

			antProject.setProperty(propertyPrefix + propName, artifact.getFile().getPath());
		}

		// Add a property containing the list of versions for the mapper
		StringBuffer versionsBuffer = new StringBuffer();
		for (Iterator<?> it = depArtifacts.iterator(); it.hasNext();) {
			Artifact artifact = (Artifact) it.next();

			versionsBuffer.append(artifact.getVersion() + File.pathSeparator);
		}
		antProject.setProperty(versionsPropertyName, versionsBuffer.toString());

		// Add properties in deprecated format to depenedency artifacts
		// This should be removed in future versions of the antrun plugin.
		for (Iterator<?> it = depArtifacts.iterator(); it.hasNext();) {
			Artifact artifact = (Artifact) it.next();

			String propName = getDependencyArtifactPropertyName(artifact);

			antProject.setProperty(propName, artifact.getFile().getPath());
		}
	}

	/**
	 * Copy properties from the ant project to the maven project.
	 * 
	 * @param antProject
	 *            not null
	 * @param mavenProject
	 *            not null
	 * @since 1.7
	 */
	public void copyProperties(Project antProject, MavenProject mavenProject) {
		if (!exportAntProperties) {
			return;
		}

		getLog().debug("Propagated Ant properties to Maven properties");
		Hashtable<?, ?> antProps = antProject.getProperties();

		Iterator<?> iter = antProps.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();

			Properties mavenProperties = mavenProject.getProperties();
			if (mavenProperties.getProperty(key) != null) {
				getLog().debug(
						"Ant property '" + key + "=" + mavenProperties.getProperty(key)
								+ "' clashs with an existing Maven property, SKIPPING this Ant property propagation.");
				continue;
			}
			mavenProperties.setProperty(key, antProps.get(key).toString());
		}
	}

	/**
	 * Prefix for legacy property format.
	 * 
	 * @deprecated This should only be used for generating the old property format.
	 */
	@Deprecated
	public static final String DEPENDENCY_PREFIX = "maven.dependency.";

	/**
	 * Returns a property name for a dependency artifact. The name is in the format
	 * maven.dependency.groupId.artifactId[.classifier].type.path
	 * 
	 * @param artifact
	 * @return property name
	 * @deprecated The dependency conflict ID should be used as the property name.
	 */
	@Deprecated
	public static String getDependencyArtifactPropertyName(Artifact artifact) {
		String key = DEPENDENCY_PREFIX + artifact.getGroupId() + "." + artifact.getArtifactId()
				+ (artifact.getClassifier() != null ? "." + artifact.getClassifier() : "")
				+ (artifact.getType() != null ? "." + artifact.getType() : "") + ".path";
		return key;
	}

	/**
	 * Get the current Maven project
	 * 
	 * @return current Maven project
	 */
	public MavenProject getMavenProject() {
		return this.project;
	}

	public void initMavenTasks(Project antProject) {
		getLog().debug("Initialize Maven Ant Tasks");
		Typedef typedef = new Typedef();
		typedef.setProject(antProject);
		typedef.setResource(ANTLIB);
		if (!customTaskPrefix.equals("")) {
			typedef.setURI(TASK_URI);
		}
		typedef.execute();
	}

	protected File createBuildWrapper() throws IOException {
		String filename = tmpDir + "/" + BUILD_WRAPPER;
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<project name=\"maven-antrun-\" default=\"main\">\n");
		sb.append("  <target name=\"main\">\n");
		sb.append("    <ant antfile=\"" + localFile.getAbsolutePath() + "\" target=\"" + target + "\"/>\n");
		sb.append("  </target>\n");
		sb.append("</project>\n");
		resourceUtils.write(filename, sb.toString());
		return new File(filename);
	}

	/**
	 * Replace text in a StringBuffer. If the match text is not found, the StringBuffer is returned unchanged.
	 * 
	 * @param text
	 *            The string buffer containing the text
	 * @param match
	 *            The string to match and remove
	 * @param with
	 *            The string to insert
	 */
	public void stringReplace(StringBuffer text, String match, String with) {
		int index = text.indexOf(match);
		if (index != -1) {
			text.replace(index, index + match.length(), with);
		}
	}

	public String checkTargetName(PlexusConfiguration antTargetConfig) throws PlexusConfigurationException {
		String targetName = antTargetConfig.getAttribute("name");
		if (targetName == null) {
			targetName = DEFAULT_ANT_TARGET_NAME;
		}
		return targetName;
	}

	/**
	 * @param buildException
	 *            not null
	 * @return the fragment XML part where the buildException occurs.
	 * @since 1.7
	 */
	protected String findFragment(BuildException buildException) {
		if (buildException == null || buildException.getLocation() == null
				|| buildException.getLocation().getFileName() == null) {
			return null;
		}

		File antFile = new File(buildException.getLocation().getFileName());
		if (!antFile.exists()) {
			return null;
		}

		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(ReaderFactory.newXmlReader(antFile));
			String line = "";
			while ((line = reader.readLine()) != null) {
				if (reader.getLineNumber() == buildException.getLocation().getLineNumber()) {
					return "around Ant part ..." + line.trim() + "... @ "
							+ buildException.getLocation().getLineNumber() + ":"
							+ buildException.getLocation().getColumnNumber() + " in " + antFile.getAbsolutePath();
				}
			}
		} catch (Exception e) {
			getLog().debug(e.getMessage(), e);
			return null;
		} finally {
			IOUtil.close(reader);
		}

		return null;
	}

	public String getAntTargetName() {
		return antTargetName;
	}

	public void setAntTargetName(String antTargetName) {
		this.antTargetName = antTargetName;
	}

	public String getPropertyPrefix() {
		return propertyPrefix;
	}

	public void setPropertyPrefix(String propertyPrefix) {
		this.propertyPrefix = propertyPrefix;
	}

	public String getCustomTaskPrefix() {
		return customTaskPrefix;
	}

	public void setCustomTaskPrefix(String customTaskPrefix) {
		this.customTaskPrefix = customTaskPrefix;
	}

	public String getVersionsPropertyName() {
		return versionsPropertyName;
	}

	public void setVersionsPropertyName(String versionsPropertyName) {
		this.versionsPropertyName = versionsPropertyName;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isExportAntProperties() {
		return exportAntProperties;
	}

	public void setExportAntProperties(boolean exportAntProperties) {
		this.exportAntProperties = exportAntProperties;
	}

	public boolean isFailOnError() {
		return failOnError;
	}

	public void setFailOnError(boolean failOnError) {
		this.failOnError = failOnError;
	}

	public MavenProject getProject() {
		return project;
	}

	public MavenProjectHelper getProjectHelper() {
		return projectHelper;
	}

	public ArtifactRepository getLocalRepository() {
		return localRepository;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String temporaryDir) {
		this.tmpDir = temporaryDir;
	}
}

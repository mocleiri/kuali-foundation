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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.ResourceUtils;

/**
 * Maven Ant Mojo.
 * 
 * @goal run2
 * @threadSafe
 * @requiresDependencyResolution test
 */
public class AntRunMojo extends AbstractMojo {
	ResourceUtils resourceUtils = new ResourceUtils();
	private static final String FS = System.getProperty("file.separator");

	public static final String ANT_DIR = "ant";
	public static final String ANT_BUILD_DIR = "target" + FS + ANT_DIR;

	/**
	 * The refid used to store the Maven project object in the Ant build.
	 */
	public static final String DEFAULT_MAVEN_PROJECT_REFID = "maven.project";

	/**
	 * The refid used to store the Maven project object in the Ant build.
	 */
	public static final String DEFAULT_MAVEN_PROJECT_HELPER_REFID = "maven.project.helper";

	/**
	 * The default target name.
	 */
	public static final String DEFAULT_ANT_TARGET_NAME = "main";

	/**
	 * The default encoding to use for the generated Ant build.
	 */
	public static final String UTF_8 = "UTF-8";

	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"" + UTF_8 + "\" ?>\n";

	/**
	 * The path to The XML file containing the definition of the Maven tasks.
	 */
	public static final String ANTLIB = "org/apache/maven/ant/tasks/antlib.xml";

	/**
	 * The URI which defines the built in Ant tasks
	 */
	public static final String TASK_URI = "antlib:org.apache.maven.ant.tasks";

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
	 * The xml tag prefix to use for the built in Ant tasks. This prefix needs to be prepended to each task referenced. For example, a prefix of "mvn"
	 * means that the attachartifact task is referenced by "&lt;mvn:attachartifact&gt;" The default value of an empty string means that no prefix is used
	 * for the tasks.
	 * 
	 * @parameter expression="${ant.customTaskPrefix}" default-value=""
	 */
	private String customTaskPrefix = "";

	/**
	 * The name of a property containing the list of all dependency versions. This is used for the removing the versions from the filenames.
	 * 
	 * @parameter expression="${ant.versionsPropertyName}" default-value="maven.project.dependencies.versions"
	 */
	private String versionsPropertyName;

	/**
	 * Specifies whether the Antrun execution should be skipped.
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
	 * If this value is 'true', the Maven build will proceed even if the ant build fails. If it is 'false', then the Maven build fails if the ant build
	 * fails.
	 * 
	 * @parameter expression="${ant.failOnError}" default-value="true"
	 */
	private boolean failOnError;

	/**
	 * The build file to use. This supports Spring 3.0 resource URL expressions eg "classpath:build.xml" or "http://myurl/build.xml". When searching the
	 * classpath for build files, the classpath of the ant-maven-plugin is what is searched, not the classpath of the project the plugin is running in.
	 * 
	 * @parameter expression="${ant.file}" default-value="build.xml"
	 * @required
	 */
	private String file;

	/**
	 * The target inside the build file to invoke. If not provided, the default target from the specified build file will be executed
	 * 
	 * @parameter expression="${ant.target}"
	 */
	private String target;

	/**
	 * @parameter expression="${ant.output}"
	 */
	private String output;

	/**
	 * @parameter expression="${ant.inheritAll}" default-value="true"
	 */
	private String inheritAll;

	/**
	 * @parameter expression="${ant.inheritRefs}" default-value="true"
	 */
	private String inheritRefs;

	private String antFilename;

	protected AntTaskPojo getAntTaskPojo() {
		AntTaskPojo pojo = new AntTaskPojo();
		pojo.setAntfile(ANT_BUILD_DIR + FS + "local-" + antFilename);
		pojo.setTarget(target);
		pojo.setOutput(output);
		pojo.setInheritAll(Boolean.parseBoolean(inheritAll));
		pojo.setInheritRefs(Boolean.parseBoolean(inheritRefs));
		return pojo;
	}

	protected void handleAntfile() throws IOException {
		antFilename = resourceUtils.getFilename(file);
		File localFile = new File(ANT_BUILD_DIR + FS + "local-" + antFilename);
		resourceUtils.copy(file, localFile.getAbsolutePath());
	}

	protected boolean isSkip() {
		if (skip) {
			getLog().info("Skipping Ant execution");
			return true;
		}
		return false;
	}

	protected Project getAntProject() throws IOException {
		Project antProject = new Project();
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

	protected Map<String, Path> getPathRefs(Project antProject, MavenProject mavenProject)
			throws DependencyResolutionRequiredException {

		Map<String, Path> pathRefs = new HashMap<String, Path>();

		// compile
		Path mcp = new Path(antProject);
		mcp.setPath(StringUtils.join(mavenProject.getCompileClasspathElements().iterator(), File.pathSeparator));
		pathRefs.put("maven.compile.classpath", mcp);

		// runtime
		Path mrp = new Path(antProject);
		mrp.setPath(StringUtils.join(mavenProject.getRuntimeClasspathElements().iterator(), File.pathSeparator));
		pathRefs.put("maven.runtime.classpath", mrp);

		// test
		Path mtp = new Path(antProject);
		mtp.setPath(StringUtils.join(mavenProject.getTestClasspathElements().iterator(), File.pathSeparator));
		pathRefs.put("maven.test.classpath", mtp);

		// plugin
		Path mpc = getPathFromArtifacts(pluginArtifacts, antProject);
		pathRefs.put("maven.plugin.classpath", mpc);
		return pathRefs;
	}

	protected void addPathRefs(Project antProject, Map<String, Path> pathRefs) {
		for (Map.Entry<String, Path> pair : pathRefs.entrySet()) {
			antProject.addReference(pair.getKey(), pair.getValue());
		}
	}

	protected void addMavenReferences(Project antProject, MavenProject mavenProject) {
		antProject.addReference(DEFAULT_MAVEN_PROJECT_REFID, getMavenProject());
		antProject.addReference(DEFAULT_MAVEN_PROJECT_HELPER_REFID, projectHelper);
		antProject.addReference("maven.local.repository", localRepository);
	}

	/**
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException {
		if (isSkip()) {
			return;
		}

		MavenProject mavenProject = getMavenProject();

		try {
			handleAntfile();
			Project antProject = getAntProject();

			BuildLogger antLogger = getBuildLogger();
			antProject.addBuildListener(antLogger);

			Map<String, Path> pathRefs = getPathRefs(antProject, mavenProject);
			addPathRefs(antProject, pathRefs);

			File antBuildFile = createBuildWrapper(pathRefs);
			ProjectHelper.configureProject(antProject, antBuildFile);
			antProject.init();

			addMavenReferences(antProject, mavenProject);
			initMavenTasks(antProject);

			// Ant project needs actual properties vs. using expression evaluator when calling an external build file.
			copyProperties(mavenProject, antProject);

			getLog().info("Executing tasks");
			antProject.executeTarget(DEFAULT_ANT_TARGET_NAME);
			getLog().info("Executed tasks");

			copyProperties(antProject, mavenProject);
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("DependencyResolutionRequiredException: " + e.getMessage(), e);
		} catch (BuildException e) {
			handleBuildException(e);
		} catch (Throwable e) {
			throw new MojoExecutionException("Error executing ant tasks: " + e.getMessage(), e);
		}
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
	}

	/**
	 * Copy properties from the ant project to the maven project.
	 * 
	 * @param antProject
	 *            not null
	 * @param mavenProject
	 *            not null
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
								+ "' clashes with an existing Maven property, SKIPPING this Ant property propagation.");
				continue;
			}
			mavenProperties.setProperty(key, antProps.get(key).toString());
		}
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

	protected String getDefaultXML(Map<String, Path> pathRefs) throws IOException {
		AntTaskPojo atp = getAntTaskPojo();
		StringBuilder sb = new StringBuilder();
		sb.append(XML_HEADER);
		sb.append(getProjectOpen());
		sb.append("  <target name=\"" + DEFAULT_ANT_TARGET_NAME + "\">\n");
		sb.append("    " + getXML(atp) + "\n");
		sb.append("  </target>\n");
		for (Map.Entry<String, Path> pair : pathRefs.entrySet()) {
			sb.append("  <path id=\"" + pair.getKey() + "\">\n");
			sb.append("    <pathelement path=\"" + pair.getValue() + "\" />\n");
			sb.append("  </path>\n");
		}
		for (String key : pathRefs.keySet()) {
			sb.append("  <property name=\"" + key + "\" refid=\"" + key + "\" />\n");
		}
		sb.append("</project>\n");
		return sb.toString();
	}

	/**
	 * Write the ant target and surrounding tags to a temporary file
	 */
	protected File createBuildWrapper(Map<String, Path> pathRefs) throws IOException {
		String xml = getDefaultXML(pathRefs);

		// The fileName should probably use the plugin executionId instead
		File buildFile = new File(ANT_BUILD_DIR + FS + antFilename);

		buildFile.getParentFile().mkdirs();
		FileUtils.fileWrite(buildFile.getAbsolutePath(), UTF_8, xml);
		return buildFile;
	}

	protected String getProjectOpen() {
		StringBuilder sb = new StringBuilder();
		sb.append("<project");
		sb.append(" name=\"ant-maven\"");
		sb.append(" default=\"" + DEFAULT_ANT_TARGET_NAME + "\"");
		sb.append(" basedir=\"" + project.getBasedir().getAbsolutePath() + "\"");
		if (!StringUtils.isBlank(customTaskPrefix)) {
			sb.append(" xmlns:" + customTaskPrefix + "=\"" + TASK_URI + "\"");
		}
		sb.append(">");
		sb.append("\n");
		return sb.toString();
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

	/**
	 * @param buildException
	 *            not null
	 * @return the fragment XML part where the buildException occurs.
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

	protected String getXML(AntTaskPojo atp) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ant");
		sb.append(attr("antfile", atp.getAntfile()));
		sb.append(attr("dir", atp.getDir()));
		sb.append(attr("target", atp.getTarget()));
		sb.append(attr("output", atp.getOutput()));
		// Only include if different from the default
		if (!atp.isInheritAll()) {
			sb.append(attr("inheritAll", atp.isInheritAll() + ""));
		}
		// Only include if different from the default
		if (atp.isInheritRefs()) {
			sb.append(attr("inheritRefs", atp.isInheritRefs() + ""));
		}
		// Only include if different from the default
		if (atp.isUseNativeBasedir()) {
			sb.append(attr("useNativeBaseDir", atp.isUseNativeBasedir() + ""));
		}
		sb.append(" />");
		return sb.toString();
	}

	protected String attr(String name, String value) {
		if (StringUtils.isEmpty(value)) {
			return "";
		} else {
			return " " + name + "=\"" + value + "\"";
		}
	}

}

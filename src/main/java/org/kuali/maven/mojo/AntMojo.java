/**
 * Copyright 2004-2011 The Kuali Foundation
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Typedef;
import org.apache.tools.ant.types.Path;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.AntMavenUtils;
import org.kuali.maven.common.ResourceUtils;

/**
 * <p>
 * Maven Ant Mojo. Allows Maven to invoke a target inside an Ant build file. The build file can be located on the file system, the ant-maven-plugin
 * classpath, or any resource URL that Spring 3.0 can understand.
 * </p>
 * 
 * <p>
 * By default, this mojo makes the following available to Ant build files as both properties and references:
 * </p>
 * 
 * maven.compile.classpath=The classpath Maven is using for compilation<br>
 * maven.runtime.classpath=The classpath Maven is using at runtime<br>
 * maven.test.classpath=The classpath Maven is using for testing<br>
 * maven.plugin.classpath=The classpath for the ant-maven-plugin
 * 
 * 
 * <p>
 * These are available as Ant references:
 * </p>
 * 
 * maven.project=MavenProject<br>
 * maven.project.helper=MavenProjectHelper<br>
 * maven.local.repository=ArtifactRepository<br>
 * 
 * @goal run
 * @threadSafe
 * @requiresDependencyResolution test
 */
public class AntMojo extends AbstractMojo {
	ResourceUtils resourceUtils = new ResourceUtils();
	AntMavenUtils antMvnUtils = new AntMavenUtils();
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
	 * If this value is 'false', the Maven build will proceed even if the ant build fails. If it is 'true', then the Maven build fails if the ant build
	 * fails.
	 * 
	 * @parameter expression="${ant.failOnError}" default-value="true"
	 */
	private boolean failOnError;

	/**
	 * The build file to use. This supports Spring 3.0 resource URL expressions eg "classpath:build.xml" or "http://myurl/build.xml". The ant-maven-plugin
	 * classpath is what is searched when using the "classpath:" notation
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
	 * Filename to redirect the ant output to. This is relative to the base directory of the current project
	 * 
	 * @parameter expression="${ant.output}"
	 */
	private String output;

	/**
	 * If true, pass all Maven properties to Ant
	 * 
	 * @parameter expression="${ant.inheritAll}" default-value="true"
	 */
	private String inheritAll;

	/**
	 * If true, pass Maven object references to Ant (MavenProject, MavenProjectHelper, ArtifactRepository - for the local repo)
	 * 
	 * @parameter expression="${ant.inheritRefs}" default-value="true"
	 */
	private String inheritRefs;

	/**
	 * If they give us "http://myurl/mybuild.xml", this gets set to "mybuild.xml"
	 */
	private String antFilename;

	/**
	 * If they give us "http://myurl/mybuild.xml" this gets set to "target/ant/mybuild.xml"
	 */
	private String relativeLocalFilename;

	/**
	 * 
	 */
	@Override
	public void execute() throws MojoExecutionException {
		// Might be skipping this execution
		if (isSkip()) {
			return;
		}

		// The currently executing project
		MavenProject mavenProject = getMavenProject();

		try {
			// Setup the build file
			handleAntfile();

			// Initialize an Ant project
			Project antProject = getAntProject();

			// Setup logging
			BuildLogger antLogger = antMvnUtils.getBuildLogger(getLog());
			antProject.addBuildListener(antLogger);

			// Create the Ant equivalents of important Maven classpath's
			Map<String, Path> pathRefs = antMvnUtils.getPathRefs(antProject, mavenProject, pluginArtifacts);

			// Collect some Maven objects
			Map<String, ?> mavenRefs = getMavenRefs(mavenProject);

			// Add both as references to the Ant project
			antMvnUtils.addRefs(antProject, pathRefs);
			antMvnUtils.addRefs(antProject, mavenRefs);

			// Add the Maven classpath's as simple properties (for convenience)
			antMvnUtils.setProperties(antProject, pathRefs);

			// Initialize Maven ant tasks
			initMavenTasks(antProject);

			// Ant project needs actual properties vs. using expression evaluator when calling an external build file.
			antMvnUtils.copyProperties(mavenProject, antProject, propertyPrefix, getLog(), localRepository);

			// Execute the target from our wrapper. This calls the target from the build file they supplied
			getLog().info("Executing tasks");
			antProject.executeTarget(DEFAULT_ANT_TARGET_NAME);
			getLog().info("Executed tasks");

			// Copy properties from Ant back to Maven (if needed)
			if (exportAntProperties) {
				antMvnUtils.copyProperties(antProject, mavenProject, getLog());
			}
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("DependencyResolutionRequiredException: " + e.getMessage(), e);
		} catch (BuildException e) {
			handleBuildException(e);
		} catch (Throwable e) {
			throw new MojoExecutionException("Error executing ant tasks: " + e.getMessage(), e);
		}
	}

	/**
	 * Determine if mojo execution should be skipped
	 */
	protected boolean isSkip() {
		if (skip) {
			getLog().info("Skipping Ant execution");
			return true;
		}
		return false;
	}

	/**
	 * Create a wrapper build file that calls into the build file they supplied us with. Initialize an Ant project from the wrapper file.
	 */
	public Project getAntProject() throws IOException {
		Project antProject = new Project();
		File antBuildFile = createBuildWrapper();
		ProjectHelper.configureProject(antProject, antBuildFile);
		antProject.init();
		return antProject;
	}

	/**
	 * Collect Maven model objects, the currently executing project, the project helper, and the local repository
	 */
	protected Map<String, ?> getMavenRefs(MavenProject mavenProject) {
		Map<String, Object> mavenRefs = new HashMap<String, Object>();
		mavenRefs.put(DEFAULT_MAVEN_PROJECT_REFID, getMavenProject());
		mavenRefs.put(DEFAULT_MAVEN_PROJECT_HELPER_REFID, projectHelper);
		mavenRefs.put("maven.local.repository", localRepository);
		return mavenRefs;
	}

	/**
	 * There was an Ant build exception
	 */
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

	/**
	 * Default XML that wraps the build file they supplied us with
	 */
	protected String getDefaultXML(AntTaskPojo atp) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(XML_HEADER);
		sb.append(getProjectOpen());
		sb.append("  <target name=\"" + DEFAULT_ANT_TARGET_NAME + "\">\n");
		sb.append("    " + getXML(atp) + "\n");
		sb.append("  </target>\n");
		sb.append("</project>\n");
		return sb.toString();
	}

	/**
	 * Write the ant target and surrounding tags to a temporary file
	 */
	protected File createBuildWrapper() throws IOException {
		AntTaskPojo atp = getAntTaskPojo();
		String xml = getDefaultXML(atp);

		File buildFile = new File(ANT_BUILD_DIR + FS + antFilename);

		buildFile.getParentFile().mkdirs();
		FileUtils.fileWrite(buildFile.getAbsolutePath(), UTF_8, xml);
		return buildFile;
	}

	/**
	 * Copy the build file to a local temp directory and preserve some information about the filename
	 */
	protected void handleAntfile() throws IOException {
		String filename = resourceUtils.getFilename(file);

		// The fileName should probably use the plugin executionId instead of target
		if (!StringUtils.isBlank(target)) {
			filename = target + "-" + filename;
		}
		antFilename = filename;
		relativeLocalFilename = ANT_BUILD_DIR + FS + "local-" + antFilename;
		File localFile = new File(relativeLocalFilename);
		resourceUtils.copy(file, localFile.getAbsolutePath());
	}

	/**
	 * Aggregate some of the Maven configuration into a pojo
	 */
	protected AntTaskPojo getAntTaskPojo() {
		AntTaskPojo pojo = new AntTaskPojo();
		pojo.setAntfile(relativeLocalFilename);
		pojo.setTarget(target);
		pojo.setOutput(output);
		pojo.setInheritAll(Boolean.parseBoolean(inheritAll));
		pojo.setInheritRefs(Boolean.parseBoolean(inheritRefs));
		return pojo;
	}

	/**
	 * XML for the Ant project tag
	 */
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

	/**
	 * Convert an AntTaskPojo into XML
	 * 
	 * http://ant.apache.org/manual/Tasks/ant.html
	 */
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

	/**
	 * Return XML for an attribute
	 */
	protected String attr(String name, String value) {
		if (StringUtils.isEmpty(value)) {
			return "";
		} else {
			return " " + name + "=\"" + value + "\"";
		}
	}

}

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
 * Maven AntRun Mojo. <br/>
 * This plugin provides the capability of calling Ant tasks from a POM by running the nested ant tasks inside the &lt;tasks/&gt; parameter. It is
 * encouraged to move the actual tasks to a separate build.xml file and call that file with an &lt;ant/&gt; task.
 * 
 * @author <a href="mailto:kenney@apache.org">Kenney Westerhof</a>
 * @author <a href="mailto:vincent.siveton@gmail.com">Vincent Siveton</a>
 * @version $Id: AntRunMojo.java 1190514 2011-10-28 19:27:43Z bimargulies $
 * @goal run2
 * @threadSafe
 * @requiresDependencyResolution test
 */
public class AntRunMojo extends AbstractMojo {
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
	 * @parameter default-value=""
	 * @since 1.4
	 */
	private String propertyPrefix;

	/**
	 * The xml tag prefix to use for the built in Ant tasks. This prefix needs to be prepended to each task referenced in the antrun target config. For
	 * example, a prefix of "mvn" means that the attachartifact task is referenced by "&lt;mvn:attachartifact&gt;" The default value of an empty string
	 * means that no prefix is used for the tasks.
	 * 
	 * @parameter default-value=""
	 * @since 1.5
	 */
	private String customTaskPrefix = "";

	/**
	 * The name of a property containing the list of all dependency versions. This is used for the removing the versions from the filenames.
	 * 
	 * @parameter default-value="maven.project.dependencies.versions"
	 */
	private String versionsPropertyName;

	/**
	 * @parameter expression="${ant.target} default-value="main"
	 */
	private String target;

	/**
	 * Specifies whether the Antrun execution should be skipped.
	 * 
	 * @parameter expression="${maven.antrun.skip}" default-value="false"
	 * @since 1.7
	 */
	private boolean skip;

	/**
	 * Specifies whether the Ant properties should be propagated to the Maven properties.
	 * 
	 * @parameter default-value="false"
	 * @since 1.7
	 */
	private boolean exportAntProperties;

	/**
	 * Specifies whether a failure in the ant build leads to a failure of the Maven build.
	 * 
	 * If this value is 'true', the Maven build will proceed even if the ant build fails. If it is 'false', then the Maven build fails if the ant build
	 * fails.
	 * 
	 * @parameter default-value="true"
	 * @since 1.7
	 */
	private boolean failOnError;

	/**
	 * @parameter expression="${ant.location}"
	 */
	private String location;

	private File localFile;

	/**
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException {
		if (skip) {
			getLog().info("Skipping Antrun execution");
			return;
		}

		MavenProject mavenProject = getMavenProject();

		if (target == null) {
			getLog().info("No ant target defined - SKIPPED");
			// return;
		}

		if (propertyPrefix == null) {
			propertyPrefix = "";
		}

		try {
			if (!StringUtils.isEmpty(location)) {
				String dir = project.getBuild().getDirectory() + "/antrun";
				String filename = "build-local.xml";
				localFile = new File(dir + "/" + filename);
				resourceUtils.copy(location, localFile.getAbsolutePath());
			}

			Project antProject = new Project();
			File antBuildFile = createBuildWrapper();
			ProjectHelper.configureProject(antProject, antBuildFile);
			antProject.init();

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

			antProject.addBuildListener(antLogger);
			antProject.setBaseDir(mavenProject.getBasedir());

			Path p = new Path(antProject);
			p.setPath(StringUtils.join(mavenProject.getCompileClasspathElements().iterator(), File.pathSeparator));

			/* maven.dependency.classpath it's deprecated as it's equal to maven.compile.classpath */
			antProject.addReference("maven.dependency.classpath", p);
			antProject.addReference("maven.compile.classpath", p);

			p = new Path(antProject);
			p.setPath(StringUtils.join(mavenProject.getRuntimeClasspathElements().iterator(), File.pathSeparator));
			antProject.addReference("maven.runtime.classpath", p);

			p = new Path(antProject);
			p.setPath(StringUtils.join(mavenProject.getTestClasspathElements().iterator(), File.pathSeparator));
			antProject.addReference("maven.test.classpath", p);

			/* set maven.plugin.classpath with plugin dependencies */
			antProject.addReference("maven.plugin.classpath", getPathFromArtifacts(pluginArtifacts, antProject));

			antProject.addReference(DEFAULT_MAVEN_PROJECT_REFID, getMavenProject());
			antProject.addReference(DEFAULT_MAVEN_PROJECT_HELPER_REFID, projectHelper);
			antProject.addReference("maven.local.repository", localRepository);
			initMavenTasks(antProject);

			// Ant project needs actual properties vs. using expression evaluator when calling an external build file.
			copyProperties(mavenProject, antProject);

			getLog().info("Executing tasks");
			antProject.executeTarget("main");
			getLog().info("Executed tasks");

			copyProperties(antProject, mavenProject);
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("DependencyResolutionRequiredException: " + e.getMessage(), e);
		} catch (BuildException e) {
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
		} catch (Throwable e) {
			throw new MojoExecutionException("Error executing ant tasks: " + e.getMessage(), e);
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
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<project name=\"maven-antrun-\" default=\"main\">\n");
		sb.append("  <target name=\"main\">\n");
		sb.append("    <property name=\"maven.plugin.classpath\" refid=\"maven.plugin.classpath\" />\n");
		sb.append("    <ant antfile=\"" + localFile.getAbsolutePath() + "\" target=\"noop\"/>\n");
		sb.append("  </target>\n");
		sb.append("</project>\n");
		String filename = project.getBuild().getDirectory() + "/antrun/build-main.xml";
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
}

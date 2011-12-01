package org.kuali.maven.mojo;

import hudson.cli.CLI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Commandline.Argument;
import org.apache.tools.ant.types.Path;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.AntMavenUtils;
import org.kuali.maven.common.Extractor;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.common.ResourceUtils;

public class Generator {
	private static final String FS = System.getProperty("file.separator");
	public static final String JAVA_RESULT_PROPERTY = "java.result";
	Extractor extractor = new Extractor();
	PropertiesUtils propertiesUtils = new PropertiesUtils();
	ResourceUtils resourceUtils = new ResourceUtils();
	AntMavenUtils antMvnUtils = new AntMavenUtils();

	public void getJob(String name, MavenProject mvnProject, String type, String workingDir, String server, String cmd,
			Log log, List<Artifact> pluginArtifacts) throws MojoExecutionException {
		try {
			String jobName = getJobName(name, mvnProject, type);
			File output = new File(workingDir + FS + jobName + ".xml");
			FileUtils.touch(output);
			String[] args = getArgs("-s", server, cmd, jobName);
			Project antProject = getAntProject(log);
			AntContext context = getAntContext(antProject, mvnProject, args, output, pluginArtifacts);
			Task task = getJavaTask(context);
			log.info("");
			log.info("Jenkins Instance - " + mvnProject);
			log.info("Job Name - " + jobName);
			log.info("File - " + output.getAbsolutePath());
			log.info("");
			task.execute();
			int result = new Integer(antProject.getProperty(Generator.JAVA_RESULT_PROPERTY));
			handleResult(context, result, log);
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected String[] getArgs(String... args) {
		return args;
	}

	public void handleResult(AntContext context, int result, Log log) throws MojoExecutionException {
		if (result == 0) {
			return;
		}
		File file = context.getOutputFile();
		if (!file.exists() || file.length() == 0) {
			throw new MojoExecutionException("Non-zero result returned from Jenkins CLI - " + result);
		}

		try {
			List<String> lines = readLines(file);
			for (String line : lines) {
				log.error(line);
			}
			throw new MojoExecutionException("Non-zero result returned from Jenkins CLI - " + result);
		} catch (IOException e) {
			throw new MojoExecutionException("Error processing Jenkins CLI error message - " + result, e);
		}

	}

	protected List<String> readLines(File file) throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			return IOUtils.readLines(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public AntContext getAntContext(Project antProject, MavenProject mvnProject, String[] args, File outputFile,
			List<Artifact> pluginArtifacts) {
		AntContext context = new AntContext();
		context.setAntProject(antProject);
		context.setMavenProject(mvnProject);
		context.setArgs(args);
		context.setOutputFile(outputFile);
		context.setPluginArtifacts(pluginArtifacts);
		context.setResultProperty(Generator.JAVA_RESULT_PROPERTY);
		return context;
	}

	public Java getJavaTask(AntContext context) throws DependencyResolutionRequiredException {
		Project antProject = context.getAntProject();
		MavenProject mvnProject = context.getMavenProject();
		List<Artifact> pluginArtifacts = context.getPluginArtifacts();

		Java task = new Java();
		task.setProject(antProject);
		task.setClassname(CLI.class.getName());
		task.setFork(true);
		task.setOutput(context.getOutputFile());
		task.setResultProperty(context.getResultProperty());
		createArgs(context.getArgs(), task);
		Map<String, Path> pathRefs = antMvnUtils.getPathRefs(antProject, mvnProject, pluginArtifacts);
		Path pluginClasspath = pathRefs.get(AntMavenUtils.MVN_PLUGIN_CLASSPATH_KEY);
		task.setClasspath(pluginClasspath);
		return task;
	}

	protected void createArgs(String[] args, Java task) {
		for (String arg : args) {
			Argument argument = task.createArg();
			argument.setValue(arg);
		}
	}

	/**
	 * 
	 */
	public Project getAntProject(Log mavenLogger) throws IOException {
		Project antProject = new Project();
		antProject.init();
		// BuildLogger logger = antMvnUtils.getBuildLogger(mavenLogger);
		// antProject.addBuildListener(logger);
		return antProject;
	}

	public String getJobName(String name, MavenProject project, String type) {
		if (!StringUtils.isBlank(name)) {
			return name;
		}
		String majorVersion = extractor.getMajorVersion(project.getVersion());
		StringBuilder sb = new StringBuilder();
		sb.append(project.getArtifactId());
		sb.append("-");
		sb.append(majorVersion);
		sb.append("-");
		sb.append(type);
		return sb.toString();
	}

	public void generate(JobContext context) throws IOException {
		Properties properties = getProperties(context);
		String xml = resourceUtils.read(context.getTemplate());
		String resolvedXml = propertiesUtils.getResolvedValue(xml, properties);
		resourceUtils.write(context.getFilename(), resolvedXml);
	}

	public void fillInContext(JobContext context) {
		MavenProject project = context.getProject();

		String scmType = extractor.getScmType(project.getScm());
		String scmUrl = extractor.getScmUrl(project.getScm());
		String majorVersion = extractor.getMajorVersion(project.getVersion());
		String filename = getFilename(project, context.getType());

		context.setScmType(scmType);
		context.setScmUrl(scmUrl);
		context.setMajorVersion(majorVersion);
		context.setFilename(filename);

	}

	protected String getFilename(MavenProject project, String jobType) {
		String buildDir = project.getBuild().getDirectory();
		StringBuilder sb = new StringBuilder();
		sb.append(buildDir);
		sb.append(FS);
		sb.append("jenkins");
		sb.append(FS);
		sb.append(jobType);
		sb.append("-");
		sb.append("config");
		sb.append(".xml");
		return sb.toString();
	}

	protected Properties getProperties(JobContext context) throws IOException {
		MavenProject project = context.getProject();

		List<String> locations = getLocations(context);
		Properties resourceProperties = propertiesUtils.getProperties(locations);
		Properties jenkinsProperties = getJenkinsProperties(context);
		Properties projectProperties = project.getProperties();
		Properties environmentProperties = propertiesUtils.getEnvironmentProperties();
		Properties systemProperties = System.getProperties();

		Properties properties = new Properties();
		properties.putAll(resourceProperties);
		properties.putAll(jenkinsProperties);
		properties.putAll(projectProperties);
		properties.putAll(environmentProperties);
		properties.putAll(systemProperties);
		return properties;
	}

	protected Properties getJenkinsProperties(JobContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat(context.getTimestampFormat());
		Date now = new Date(System.currentTimeMillis());
		MavenProject project = context.getProject();
		Properties properties = new Properties();
		properties.setProperty("jenkins.project.scmType", context.getScmType());
		properties.setProperty("jenkins.project.scmUrl", context.getScmUrl());
		properties.setProperty("jenkins.project.majorVersion", context.getMajorVersion());
		properties.setProperty("jenkins.project.groupId", project.getGroupId());
		properties.setProperty("jenkins.project.artifactId", project.getArtifactId());
		properties.setProperty("jenkins.build.timestamp", sdf.format(now));
		return properties;
	}

	protected List<String> getLocations(JobContext context) {
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/jenkins/kuali.properties");
		locations.add("classpath:org/kuali/jenkins/jenkins.properties");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/common.xml");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/" + context.getScmType() + ".xml");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/types/" + context.getType() + ".xml");
		return locations;
	}

}

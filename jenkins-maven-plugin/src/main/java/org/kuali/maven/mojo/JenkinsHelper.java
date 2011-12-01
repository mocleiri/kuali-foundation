package org.kuali.maven.mojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.Mojo;
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
import org.kuali.maven.mojo.context.AntContext;
import org.kuali.maven.mojo.context.CliContext;
import org.kuali.maven.mojo.context.JobContext;
import org.kuali.maven.mojo.context.MavenContext;
import org.kuali.maven.mojo.context.MojoContext;
import org.kuali.maven.mojo.context.ResultContext;

public class JenkinsHelper {
	private static final String FS = System.getProperty("file.separator");
	public static final String JAVA_RESULT_PROPERTY = "java.result";
	Extractor extractor = new Extractor();
	PropertiesUtils propertiesUtils = new PropertiesUtils();
	ResourceUtils resourceUtils = new ResourceUtils();
	AntMavenUtils antMvnUtils = new AntMavenUtils();

	protected <T> T getContext(Class<T> type, Mojo mojo) {
		try {
			T context = type.newInstance();
			BeanUtils.copyProperties(context, mojo);
			return context;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected MojoContext getMojoContext(MavenContext mvnContext, JobContext jobContext, CliContext cliContext) {
		MojoContext context = new MojoContext();
		context.setMvnContext(mvnContext);
		context.setJobContext(jobContext);
		context.setCliContext(cliContext);
		return context;
	}

	public MojoContext getJob(Mojo mojo, String name, String type) throws MojoExecutionException {
		try {
			MavenContext mvnContext = getMavenContext(mojo);
			JobContext jobContext = getJobContext(mvnContext, mojo, name, type);
			FileUtils.touch(jobContext.getLocalFile());
			CliContext cliContext = getCliContext(jobContext, mojo);
			MojoContext context = getMojoContext(mvnContext, jobContext, cliContext);
			AntContext antContext = getAntContext(context);
			context.setAntContext(antContext);

			Task task = getJavaTask(antContext);
			mojo.getLog().info("");
			mojo.getLog().info(cliContext.getServer() + " - " + cliContext.getCmd() + " - " + jobContext.getName());
			mojo.getLog().info("File - " + antContext.getOutputFile().getAbsolutePath());
			task.execute();
			int result = new Integer(antContext.getAntProject().getProperty(JAVA_RESULT_PROPERTY));
			antContext.setResult(result);
			ResultContext resultContext = handleResult(context, result, jobContext.getLocalFile());
			String location = jobContext.getLocalFile().getAbsolutePath();
			String content = resourceUtils.read(location);
			jobContext.setResolvedContent(content);
			context.setResultContext(resultContext);
			return context;
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	public void handleResults(List<MojoContext> contexts) throws MojoExecutionException {
		List<MojoContext> issues = new ArrayList<MojoContext>();
		for (MojoContext context : contexts) {
			ResultContext rc = context.getResultContext();
			if (rc.getReturnCode() != 0) {
				issues.add(context);
			}
		}
		MojoExecutionException e = null;
		for (MojoContext issue : issues) {
			Log log = issue.getMvnContext().getLog();
			ResultContext rc = issue.getResultContext();
			log.error(issue.getJobContext().getName() + " " + rc.getException().getMessage());
			e = rc.getException();
		}
		if (issues.size() > 0) {
			throw new MojoExecutionException("One or more requests had an issue", e);
		}
	}

	public List<MojoContext> getJobs(Mojo mojo, List<String> names, String[] types) throws MojoExecutionException {
		List<MojoContext> contexts = new ArrayList<MojoContext>();
		if (!isEmpty(names)) {
			for (String name : names) {
				contexts.add(getJob(mojo, name, null));
			}
		} else {
			for (String type : types) {
				contexts.add(getJob(mojo, null, type));
			}
		}
		return contexts;
	}

	protected String[] getArgs(String... args) {
		return args;
	}

	protected ResultContext getResultContext(int result, File resultFile) {
		if (result == 0) {
			return new ResultContext(result, null);
		}
		if (resultFile == null || !resultFile.exists() || resultFile.length() == 0) {
			MojoExecutionException e = new MojoExecutionException("Non-zero result returned from Jenkins CLI: "
					+ result);
			return new ResultContext(result, e);
		}

		try {
			String msg = resourceUtils.read(resultFile.getAbsolutePath());
			MojoExecutionException e = new MojoExecutionException("Non-zero result returned from Jenkins CLI: "
					+ result + "\n" + msg);
			return new ResultContext(result, e);
		} catch (IOException e) {
			MojoExecutionException ee = new MojoExecutionException("Error processing Jenkins CLI error message: "
					+ result, e);
			return new ResultContext(result, ee);
		}
	}

	protected ResultContext handleResult(MojoContext context, int result, File resultFile)
			throws MojoExecutionException {
		ResultContext resultContext = getResultContext(result, resultFile);
		boolean stopOnError = context.getMvnContext().isStopOnError();
		MojoExecutionException e = resultContext.getException();
		if (stopOnError && e != null) {
			throw e;
		} else {
			return resultContext;
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

	protected Path getPluginClasspath(Project antProject, MavenContext mvnContext) throws MojoExecutionException {
		try {
			MavenProject mvnProject = mvnContext.getProject();
			List<Artifact> pluginArtifacts = mvnContext.getPluginArtifacts();
			Map<String, Path> pathRefs = antMvnUtils.getPathRefs(antProject, mvnProject, pluginArtifacts);
			Path pluginClasspath = pathRefs.get(AntMavenUtils.MVN_PLUGIN_CLASSPATH_KEY);
			return pluginClasspath;
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("Error obtaining classpath", e);
		}
	}

	protected AntContext getAntContext(MojoContext mojoContext) throws MojoExecutionException {
		Project antProject = getAntProject();
		Path classpath = getPluginClasspath(antProject, mojoContext.getMvnContext());

		AntContext context = new AntContext();
		context.setAntProject(antProject);
		context.setClasspath(classpath);
		context.setClassname(mojoContext.getCliContext().getClassname());
		context.setArgs(mojoContext.getCliContext().getArgs());
		context.setOutputFile(mojoContext.getJobContext().getLocalFile());
		context.setResultProperty(JenkinsHelper.JAVA_RESULT_PROPERTY);
		return context;
	}

	protected Java getJavaTask(AntContext context) {
		Java task = new Java();
		task.setProject(context.getAntProject());
		task.setClassname(context.getClassname());
		task.setFork(true);
		task.setOutput(context.getOutputFile());
		task.setInput(context.getInputFile());
		task.setResultProperty(context.getResultProperty());
		createArgs(context.getArgs(), task);
		task.setClasspath(context.getClasspath());
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
	public Project getAntProject() {
		Project antProject = new Project();
		antProject.init();
		return antProject;
	}

	public List<MojoContext> pushJobsToJenkins(Mojo mojo, String[] types) throws MojoExecutionException {

		List<MojoContext> contexts = new ArrayList<MojoContext>();
		for (String type : types) {
			MojoContext context = pushJobToJenkins(mojo, type);
			contexts.add(context);
		}
		return contexts;

	}

	public MojoContext pushJobToJenkins(Mojo mojo, String type) throws MojoExecutionException {
		MojoContext genContext = generate(mojo, type);
		MojoContext createContext = new MojoContext();
		createContext.setMvnContext(genContext.getMvnContext());
		createContext.setJobContext(genContext.getJobContext());
		JobContext jobContext = genContext.getJobContext();
		CliContext cliContext = getCliContext(genContext.getJobContext(), mojo);
		createContext.setCliContext(cliContext);
		AntContext antContext = getAntContext(createContext);
		antContext.setInputFile(jobContext.getLocalFile());
		File outputFile = new File(jobContext.getLocalFile().getAbsolutePath() + ".out");
		antContext.setOutputFile(outputFile);
		createContext.setAntContext(antContext);
		Task task = getJavaTask(antContext);
		mojo.getLog().info(cliContext.getServer() + " - " + cliContext.getCmd() + " - " + jobContext.getName());
		task.execute();
		int result = new Integer(antContext.getAntProject().getProperty(JAVA_RESULT_PROPERTY));
		antContext.setResult(result);
		ResultContext resultContext = handleResult(createContext, result, outputFile);
		createContext.setResultContext(resultContext);
		return createContext;
	}

	public MojoContext generate(Mojo mojo, String type) throws MojoExecutionException {
		try {
			MavenContext mvnContext = getMavenContext(mojo);
			JobContext jobContext = getJobContext(mvnContext, mojo, null, type);
			File localFile = jobContext.getLocalFile();
			String localFilePath = localFile.getCanonicalPath();
			mojo.getLog().info("Generating: " + localFilePath);
			Properties properties = getProperties(mvnContext, jobContext);
			String xml = resourceUtils.read(jobContext.getTemplate());
			String resolvedXml = propertiesUtils.getResolvedValue(xml, properties);
			jobContext.setResolvedContent(resolvedXml);
			jobContext.setUnresolvedContent(xml);
			mvnContext.setProperties(properties);
			MojoContext context = new MojoContext();
			context.setJobContext(jobContext);
			context.setMvnContext(mvnContext);
			resourceUtils.write(localFilePath, resolvedXml);
			return context;
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	public List<MojoContext> generate(Mojo mojo, String[] types) throws MojoExecutionException {
		List<MojoContext> contexts = new ArrayList<MojoContext>();
		for (String type : types) {
			MojoContext context = generate(mojo, type);
			contexts.add(context);
		}
		return contexts;
	}

	protected JobContext getJobContext(MavenContext mvnContext, Mojo mojo, String name, String type) {
		JobContext jobContext = getContext(JobContext.class, mojo);
		jobContext.setType(type);
		String jobName = getJobName(mvnContext, name, type);
		jobContext.setName(jobName);
		String filename = getFilename(mvnContext, jobContext, jobName);
		File localFile = new File(filename);
		jobContext.setLocalFile(localFile);
		return jobContext;
	}

	protected CliContext getCliContext(JobContext jobContext, Mojo mojo) {
		CliContext context = getContext(CliContext.class, mojo);
		String[] args = getArgs("-s", context.getServer(), context.getCmd(), jobContext.getName());
		context.setArgs(args);
		return context;
	}

	protected MavenContext getMavenContext(Mojo mojo) {
		MavenContext context = getContext(MavenContext.class, mojo);
		MavenProject project = context.getProject();
		String scmType = extractor.getScmType(project.getScm());
		String scmUrl = extractor.getScmUrl(project.getScm());
		String majorVersion = extractor.getMajorVersion(project.getVersion());

		context.setMajorVersion(majorVersion);
		context.setScmType(scmType);
		context.setScmUrl(scmUrl);
		return context;
	}

	protected String getFilename(MavenContext mvnContext, JobContext jobContext, String jobName) {
		StringBuilder sb = new StringBuilder();
		sb.append(jobContext.getWorkingDir());
		sb.append(FS);
		sb.append(jobName);
		sb.append(".xml");
		return sb.toString();
	}

	protected String getJobName(MavenContext mvnContext, String name, String type) {
		if (!StringUtils.isBlank(name)) {
			return name;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(mvnContext.getProject().getArtifactId());
		sb.append("-");
		sb.append(mvnContext.getMajorVersion());
		sb.append("-");
		sb.append(type);
		return sb.toString();
	}

	protected Properties getProperties(MavenContext mvnContext, JobContext jobContext) throws IOException {

		List<String> locations = getLocations(mvnContext, jobContext);
		Properties resourceProperties = propertiesUtils.getProperties(locations);
		Properties jenkinsProperties = getJenkinsProperties(mvnContext, jobContext);
		Properties projectProperties = mvnContext.getProject().getProperties();
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

	protected Properties getJenkinsProperties(MavenContext mvnContext, JobContext jobContext) {
		SimpleDateFormat sdf = new SimpleDateFormat(jobContext.getTimestampFormat());
		Date now = new Date(System.currentTimeMillis());
		MavenProject project = mvnContext.getProject();
		Properties properties = new Properties();
		properties.setProperty("jenkins.project.scmType", mvnContext.getScmType());
		properties.setProperty("jenkins.project.scmUrl", mvnContext.getScmUrl());
		properties.setProperty("jenkins.project.majorVersion", mvnContext.getMajorVersion());
		properties.setProperty("jenkins.project.groupId", project.getGroupId());
		properties.setProperty("jenkins.project.artifactId", project.getArtifactId());
		properties.setProperty("jenkins.build.timestamp", sdf.format(now));
		return properties;
	}

	protected List<String> getLocations(MavenContext mvnContext, JobContext jobContext) {
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/jenkins/kuali.properties");
		locations.add("classpath:org/kuali/jenkins/jenkins.properties");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/common.xml");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/" + mvnContext.getScmType() + ".xml");
		locations.add("classpath:org/kuali/jenkins/jobs/properties/types/" + jobContext.getType() + ".xml");
		return locations;
	}

}

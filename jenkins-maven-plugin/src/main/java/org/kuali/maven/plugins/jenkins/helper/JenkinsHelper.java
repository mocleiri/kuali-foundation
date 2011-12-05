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
package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
import org.kuali.maven.plugins.jenkins.context.AntContext;
import org.kuali.maven.plugins.jenkins.context.CliContext;
import org.kuali.maven.plugins.jenkins.context.GAV;
import org.kuali.maven.plugins.jenkins.context.JobContext;
import org.kuali.maven.plugins.jenkins.context.MavenContext;
import org.kuali.maven.plugins.jenkins.context.MojoContext;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;
import org.kuali.maven.plugins.jenkins.context.ResultContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JenkinsHelper {

    private static final Logger logger = LoggerFactory.getLogger(JenkinsHelper.class);

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
            CliContext cliContext = getCliContext(mojo, jobContext);
            MojoContext context = getMojoContext(mvnContext, jobContext, cliContext);
            AntContext antContext = getAntContext(context);
            context.setAntContext(antContext);

            Task task = getJavaTask(antContext);
            mojo.getLog().info(cliContext.getUrl() + " - " + cliContext.getCmd() + " - " + jobContext.getName());
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

    protected boolean isIgnore(int result, List<Integer> ignoreCodes) {
        if (result == 0) {
            return true;
        }
        for (Integer ignore : ignoreCodes) {
            if (result == ignore) {
                return true;
            }
        }
        return false;
    }

    public void handleResults(List<MojoContext> contexts, List<Integer> ignoreCodes) throws MojoExecutionException {
        List<MojoContext> issues = new ArrayList<MojoContext>();
        for (MojoContext context : contexts) {
            ResultContext rc = context.getResultContext();
            int returnCode = rc.getReturnCode();
            boolean ignore = isIgnore(returnCode, ignoreCodes);
            if (!ignore) {
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

    public void handleResults(List<MojoContext> contexts) throws MojoExecutionException {
        handleResults(contexts, new ArrayList<Integer>());
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

    protected String getContents(File file) {
        try {
            if (file == null || !file.exists() || file.length() == 0) {
                return null;
            }
            return resourceUtils.read(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected List<String> getContentLines(File file) {
        try {
            if (file == null || !file.exists() || file.length() == 0) {
                return null;
            }
            return readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    protected ResultContext getResultContext(int result, File resultFile) {
        String contents = getContents(resultFile);
        List<String> lines = getContentLines(resultFile);
        if (result == 0) {
            return new ResultContext(result, null, contents, lines);
        } else {
            String msg = "Non-zero result returned from Jenkins CLI: " + result;
            MojoExecutionException e = new MojoExecutionException(msg);
            return new ResultContext(result, e, contents, lines);
        }
    }

    protected ResultContext handleResult(MojoContext context, int result, File file) throws MojoExecutionException {
        ResultContext resultContext = getResultContext(result, file);
        boolean stopOnError = context.getMvnContext().isStopOnError();
        MojoExecutionException e = resultContext.getException();
        if (stopOnError && e != null) {
            throw e;
        } else {
            return resultContext;
        }
    }

    protected Path getMyPath(MavenContext context) {
        List<Artifact> artifacts = context.getPluginArtifacts();
        List<String> list = new ArrayList<String>(artifacts.size());
        for (Iterator<Artifact> itr = artifacts.iterator(); itr.hasNext();) {
            Artifact artifact = itr.next();
            File file = artifact.getFile();
            list.add(file.getPath());
        }
        String path = StringUtils.join(list.iterator(), File.pathSeparator);
        System.out.println(path.replace(File.pathSeparator, "\n"));
        return null;
    }

    protected GAV getGav(Properties properties) {
        String groupId = properties.getProperty("jenkins.cli.groupId");
        String artifactId = properties.getProperty("jenkins.cli.artifactId");
        String version = properties.getProperty("jenkins.cli.version");
        if (anyAreBlank(groupId, artifactId, version)) {
            return null;
        }
        GAV gav = new GAV();
        gav.setVersion(version);
        gav.setArtifactId(artifactId);
        gav.setGroupId(groupId);
        return gav;
    }

    protected GAV getGav(Artifact artifact) {
        GAV gav = new GAV();
        gav.setGroupId(artifact.getGroupId());
        gav.setArtifactId(artifact.getArtifactId());
        gav.setVersion(artifact.getVersion());
        return gav;
    }

    /**
     * Return true if any of the args passed in are blank
     */
    protected boolean anyAreBlank(String... args) {
        for (String arg : args) {
            if (StringUtils.isBlank(arg)) {
                return true;
            }
        }
        return false;
    }

    protected GAV getGav(MavenContext context) throws IOException {
        String location = "classpath:org/kuali/maven/plugins/jenkins/jenkins-cli.properties";
        Properties internal = propertiesUtils.getProperties(location);
        GAV internalGAV = getGav(internal);
        GAV overrideGAV = getGav(context.getProject().getProperties());

        GAV gav = internalGAV;
        if (overrideGAV != null && !internalGAV.equals(overrideGAV)) {
            logger.info("Jenkins CLI override: [internal=" + internalGAV + ", override=" + overrideGAV + "]");
            gav = overrideGAV;
        }
        return gav;

    }

    protected File getJenkinsJar(MavenContext context) throws IOException {
        GAV gav = getGav(context);

        List<Artifact> artifacts = context.getPluginArtifacts();
        for (Iterator<Artifact> itr = artifacts.iterator(); itr.hasNext();) {
            Artifact artifact = itr.next();
            if (equals(artifact, gav)) {
                return artifact.getFile();
            }
        }
        throw new IOException("Unable to locate jenkins-cli.jar");
    }

    protected boolean equals(Artifact artifact, GAV gav) {
        GAV artifactGav = getGav(artifact);
        return artifactGav.equals(gav);
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

    protected String[] getJenkinsArgs(File jar) {
        List<String> args = new ArrayList<String>();
        args.add("java");
        args.add("-jar");
        args.add(jar.getAbsolutePath());
        args.add("-s");
        args.add("http://ci.fn.kuali.orgg");
        args.add("version");
        return args.toArray(new String[args.size()]);
    }

    protected AntContext getAntContext(MojoContext mojoContext) throws MojoExecutionException {
        Log log = mojoContext.getMvnContext().getLog();
        File jenkinsJar = null;// getJenkinsJar(mojoContext.getMvnContext());
        try {
            String[] args = getJenkinsArgs(jenkinsJar);
            ProcessBuilder builder = new ProcessBuilder(args);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            List<String> output = IOUtils.readLines(process.getInputStream());
            int result = process.waitFor();
            log.info("");
            log.info(" == Jenkins CLI Output ==");
            for (String line : output) {
                if (result == 0) {
                    log.info(line);
                } else {
                    log.error(line);
                }
            }
            log.info(" == Jenkins CLI Output ==");
            log.info("");
        } catch (Exception e) {
            throw new MojoExecutionException("Unexpected error", e);
        }

        System.out.println(jenkinsJar.getAbsolutePath());
        Project antProject = getAntProject();
        Path classpath = getPluginClasspath(antProject, mojoContext.getMvnContext());
        System.out.println(classpath.toString());
        AntContext context = new AntContext();
        context.setAntProject(antProject);
        context.setClasspath(classpath);
        context.setClassname(mojoContext.getCliContext().getClassname());
        context.setArgs(mojoContext.getCliContext().getArgs());
        if (mojoContext.getJobContext() != null) {
            context.setOutputFile(mojoContext.getJobContext().getLocalFile());
        }
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
    protected Project getAntProject() {
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
        MojoContext genContext = generate(mojo, type, false);
        MojoContext createContext = new MojoContext();
        createContext.setMvnContext(genContext.getMvnContext());
        createContext.setJobContext(genContext.getJobContext());
        JobContext jobContext = genContext.getJobContext();
        CliContext cliContext = getCliContext(mojo, genContext.getJobContext());
        createContext.setCliContext(cliContext);
        AntContext antContext = getAntContext(createContext);
        antContext.setInputFile(jobContext.getLocalFile());
        File outputFile = new File(jobContext.getLocalFile().getAbsolutePath() + ".output.txt");
        antContext.setOutputFile(outputFile);
        createContext.setAntContext(antContext);
        Task task = getJavaTask(antContext);
        mojo.getLog().info(cliContext.getUrl() + " - " + cliContext.getCmd() + " - " + jobContext.getName());
        task.execute();
        int result = new Integer(antContext.getAntProject().getProperty(JAVA_RESULT_PROPERTY));
        antContext.setResult(result);
        ResultContext resultContext = handleResult(createContext, result, outputFile);
        createContext.setResultContext(resultContext);
        return createContext;
    }

    public List<MojoContext> deleteJobs(Mojo mojo, List<String> names, String[] types) throws MojoExecutionException {
        List<MojoContext> contexts = new ArrayList<MojoContext>();
        if (!isEmpty(names)) {
            for (String name : names) {
                MojoContext context = executeCliJobCommand(mojo, name, null);
                contexts.add(context);
            }
        } else {
            for (String type : types) {
                MojoContext context = executeCliJobCommand(mojo, null, type);
                contexts.add(context);
            }
        }
        return contexts;
    }

    public void executeCli(Mojo mojo) throws MojoExecutionException {
        try {
            MavenContext context = getMavenContext(mojo);
            CliContext cliContext = getCliContext(mojo);
            String cmd = cliContext.getCmd();
            String url = context.getProject().getProperties().getProperty("jenkins.url");
            List<String> list = new ArrayList<String>();
            list.add("-s");
            list.add(url);
            String[] cmdTokens = PropertiesUtils.splitAndTrim(cmd, " ");
            Helper.addToList(list, cmdTokens);
            String[] args = list.toArray(new String[list.size()]);
            File jar = getJenkinsJar(context);
            JavaHelper jHelper = new JavaHelper();
            ProcessResult result = jHelper.executeJar(jar, args);
            logger.info("Result: " + result);
        } catch (Exception e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    public MojoContext executeCliCommand(Mojo mojo) throws MojoExecutionException {
        try {
            MavenContext mvnContext = getMavenContext(mojo);
            CliContext cliContext = getCliContext(mojo);
            MojoContext context = getMojoContext(mvnContext, null, cliContext);
            AntContext antContext = getAntContext(context);
            File outputFile = new File(mvnContext.getWorkingDir() + FS + cliContext.getCmd() + ".txt");
            FileUtils.touch(outputFile);
            antContext.setOutputFile(outputFile);
            Task task = getJavaTask(antContext);
            mojo.getLog().info(cliContext.getUrl() + " - " + cliContext.getCmd());
            task.execute();
            int result = new Integer(antContext.getAntProject().getProperty(JAVA_RESULT_PROPERTY));
            antContext.setResult(result);
            ResultContext resultContext = handleResult(context, result, outputFile);
            showResult(resultContext, mvnContext);
            context.setResultContext(resultContext);
            return context;
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    protected void showResult(ResultContext resultContext, MavenContext mvnContext) {
        mvnContext.getLog().info("");
        mvnContext.getLog().info("== Jenkins CLI Output ==");
        mvnContext.getLog().info("");
        List<String> lines = resultContext.getFileContentLines();
        if (!isEmpty(lines)) {
            for (String line : lines) {
                mvnContext.getLog().info(line);
            }
        }
        mvnContext.getLog().info("");
        mvnContext.getLog().info("== Jenkins CLI Output ==");
        mvnContext.getLog().info("");
    }

    public MojoContext executeCliJobCommand(Mojo mojo, String name, String type) throws MojoExecutionException {
        try {
            MavenContext mvnContext = getMavenContext(mojo);
            JobContext jobContext = getJobContext(mvnContext, mojo, name, type);
            FileUtils.touch(jobContext.getLocalFile());
            CliContext cliContext = getCliContext(mojo, jobContext);
            MojoContext context = getMojoContext(mvnContext, jobContext, cliContext);
            AntContext antContext = getAntContext(context);
            Task task = getJavaTask(antContext);
            mojo.getLog().info(cliContext.getUrl() + " - " + cliContext.getCmd() + " - " + jobContext.getName());
            task.execute();
            int result = new Integer(antContext.getAntProject().getProperty(JAVA_RESULT_PROPERTY));
            antContext.setResult(result);
            ResultContext resultContext = handleResult(context, result, jobContext.getLocalFile());
            context.setResultContext(resultContext);
            return context;
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    public MojoContext generate(Mojo mojo, String type, boolean logFilename) throws MojoExecutionException {
        try {
            MavenContext mvnContext = getMavenContext(mojo);
            JobContext jobContext = getJobContext(mvnContext, mojo, null, type);
            File localFile = jobContext.getLocalFile();
            String localFilePath = localFile.getCanonicalPath();
            if (logFilename) {
                mojo.getLog().info("Generating: " + localFilePath);
            }
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

    public MojoContext generate(Mojo mojo, String type) throws MojoExecutionException {
        return generate(mojo, type, true);
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
        String filename = getFilename(jobContext, jobName);
        File localFile = new File(filename);
        jobContext.setLocalFile(localFile);
        return jobContext;
    }

    protected CliContext getCliContext(Mojo mojo) {
        CliContext context = getContext(CliContext.class, mojo);
        String[] args = getArgs("-s", context.getUrl(), context.getCmd());
        context.setArgs(args);
        return context;
    }

    protected CliContext getCliContext(Mojo mojo, JobContext jobContext) {
        CliContext context = getContext(CliContext.class, mojo);
        String[] args = getArgs("-s", context.getUrl(), context.getCmd(), jobContext.getName());
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

    protected String getFilename(JobContext jobContext, String jobName) {
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
        locations.add("classpath:org/kuali/jenkins/jobs/properties/common.xml");
        locations.add("classpath:org/kuali/jenkins/jobs/properties/" + mvnContext.getScmType() + ".xml");
        locations.add("classpath:org/kuali/jenkins/jobs/properties/types/" + jobContext.getType() + ".xml");
        return locations;
    }

}

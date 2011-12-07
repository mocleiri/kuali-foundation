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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.Extractor;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.common.ResourceUtils;
import org.kuali.maven.plugins.jenkins.BaseMojo;
import org.kuali.maven.plugins.jenkins.CliMojo;
import org.kuali.maven.plugins.jenkins.Command;
import org.kuali.maven.plugins.jenkins.RunJobCommand;
import org.kuali.maven.plugins.jenkins.RunJobMojo;
import org.kuali.maven.plugins.jenkins.SimpleJobCommand;
import org.kuali.maven.plugins.jenkins.SimpleJobMojo;
import org.kuali.maven.plugins.jenkins.context.CliException;
import org.kuali.maven.plugins.jenkins.context.GAV;
import org.kuali.maven.plugins.jenkins.context.MavenContext;
import org.kuali.maven.plugins.jenkins.context.ProcessContext;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JenkinsHelper {

    private final Logger logger = LoggerFactory.getLogger(JenkinsHelper.class);

    public static final String XML_EXTENSION = ".xml";
    public static final int SUCCESS_CODE = 0;
    public static final int NO_SUCH_COMMAND = 255;
    public static final String SERVER_ARG = "-s";
    public static final String FS = System.getProperty("file.separator");

    Extractor extractor = new Extractor();
    PropertiesUtils propertiesUtils = new PropertiesUtils();
    ResourceUtils resourceUtils = new ResourceUtils();
    JavaHelper javaHelper = new JavaHelper();
    CommandHelper cmdHelper = new CommandHelper();

    public String[] getArgs(SimpleJobCommand sjc) {
        List<String> args = new ArrayList<String>();
        args.add(sjc.getJenkinsCommand());
        args.add(sjc.getJobName());
        return Helper.toArray(args);
    }

    protected <T> T getContext(Class<T> type, BaseMojo mojo) {
        try {
            T context = type.newInstance();
            copyProperties(context, mojo);
            return context;
        } catch (Exception e) {
            throw new CliException(e);
        }
    }

    protected void copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new CliException(e);
        }
    }

    public void getJob(BaseMojo mojo, String cmd, String name, String type) {
        getJobs(mojo, cmd, Helper.toList(name), type);
    }

    protected List<Command> createGetJobCommands(BaseMojo mojo, String cmd, List<String> jobNames) {
        List<Command> commands = new ArrayList<Command>();
        for (String jobName : jobNames) {
            String filename = mojo.getWorkingDir() + FS + jobName + XML_EXTENSION;
            String[] args = { cmd, jobName };
            Command command = new Command();
            command.setArgs(Arrays.asList(args));
            command.setStdout(new File(filename));
            commands.add(command);
        }
        return commands;
    }

    public void getJobs(BaseMojo mojo, String cmd, List<String> names, String types) {
        MavenContext context = getMavenContext(mojo);
        List<String> jobNames = getJobNames(context, names, types);
        List<Command> commands = createGetJobCommands(mojo, cmd, jobNames);
        executeCli(mojo, commands);
    }

    protected List<String> getJobNames(MavenContext context, List<String> names, String types) {
        if (!Helper.isEmpty(names)) {
            return names;
        }
        List<String> newNames = new ArrayList<String>();
        String[] tokens = Helper.splitAndTrimCSV(types);
        for (String type : tokens) {
            String name = getJobName(context, type);
            newNames.add(name);
        }
        return newNames;
    }

    protected GAV getGav(Properties properties) {
        String groupId = properties.getProperty("jenkins.cli.groupId");
        String artifactId = properties.getProperty("jenkins.cli.artifactId");
        String version = properties.getProperty("jenkins.cli.version");
        if (Helper.anyAreBlank(groupId, artifactId, version)) {
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

    protected GAV getGav(MavenProject project) {
        try {
            String location = "classpath:org/kuali/maven/plugins/jenkins/jenkins-cli.properties";
            Properties internal = propertiesUtils.getProperties(location);
            GAV internalGAV = getGav(internal);
            GAV overrideGAV = getGav(project.getProperties());

            GAV gav = internalGAV;
            if (overrideGAV != null && !internalGAV.equals(overrideGAV)) {
                logger.info("Jenkins CLI override: [internal=" + internalGAV + ", override=" + overrideGAV + "]");
                gav = overrideGAV;
            }
            return gav;
        } catch (IOException e) {
            throw new CliException(e);
        }

    }

    protected File getJenkinsJar(MavenProject project, List<Artifact> pluginArtifacts) {
        GAV gav = getGav(project);
        File jar = getJar(gav, pluginArtifacts);
        if (jar == null) {
            throw new CliException("Unable to locate jenkins-cli.jar");
        } else {
            return jar;
        }
    }

    protected File getJar(GAV gav, List<Artifact> artifacts) {
        for (Artifact artifact : artifacts) {
            if (equals(artifact, gav)) {
                return artifact.getFile();
            }
        }
        return null;
    }

    protected boolean equals(Artifact artifact, GAV gav) {
        GAV artifactGav = getGav(artifact);
        return artifactGav.equals(gav);
    }

    protected List<File> getFiles(File workingDir) {
        if (!workingDir.exists()) {
            return new ArrayList<File>();
        }
        File[] files = workingDir.listFiles();
        return Arrays.asList(files);
    }

    public void pushJobs(BaseMojo mojo, String cmd) {
        try {
            List<Command> commands = getCommands(mojo.getWorkingDir(), cmd);
            executeCli(mojo, commands);
        } catch (IOException e) {
            throw new CliException(e);
        }

    }

    protected List<Command> getCommands(File workingDir, String cmd) throws IOException {
        List<File> files = getFiles(workingDir);
        List<Command> commands = new ArrayList<Command>();
        for (File file : files) {
            Command command = getCommand(file, cmd);
            commands.add(command);
        }
        return commands;
    }

    protected String getJobName(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(XML_EXTENSION);
        return name.substring(0, pos);
    }

    protected Command getCommand(File file, String cmd) throws IOException {
        String stdin = resourceUtils.read(file.getAbsolutePath());
        String jobName = getJobName(file);
        String[] args = { cmd, jobName };
        Command command = new Command();
        command.setStdin(stdin);
        command.setArgs(Arrays.asList(args));
        return command;
    }

    protected String getInput(Command cmd) {
        if (!StringUtils.isBlank(cmd.getStdinUrl())) {
            try {
                return resourceUtils.read(cmd.getStdinUrl());
            } catch (IOException e) {
                throw new CliException(e);
            }
        } else {
            return cmd.getStdin();
        }
    }

    public ProcessResult executeCli(File jar, String url, Command cmd) {
        String input = getInput(cmd);
        return executeCli(jar, url, cmd.getArgs(), input);
    }

    public ProcessResult executeCli(File jar, String url, List<String> args, String input) {
        String[] cliArgs = getCliArgs(url, args);
        return javaHelper.executeJar(jar, cliArgs, input);
    }

    protected String[] getCliArgs(String url, List<String> args) {
        List<String> list = new ArrayList<String>();
        list.add(SERVER_ARG);
        list.add(url);
        list.addAll(args);
        return Helper.toArray(list);
    }

    protected boolean isSuccess(int exitValue, int... successValues) {
        for (int successValue : successValues) {
            if (exitValue == successValue) {
                return true;
            }
        }
        return false;
    }

    protected void handleResult(Command command, ProcessResult result, BaseMojo mojo) {
        List<Integer> successCodes = mojo.getSuccessCodesList();
        int exitValue = result.getExitValue();
        if (isSuccess(exitValue, Helper.toIntArray(successCodes))) {
            handleSuccess(command, result);
        } else {
            handleFailure(mojo, result);
        }
    }

    protected void logInfo(List<String> lines) {
        if (lines.size() == 0) {
            return;
        }
        for (String line : lines) {
            logger.info(line);
        }
    }

    protected void logError(List<String> lines) {
        String top = getTop(lines);
        if (top != null) {
            logger.error(top);
        }
    }

    protected void logWarning(List<String> lines) {
        String top = getTop(lines);
        if (top != null) {
            logger.warn(top);
        }
    }

    protected String getTop(List<String> lines) {
        for (String line : lines) {
            if (!StringUtils.isBlank(line)) {
                return line;
            }
        }
        return null;
    }

    protected void handleFailure(BaseMojo mojo, ProcessResult result) {
        if (mojo.isStopOnError()) {
            logger.error("Jenkins CLI Exception:" + getErrorMessage(result));
            throw new CliException("Jenkins CLI Exception");
        } else {
            if (mojo.isStopOnError()) {
                logError(result.getOutputLines());
            } else {
                logWarning(result.getOutputLines());
            }
        }
    }

    protected void handleSuccess(Command command, ProcessResult result) {
        File stdout = command.getStdout();
        if (stdout != null) {
            write(stdout.getAbsolutePath(), result.getOutput());
        } else {
            logInfo(result.getOutputLines());
        }
    }

    protected void write(String filename, String content) {
        try {
            resourceUtils.write(filename, content);
        } catch (IOException e) {
            throw new CliException(e);
        }
    }

    public void executeCli(SimpleJobMojo mojo) {
        MavenContext context = getMavenContext(mojo);
        String jobName = getJobName(context, mojo.getName(), mojo.getType());
        SimpleJobCommand sjc = new SimpleJobCommand();
        sjc.setJenkinsCommand(mojo.getJobCmd());
        sjc.setJobName(jobName);
        Command command = new Command();
        command.setArgs(cmdHelper.toArgs(sjc));
        executeCli(mojo, command);
    }

    protected Map<String, String> getBuildParameters(Map<String, String> map, String csv) {
        Map<String, String> buildParameters = new HashMap<String, String>();
        if (!Helper.isEmpty(map)) {
            buildParameters.putAll(map);
        }
        if (!Helper.isEmpty(csv)) {
            String[] keyValuePairs = Helper.splitAndTrimCSV(csv);
            Map<String, String> csvMap = Helper.toMap(keyValuePairs);
            buildParameters.putAll(csvMap);
        }
        return buildParameters;
    }

    public void executeCli(RunJobMojo mojo) {
        MavenContext context = getMavenContext(mojo);
        String jobName = getJobName(context, mojo.getName(), mojo.getType());
        Map<String, String> params = getBuildParameters(mojo.getParamMap(), mojo.getParams());

        RunJobCommand rjc = new RunJobCommand();
        rjc.setJobName(jobName);
        rjc.setParams(params);
        rjc.setJenkinsCommand(mojo.getJobCmd());
        rjc.setWait(mojo.isWait());
        rjc.setSkipIfNoChanges(mojo.isSkipIfNoChanges());

        Command command = new Command();
        command.setArgs(cmdHelper.toArgs(rjc));
        executeCli(mojo, command);
    }

    public void executeCli(CliMojo mojo) {
        List<Command> cmds = cmdHelper.getCommands(mojo);
        executeCli(mojo, cmds);

    }

    public void executeCli(BaseMojo mojo, Command command) {
        executeCli(mojo, Helper.toList(command));
    }

    public void executeCli(BaseMojo mojo, List<Command> commands) {
        File jar = getJenkinsJar(mojo.getProject(), mojo.getPluginArtifacts());
        String url = mojo.getUrl();
        logger.info("Jenkins CLI: " + jar.getPath());
        logger.info("Jenkins URL: " + url);
        List<ProcessResult> results = new ArrayList<ProcessResult>();
        for (Command command : commands) {
            logger.info("Issuing command '" + Helper.toString(command.getArgs()) + "'");
            ProcessResult result = executeCli(jar, url, command);
            handleResult(command, result, mojo);
            results.add(result);
        }
        handleResults(results, mojo);
    }

    protected void handleResults(List<ProcessResult> results, BaseMojo mojo) {
        int[] successCodes = Helper.toIntArray(mojo.getSuccessCodesList());
        List<ProcessResult> errors = new ArrayList<ProcessResult>();
        for (ProcessResult result : results) {
            int exitValue = result.getExitValue();
            if (!Helper.isMatch(exitValue, successCodes)) {
                errors.add(result);
            }
        }
        if (errors.size() == 0) {
            return;
        }
        if (mojo.isFailOnError()) {
            logger.error(getErrorMessage(errors));
            throw new CliException("Jenkins CLI error");
        } else {
            logger.warn(getErrorMessage(errors));
        }
    }

    protected String getErrorMessage(List<ProcessResult> errors) {
        StringBuilder sb = new StringBuilder();
        if (errors.size() == 1) {
            sb.append("There was 1 error.");
        } else {
            sb.append("There were " + errors.size() + " errors.");
        }
        for (ProcessResult result : errors) {
            sb.append(getErrorMessage(result));
        }
        return sb.toString();
    }

    protected String getErrorMessage(ProcessResult result) {
        ProcessContext context = result.getContext();
        int exitValue = result.getExitValue();
        String top = getTop(result.getOutputLines());
        String[] args = result.getContext().getArgs();
        String cmd = Helper.toString(args, " ");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("msg: " + top + "\n");
        sb.append("executable: " + context.getExecutable() + "\n");
        sb.append("cmd: " + cmd + "\n");
        sb.append("result: " + exitValue + "\n");
        sb.append("input: " + Helper.toEmpty(context.getInput()) + "\n");
        if (exitValue != NO_SUCH_COMMAND) {
            sb.append("details: " + result.getOutput());
        }
        return sb.toString();
    }

    public void generate(BaseMojo mojo, String types) {
        MavenContext context = getMavenContext(mojo);
        String[] tokens = Helper.splitAndTrimCSV(types);
        for (String type : tokens) {
            generate(mojo, context, type);
        }
    }

    public void generate(BaseMojo mojo, MavenContext context, String type) {
        try {
            String jobName = getJobName(context, type);
            String filename = mojo.getWorkingDir() + FS + jobName + XML_EXTENSION;
            mojo.getLog().info("Generating: " + filename);
            Properties properties = getProperties(context, type, mojo.getTimestampFormat());
            String xml = resourceUtils.read(mojo.getTemplate());
            String resolvedXml = propertiesUtils.getResolvedValue(xml, properties);
            resourceUtils.write(filename, resolvedXml);
        } catch (IOException e) {
            throw new CliException(e);
        }
    }

    public MavenContext getMavenContext(BaseMojo mojo) {
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

    public String getJobName(MavenContext context, String name, String type) {
        if (!StringUtils.isBlank(name)) {
            return name;
        } else {
            return getJobName(context, type);
        }
    }

    public String getJobName(MavenContext context, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getProject().getArtifactId());
        sb.append("-");
        sb.append(context.getMajorVersion());
        sb.append("-");
        sb.append(type);
        return sb.toString();
    }

    protected Properties getProperties(MavenContext mvnContext, String type, String timestampFormat) throws IOException {

        List<String> locations = getLocations(mvnContext, type);
        Properties resourceProperties = propertiesUtils.getProperties(locations);
        Properties jenkinsProperties = getJenkinsProperties(mvnContext, timestampFormat);
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

    protected Properties getJenkinsProperties(MavenContext context, String timestampFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);
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

    protected List<String> getLocations(MavenContext context, String type) {
        List<String> locations = new ArrayList<String>();
        locations.add("classpath:org/kuali/jenkins/jobs/properties/common.xml");
        locations.add("classpath:org/kuali/jenkins/jobs/properties/" + context.getScmType() + XML_EXTENSION);
        locations.add("classpath:org/kuali/jenkins/jobs/properties/types/" + type + XML_EXTENSION);
        return locations;
    }

}

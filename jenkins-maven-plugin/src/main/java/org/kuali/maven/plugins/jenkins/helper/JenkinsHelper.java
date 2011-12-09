/**
 * Copyright 2011-2011 The Kuali Foundation
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

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.Extractor;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.common.ResourceUtils;
import org.kuali.maven.plugins.jenkins.BaseMojo;
import org.kuali.maven.plugins.jenkins.CliMojo;
import org.kuali.maven.plugins.jenkins.Command;
import org.kuali.maven.plugins.jenkins.DeleteJobMojo;
import org.kuali.maven.plugins.jenkins.DeleteJobsMojo;
import org.kuali.maven.plugins.jenkins.GenJobMojo;
import org.kuali.maven.plugins.jenkins.GenJobsMojo;
import org.kuali.maven.plugins.jenkins.GetJobsMojo;
import org.kuali.maven.plugins.jenkins.PushJobsMojo;
import org.kuali.maven.plugins.jenkins.RunJobCommand;
import org.kuali.maven.plugins.jenkins.RunJobMojo;
import org.kuali.maven.plugins.jenkins.RunJobsMojo;
import org.kuali.maven.plugins.jenkins.SimpleJobCommand;
import org.kuali.maven.plugins.jenkins.context.GAV;
import org.kuali.maven.plugins.jenkins.context.JenkinsException;
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
    public static final String NONE = "NONE";
    public static final String DASH = "-";
    public static final String FS = System.getProperty("file.separator");

    Extractor extractor = new Extractor();
    PropertiesUtils propertiesUtils = new PropertiesUtils();
    ResourceUtils resourceUtils = new ResourceUtils();
    JavaHelper javaHelper = new JavaHelper();
    CommandHelper cmdHelper = new CommandHelper();

    protected List<String> getNamesList(String csvNames, List<String> names) {
        if (!Helper.isEmpty(names)) {
            return names;
        } else {
            return Helper.splitAndTrimCSVToList(csvNames);
        }
    }

    protected List<String> getJobNames(BaseMojo mojo, String csvNames, List<String> names) {
        List<String> namesList = getNamesList(csvNames, names);
        List<String> newNames = new ArrayList<String>();
        for (String name : namesList) {
            String newName = getJobName(mojo, name);
            newNames.add(newName);
        }
        return newNames;
    }

    public void execute(GetJobsMojo mojo) {
        List<String> jobNames = getJobNames(mojo, mojo.getNames(), mojo.getNameList());
        List<Command> commands = createGetJobCommands(mojo, mojo.getCmd(), jobNames);
        executeCli(mojo, commands);
    }

    protected List<Command> createGetJobCommands(BaseMojo mojo, String cmd, List<String> jobNames) {
        List<Command> commands = new ArrayList<Command>();
        for (String jobName : jobNames) {
            Command command = createGetJobCommand(mojo, cmd, jobName);
            commands.add(command);
        }
        return commands;
    }

    protected Command createGetJobCommand(BaseMojo mojo, String cmd, String jobName) {
        String filename = mojo.getWorkingDir() + FS + jobName + XML_EXTENSION;
        String[] args = { cmd, jobName };
        Command command = new Command();
        command.setArgs(Arrays.asList(args));
        command.setStdout(new File(filename));
        return command;
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
            throw new JenkinsException(e);
        }

    }

    protected File getJenkinsJar(MavenProject project, List<Artifact> pluginArtifacts) {
        GAV gav = getGav(project);
        File jar = getJar(gav, pluginArtifacts);
        if (jar == null) {
            throw new JenkinsException("Unable to locate jenkins-cli.jar");
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

    protected void pushJobs(BaseMojo mojo, String cmd) {
        try {
            List<Command> commands = getCommands(mojo.getWorkingDir(), cmd);
            executeCli(mojo, commands);
        } catch (IOException e) {
            throw new JenkinsException(e);
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
                throw new JenkinsException(e);
            }
        } else {
            return cmd.getStdin();
        }
    }

    protected ProcessResult executeCli(File jar, String url, Command cmd) {
        String input = getInput(cmd);
        return executeCli(jar, url, cmd.getArgs(), input);
    }

    protected ProcessResult executeCli(File jar, String url, List<String> args, String input) {
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

    protected int[] getSuccessCodes(BaseMojo mojo) {
        String csv = mojo.getSuccessCodes();
        return Helper.toIntArray(csv);
    }

    protected void handleResult(Command command, ProcessResult result, BaseMojo mojo) {
        int[] successCodes = getSuccessCodes(mojo);
        int exitValue = result.getExitValue();
        if (Helper.isMatch(exitValue, successCodes)) {
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
            throw new JenkinsException("Jenkins CLI Exception");
        } else {
            if (mojo.isFailOnError()) {
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
            throw new JenkinsException(e);
        }
    }

    protected Map<String, String> getBuildParameters(Map<String, String> map, String csv) {
        Map<String, String> buildParameters = new HashMap<String, String>();
        if (!Helper.isEmpty(map)) {
            buildParameters.putAll(map);
        }
        if (!StringUtils.isBlank(csv)) {
            String[] keyValuePairs = Helper.splitAndTrimCSV(csv);
            Map<String, String> csvMap = Helper.toMap(keyValuePairs);
            buildParameters.putAll(csvMap);
        }
        return buildParameters;
    }

    protected RunJobCommand getRunJobCommand(RunJobMojo mojo, String jobName, Map<String, String> params) {
        RunJobCommand rjc = new RunJobCommand();
        rjc.setName(jobName);
        rjc.setParams(params);
        rjc.setCommand(mojo.getCmd());
        rjc.setWait(mojo.isWait());
        rjc.setSkipIfNoChanges(mojo.isSkipIfNoChanges());
        return rjc;
    }

    public void updateMojo(BaseMojo mojo) {
        MavenProject project = mojo.getProject();
        String scmType = extractor.getScmType(project.getScm());
        String scmUrl = extractor.getScmUrl(project.getScm());
        String majorVersion = extractor.getMajorVersion(project.getVersion());

        if (StringUtils.isBlank(mojo.getScmType())) {
            mojo.setScmType(scmType);
        }
        if (StringUtils.isBlank(mojo.getScmUrl())) {
            mojo.setScmUrl(scmUrl);
        }
        if (StringUtils.isBlank(mojo.getMajorVersion())) {
            mojo.setMajorVersion(majorVersion);
        }
    }

    public void execute(DeleteJobMojo mojo) {
        String jobName = getJobName(mojo, mojo.getName());
        SimpleJobCommand sjc = getSimpleJobCommand(jobName, mojo.getCmd());
        Command command = new Command();
        command.setArgs(cmdHelper.toArgs(sjc));
        executeCli(mojo, command);
    }

    protected List<SimpleJobCommand> getSimpleJobCommands(List<String> names, String cmd) {
        List<SimpleJobCommand> commands = new ArrayList<SimpleJobCommand>();
        for (String name : names) {
            SimpleJobCommand sjc = getSimpleJobCommand(name, cmd);
            commands.add(sjc);
        }
        return commands;
    }

    protected SimpleJobCommand getSimpleJobCommand(String name, String cmd) {
        SimpleJobCommand sjc = new SimpleJobCommand();
        sjc.setName(name);
        sjc.setCommand(cmd);
        return sjc;
    }

    public void execute(DeleteJobsMojo mojo) {
        List<String> jobNames = getJobNames(mojo, mojo.getNames(), mojo.getNameList());
        List<SimpleJobCommand> sjcs = getSimpleJobCommands(jobNames, mojo.getCmd());
        List<Command> commands = getCommandsFromSimple(sjcs);
        executeCli(mojo, commands);
    }

    public void execute(RunJobMojo mojo) {
        String jobName = getJobName(mojo, mojo.getName());
        Map<String, String> params = getBuildParameters(mojo.getParamMap(), mojo.getParams());
        RunJobCommand rjc = getRunJobCommand(mojo, jobName, params);
        Command command = new Command();
        command.setArgs(cmdHelper.toArgs(rjc));
        executeCli(mojo, command);
    }

    protected void updateCommands(List<RunJobCommand> commands, String cmd, BaseMojo mojo) {
        for (RunJobCommand command : commands) {
            String name = getJobName(mojo, command.getName());
            command.setName(name);
            command.setCommand(cmd);
        }
    }

    protected List<Command> getCommandsFromSimple(List<SimpleJobCommand> sjcs) {
        List<Command> commands = new ArrayList<Command>();
        for (SimpleJobCommand sjc : sjcs) {
            Command command = new Command();
            command.setArgs(cmdHelper.toArgs(sjc));
            commands.add(command);
        }
        return commands;
    }

    protected List<Command> getCommands(List<RunJobCommand> rjcs) {
        List<Command> commands = new ArrayList<Command>();
        for (RunJobCommand rjc : rjcs) {
            Command command = new Command();
            command.setArgs(cmdHelper.toArgs(rjc));
            commands.add(command);
        }
        return commands;
    }

    public void execute(RunJobsMojo mojo) {
        // nothing to do
        if (Helper.isEmpty(mojo.getCommands())) {
            return;
        }
        updateCommands(mojo.getCommands(), mojo.getCmd(), mojo);
        List<Command> commands = getCommands(mojo.getCommands());
        executeCli(mojo, commands);
    }

    public void execute(PushJobsMojo mojo) {
        pushJobs(mojo, mojo.getCmd());
    }

    public void execute(CliMojo mojo) {
        List<Command> cmds = cmdHelper.getCommands(mojo);
        executeCli(mojo, cmds);
    }

    protected void executeCli(BaseMojo mojo, Command command) {
        executeCli(mojo, Helper.toList(command));
    }

    protected void executeCli(BaseMojo mojo, List<Command> commands) {
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
        int[] successCodes = getSuccessCodes(mojo);
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
            throw new JenkinsException("Jenkins CLI error");
        } else {
            logger.warn(getWarnMessage(errors));
        }
    }

    protected String getWarnMessage(List<ProcessResult> errors) {
        StringBuilder sb = new StringBuilder();
        if (errors.size() == 1) {
            sb.append("There was 1 error.");
        } else {
            sb.append("There were " + errors.size() + " errors.");
        }
        return sb.toString();
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

    public void execute(GenJobsMojo mojo) {
        List<String> types = Helper.splitAndTrimCSVToList(mojo.getTypes());
        generateJobs(mojo, types);
    }

    public void execute(GenJobMojo mojo) {
        generateJob(mojo, mojo.getType());
    }

    protected void generateJobs(BaseMojo mojo, List<String> types) {
        for (String type : types) {
            generateJob(mojo, type);
        }
    }

    protected String getRelativePath(BaseMojo mojo, String filename) {
        File dir = mojo.getProject().getBasedir();
        File file = new File(filename);
        String relativePath = Helper.getRelativePath(dir, file);
        if (relativePath == null) {
            return filename;
        } else {
            return relativePath;
        }
    }

    protected void generateJob(BaseMojo mojo, String type) {
        try {
            String jobName = getJobName(mojo, type);
            String filename = mojo.getWorkingDir() + FS + jobName + XML_EXTENSION;
            String relativePath = getRelativePath(mojo, filename);
            mojo.getLog().info("Generating: " + relativePath);
            Properties properties = getProperties(mojo, type, mojo.getTimestampFormat());
            String xml = resourceUtils.read(mojo.getTemplate());
            String resolvedXml = propertiesUtils.getResolvedValue(xml, properties);
            resourceUtils.write(filename, resolvedXml);
        } catch (IOException e) {
            throw new JenkinsException(e);
        }
    }

    protected boolean isKnownJobType(BaseMojo mojo, String name) {
        String jobTypes = mojo.getJobTypes();
        if (StringUtils.isBlank(jobTypes)) {
            return false;
        }
        if (NONE.equalsIgnoreCase(jobTypes)) {
            return false;
        }
        List<String> jobTypesList = Helper.splitAndTrimCSVToList(mojo.getJobTypes());
        return jobTypesList.contains(name);
    }

    protected String getJobName(BaseMojo mojo, String name) {
        if (isKnownJobType(mojo, name)) {
            StringBuilder sb = new StringBuilder();
            sb.append(mojo.getProject().getArtifactId());
            sb.append(DASH);
            sb.append(mojo.getMajorVersion());
            sb.append(DASH);
            sb.append(name);
            return sb.toString();
        } else {
            return name;
        }
    }

    protected Properties getProperties(BaseMojo mojo, String type, String timestampFormat) throws IOException {

        List<String> locations = getLocations(mojo, type);
        Properties resourceProperties = propertiesUtils.getProperties(locations);
        Properties jenkinsProperties = getJenkinsProperties(mojo, timestampFormat);
        Properties projectProperties = mojo.getProject().getProperties();
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

    protected Properties getJenkinsProperties(BaseMojo mojo, String timestampFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);
        Date now = new Date(System.currentTimeMillis());
        MavenProject project = mojo.getProject();
        Properties properties = new Properties();
        properties.setProperty("jenkins.project.scmType", mojo.getScmType());
        properties.setProperty("jenkins.project.scmUrl", mojo.getScmUrl());
        properties.setProperty("jenkins.project.majorVersion", mojo.getMajorVersion());
        properties.setProperty("jenkins.project.groupId", project.getGroupId());
        properties.setProperty("jenkins.project.artifactId", project.getArtifactId());
        properties.setProperty("jenkins.build.timestamp", sdf.format(now));
        return properties;
    }

    protected List<String> getLocations(BaseMojo mojo, String type) {
        List<String> locations = new ArrayList<String>();
        locations.add("classpath:org/kuali/jenkins/jobs/properties/common.xml");
        locations.add("classpath:org/kuali/jenkins/jobs/properties/" + mojo.getScmType() + XML_EXTENSION);
        locations.add("classpath:org/kuali/jenkins/jobs/properties/types/" + type + XML_EXTENSION);
        return locations;
    }

}

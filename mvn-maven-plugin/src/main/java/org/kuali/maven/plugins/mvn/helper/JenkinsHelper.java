/**
 * Copyright 2011-2012 The Kuali Foundation
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
package org.kuali.maven.plugins.mvn.helper;

import java.io.File;
import java.io.FileFilter;
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
import org.kuali.maven.plugins.mvn.Command;
import org.kuali.maven.plugins.mvn.MvnMojo;
import org.kuali.maven.plugins.mvn.context.GAV;
import org.kuali.maven.plugins.mvn.context.MvnException;
import org.kuali.maven.plugins.mvn.context.ProcessContext;
import org.kuali.maven.plugins.mvn.context.ProcessResult;
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

    protected List<String> getNamesList(String csvNames, List<String> names) {
        if (!Helper.isEmpty(names)) {
            return names;
        } else {
            return Helper.splitAndTrimCSVToList(csvNames);
        }
    }

    protected List<Command> createGetJobCommands(MvnMojo mojo, String cmd, List<String> jobNames) {
        List<Command> commands = new ArrayList<Command>();
        for (String jobName : jobNames) {
            Command command = createGetJobCommand(mojo, cmd, jobName);
            commands.add(command);
        }
        return commands;
    }

    protected Command createGetJobCommand(MvnMojo mojo, String cmd, String jobName) {
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
            throw new MvnException(e);
        }

    }

    protected File getJenkinsJar(MavenProject project, List<Artifact> pluginArtifacts) {
        GAV gav = getGav(project);
        File jar = getJar(gav, pluginArtifacts);
        if (jar == null) {
            throw new MvnException("Unable to locate jenkins-cli.jar");
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
        FileFilter filter = new XmlFileFilter();
        File[] files = workingDir.listFiles(filter);
        return Arrays.asList(files);
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
                throw new MvnException(e);
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

    protected void handleSuccess(MvnMojo mojo, Command command, ProcessResult result) {
        File stdout = command.getStdout();
        if (stdout != null) {
            write(stdout.getAbsolutePath(), result.getOutput());
        } else {
            List<String> lines = result.getOutputLines();
            if (result.getExitValue() == SUCCESS_CODE) {
                logInfo(lines);
            } else {
                String top = getTop(lines);
                if (top != null) {
                    logger.info(top);
                }
            }
        }
    }

    protected void write(String filename, String content) {
        try {
            resourceUtils.write(filename, content);
        } catch (IOException e) {
            throw new MvnException(e);
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

    protected String getWarnMessage(List<ProcessResult> errors) {
        StringBuilder sb = new StringBuilder();
        if (errors.size() == 1) {
            sb.append("There was 1 error.");
        } else {
            sb.append("There were " + errors.size() + " errors.");
        }
        return sb.toString();
    }

    protected String getErrorMessage(MvnMojo mojo, List<ProcessResult> errors) {
        StringBuilder sb = new StringBuilder();
        if (errors.size() == 1) {
            sb.append("There was 1 error.");
        } else {
            sb.append("There were " + errors.size() + " errors.");
        }
        for (ProcessResult result : errors) {
            sb.append(getErrorMessage(mojo, result));
        }
        return sb.toString();
    }

    protected String getErrorMessage(MvnMojo mojo, ProcessResult result) {
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
        sb.append("input: " + getInputErrorMessage(mojo, context.getInput()) + "\n");
        if (exitValue != NO_SUCH_COMMAND) {
            sb.append("details: " + result.getOutput());
        }
        return sb.toString();
    }

    protected String getInputErrorMessage(MvnMojo mojo, String input) {
        String s = Helper.toEmpty(input);
        if (StringUtils.isBlank(s)) {
            return s;
        }
        int length = input.length();
        if (length > 50) {
            long count = Counter.increment() + 1;
            File dir = mojo.getWorkingDir();
            String filename = dir + FS + "error" + FS + "input-" + count + ".log";
            File file = new File(filename);
            write(filename, input);
            return Helper.getRelativePath(mojo.getProject().getBasedir(), file);
        } else {
            return input;
        }
    }

    protected String getRelativePath(MvnMojo mojo, String filename) {
        File dir = mojo.getProject().getBasedir();
        File file = new File(filename);
        String relativePath = Helper.getRelativePath(dir, file);
        if (relativePath == null) {
            return filename;
        } else {
            return relativePath;
        }
    }

}

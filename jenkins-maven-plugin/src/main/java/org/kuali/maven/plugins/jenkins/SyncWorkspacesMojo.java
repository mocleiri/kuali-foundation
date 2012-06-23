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
package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.maven.plugins.jenkins.helper.RsyncHelper;

/**
 * Sync any workspaces that have changed from the master to a workspace server
 *
 * @goal syncworkspaces
 */
public class SyncWorkspacesMojo extends AbstractMojo {
    RsyncHelper helper = new RsyncHelper();

    /**
     * Properties file containing the jobs and buildNumbers the plugin has sync'd to the workspace server
     *
     * @parameter expression="${jenkins.buildNumberTracker}"
     *            default-value="${user.home}/.jenkins-maven-plugin/nextBuildNumber.properties"
     */
    private String buildNumberTracker;

    /**
     * If true, the Maven build will fail if <code>rsync</code> returns a non-zero exit value
     *
     * @parameter expression="${jenkins.failOnError}" default-value="true"
     */
    private boolean failOnError;

    /**
     * The base directory to scan for Jenkins workspace directories
     *
     * @parameter expression="${jenkins.basedir}" default-value="/var/lib/jenkins/jobs"
     * @required
     */
    private File basedir;

    /**
     * The destination directory <code>rsync</code> pushes files to
     *
     * @parameter expression="${jenkins.destination}" default-value="/var/lib/jenkins/workspace"
     * @required
     *
     */
    private String destination;

    /**
     * The hostname for the workspace server
     *
     * @parameter expression="${jenkins.destinationHostname}" default-value="ws.rice.kuali.org"
     * @required
     */
    private String destinationHostname;

    /**
     * The user to login to the workspace server with
     *
     * @parameter expression="${jenkins.destinationUser}" default-value="root"
     * @required
     */
    private String destinationUser;

    /**
     * The <code>rsync</code> executable
     *
     * @parameter expression="${jenkins.executable}" default-value="rsync"
     * @required
     */
    private String executable;

    protected List<Job> getJobs(List<File> dirs) {
        List<Job> jobs = new ArrayList<Job>();
        String prefix = basedir.getAbsolutePath();
        for (File dir : dirs) {
            String path = dir.getAbsolutePath();
            int pos = path.lastIndexOf("/workspace");

            String name = path.substring(prefix.length() + 1, pos);
            String src = basedir.getAbsolutePath() + "/" + name + "/workspace/";
            String dst = destinationUser + "@" + destinationHostname + ":" + destination + "/" + name;
            int buildNumber = getBuildNumber(name);

            Job job = new Job();
            job.setName(name);
            job.setBuildNumber(buildNumber);
            job.setSrc(src);
            job.setDst(dst);
            jobs.add(job);
        }
        Collections.sort(jobs);
        return jobs;
    }

    protected int getBuildNumber(String name) {
        File buildNumberFile = new File(basedir.getAbsolutePath() + "/" + name + "/nextBuildNumber");
        if (!buildNumberFile.exists()) {
            throw new IllegalStateException("Expected the file " + buildNumberFile + " to be present");
        }
        try {
            String s = FileUtils.readFileToString(buildNumberFile);
            return new Integer(s.trim());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected List<Commandline> getExecutions(List<Job> jobs) {
        List<Commandline> executions = new ArrayList<Commandline>();
        for (Job job : jobs) {
            Commandline cl = getCommandLine();
            addArg(cl, "-av");
            addArg(cl, "--delete");
            addArg(cl, job.getSrc());
            addArg(cl, job.getDst());
            executions.add(cl);
        }
        return executions;
    }

    protected void execute(List<Commandline> executions) throws MojoExecutionException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < executions.size(); i++) {
            Commandline cl = executions.get(i);
            getLog().info(StringUtils.leftPad((i + 1) + "", 3) + " : " + cl.toString());
            int exitValue = executeRsync(cl);
            validateExitValue(exitValue);
        }
        long elapsed = System.currentTimeMillis() - start;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(3);
        getLog().info("Sync time: " + nf.format(elapsed / 1000D) + "s");
    }

    @Override
    public void execute() throws MojoExecutionException {
        List<File> wsDirs = helper.getWorkspaceDirs(basedir);
        getLog().info("Sync'ing " + wsDirs.size() + " workspaces");
        List<Job> jobs = getJobs(wsDirs);
        List<Commandline> executions = getExecutions(jobs);
        execute(executions);
    }

    protected List<String> getJobNames(List<File> dirs) {
        String prefix = basedir.getAbsolutePath();
        List<String> names = new ArrayList<String>();
        for (File dir : dirs) {
            String path = dir.getAbsolutePath();
            int pos = path.lastIndexOf("/workspace");
            String name = path.substring(prefix.length() + 1, pos);
            names.add(name);
        }
        Collections.sort(names);
        return names;
    }

    protected Commandline getCommandLine() {
        Commandline cl = new Commandline();
        cl.setExecutable(executable);
        cl.setWorkingDirectory(basedir);
        return cl;
    }

    protected void addArg(Commandline cl, String arg) {
        List<String> args = new ArrayList<String>();
        args.add(arg);
        addArgs(cl, args);
    }

    protected void addArgs(Commandline cl, List<String> args) {
        if (args == null || args.size() == 0) {
            return;
        }
        for (String arg : args) {
            cl.createArg().setValue(arg);
        }
    }

    protected int executeRsync(Commandline cl) throws MojoExecutionException {
        StreamConsumer stdout = new DefaultConsumer();
        StreamConsumer stderr = new DefaultConsumer();
        try {
            return CommandLineUtils.executeCommandLine(cl, stdout, stderr);
        } catch (CommandLineException e) {
            throw new MojoExecutionException("Error executing " + executable, e);
        }
    }

    protected boolean isFail(int exitValue) {
        return failOnError && exitValue != 0;
    }

    protected void validateExitValue(int exitValue) throws MojoExecutionException {
        if (isFail(exitValue)) {
            throw new MojoExecutionException("Non-zero exit value - " + exitValue);
        }
        if (exitValue != 0) {
            getLog().info("Ignoring non-zero exit value - " + exitValue);
        }
    }

    protected void prepareFileSystem() throws MojoExecutionException {
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isFailOnError() {
        return failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public File getBasedir() {
        return basedir;
    }

    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }

    public String getDestinationHostname() {
        return destinationHostname;
    }

    public void setDestinationHostname(String destinationHostname) {
        this.destinationHostname = destinationHostname;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }

    public String getBuildNumberTracker() {
        return buildNumberTracker;
    }

    public void setBuildNumberTracker(String buildNumberTracker) {
        this.buildNumberTracker = buildNumberTracker;
    }
}
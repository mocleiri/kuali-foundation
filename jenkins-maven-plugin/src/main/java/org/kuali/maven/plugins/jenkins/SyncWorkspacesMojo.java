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
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.plugins.jenkins.helper.RsyncHelper;

/**
 * Sync any workspaces that have changed from the master to a workspace server
 *
 * @goal syncworkspaces
 */
public class SyncWorkspacesMojo extends AbstractMojo {
    RsyncHelper helper = new RsyncHelper();
    PropertiesUtils utils = new PropertiesUtils();

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
            Commandline commandLine = getCommandLine(src, dst);

            Job job = new Job();
            job.setName(name);
            job.setBuildNumber(buildNumber);
            job.setSrc(src);
            job.setDst(dst);
            job.setCommandLine(commandLine);

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

    protected Commandline getCommandLine(String src, String dst) {
        Commandline cl = getCommandLine();
        addArg(cl, "-av");
        addArg(cl, "--delete");
        addArg(cl, src);
        addArg(cl, dst);
        return cl;
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

    protected void execute(List<Job> jobs) throws MojoExecutionException {
        File buildNumberPropertiesFile = new File(buildNumberTracker);
        Properties p = getBuildNumberProperties();
        long start = System.currentTimeMillis();
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            Commandline cl = job.getCommandLine();
            getLog().info(StringUtils.leftPad((i + 1) + "", 3) + " : " + cl.toString());
            int exitValue = 0; // executeRsync(cl);
            validateExitValue(exitValue);
            p.setProperty(job.getName(), job.getBuildNumber() + "");
            try {
                store(p, buildNumberPropertiesFile);
            } catch (IOException e) {
                throw new MojoExecutionException("Error updating tracked build number properties", e);
            }
        }
        long elapsed = System.currentTimeMillis() - start;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(3);
        getLog().info("Sync time: " + nf.format(elapsed / 1000D) + "s");
    }

    protected void store(Properties p, File file) throws IOException {
        OutputStream out = null;
        try {
            out = FileUtils.openOutputStream(file);
            p.store(out, "Build Number Tracker");
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    protected void createBuildNumberProperties() throws IOException {
        File file = new File(buildNumberTracker);
        store(new Properties(), file);
    }

    protected Properties getBuildNumberProperties() {
        try {
            File file = new File(buildNumberTracker);
            if (!file.exists()) {
                getLog().info("Creating " + file.getAbsolutePath());
                createBuildNumberProperties();
            }
            return utils.getProperties(file.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected List<Job> getTrackedJobs() {
        Properties p = getBuildNumberProperties();
        List<Job> trackedJobs = new ArrayList<Job>();
        for (String name : p.stringPropertyNames()) {
            int buildNumber = new Integer(p.getProperty(name));
            Job job = new Job();
            job.setName(name);
            job.setBuildNumber(buildNumber);
            trackedJobs.add(job);
        }
        Collections.sort(trackedJobs);
        return trackedJobs;
    }

    @Override
    public void execute() throws MojoExecutionException {
        List<File> wsDirs = helper.getWorkspaceDirs(basedir);
        getLog().info("Located " + wsDirs.size() + " jobs containing workspaces");
        List<Job> trackedJobs = getTrackedJobs();
        getLog().info("Loaded build number info on " + trackedJobs.size() + " jobs that have been sync'd previously");
        List<Job> jobs = getJobs(wsDirs);
        List<Job> syncJobs = getSyncJobs(jobs, trackedJobs);
        int skipped = jobs.size() - syncJobs.size();
        getLog().info("Skipping sync call on " + skipped + " jobs that have not run since the last sync");
        execute(syncJobs);
    }

    protected List<Job> getSyncJobs(List<Job> allJobs, List<Job> trackedJobs) {
        List<Job> syncJobs = new ArrayList<Job>();
        for (Job job : allJobs) {
            boolean sync = isSync(job, trackedJobs);
            if (sync) {
                syncJobs.add(job);
            }
        }
        return syncJobs;
    }

    protected boolean isSync(Job job, List<Job> trackedJobs) {
        int currentBuildNumber = job.getBuildNumber();
        for (Job trackedJob : trackedJobs) {
            String name = trackedJob.getName();
            if (name.equals(job.getName())) {
                int previousBuildNumber = trackedJob.getBuildNumber();
                if (currentBuildNumber > previousBuildNumber) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        // Don't have any information on this job
        return true;
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
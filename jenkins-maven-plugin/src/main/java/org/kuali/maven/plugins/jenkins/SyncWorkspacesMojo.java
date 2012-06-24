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
import java.util.Arrays;
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
 * Sync any workspaces from the Jenkins master to a workspace server. Only sync workspaces where the build number has
 * incremented since the last sync call.
 *
 * @goal syncworkspaces
 */
public class SyncWorkspacesMojo extends AbstractMojo {
    RsyncHelper helper = new RsyncHelper();
    PropertiesUtils utils = new PropertiesUtils();

    /**
     * If true, rsync shows transfer statistics. Equivalent to passing rsync <code>--stats</code>
     *
     * @parameter expression="${jenkins.stats}" default-value="false"
     */
    private boolean stats;

    /**
     * If true, rsync logs files that get transferred to the workspace server. Equivalent to passing rsync
     * <code>-v</code>
     *
     * @parameter expression="${jenkins.verbose}" default-value="false"
     */
    private boolean verbose;

    /**
     * If true, sync all jobs even if the job has not been re-run since the last sync
     *
     * @parameter expression="${jenkins.forceSync}" default-value="false"
     */
    private boolean forceSync;

    /**
     * Properties file containing the jobs and buildNumbers the plugin has sync'd to the workspace server
     *
     * @parameter expression="${jenkins.trackedBuildNumbers}"
     *            default-value="${user.home}/.m2/jenkins-build-numbers.properties"
     */
    private String trackedBuildNumbers;

    /**
     * If true, the Maven build will fail if <code>rsync</code> returns a non-zero exit value
     *
     * @parameter expression="${jenkins.failOnError}" default-value="true"
     */
    private boolean failOnError;

    /**
     * The local directory where Jenkins stores job information and workspaces
     *
     * @parameter expression="${jenkins.localJobsDir}" default-value="/var/lib/jenkins/jobs"
     * @required
     */
    private File localJobsDir;

    /**
     * The directory on the workspace server containing workspaces
     *
     * @parameter expression="${jenkins.workspaceServerDir}" default-value="/var/lib/jenkins/workspace"
     * @required
     *
     */
    private String workspaceServerDir;

    /**
     * The hostname for the workspace server
     *
     * @parameter expression="${jenkins.workspaceServerHostname}" default-value="ws.rice.kuali.org"
     * @required
     */
    private String workspaceServerHostname;

    /**
     * The user to login to the workspace server with
     *
     * @parameter expression="${jenkins.workspaceServerUser}" default-value="root"
     * @required
     */
    private String workspaceServerUser;

    /**
     * The <code>rsync</code> executable
     *
     * @parameter expression="${jenkins.executable}" default-value="rsync"
     * @required
     */
    private String executable;

    /**
     * Comma separated list of jobs to ignore
     *
     * @parameter expression="${jenkins.ignoreJobs}"
     */
    private String ignoreJobs;

    protected List<Job> getJobs(List<File> dirs) {
        List<Job> jobs = new ArrayList<Job>();
        String prefix = localJobsDir.getAbsolutePath();
        for (File dir : dirs) {
            String path = dir.getAbsolutePath();
            int pos = path.lastIndexOf("/workspace");

            String name = path.substring(prefix.length() + 1, pos);
            String src = localJobsDir.getAbsolutePath() + "/" + name + "/workspace/";
            String dst = workspaceServerUser + "@" + workspaceServerHostname + ":" + workspaceServerDir + "/" + name;
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
        File buildNumberFile = new File(localJobsDir.getAbsolutePath() + "/" + name + "/nextBuildNumber");
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
        addArg(cl, "-a");
        if (verbose) {
            addArg(cl, "-v");
        }
        if (stats) {
            addArg(cl, "--stats");
        }
        addArg(cl, "--delete");
        addArg(cl, src);
        addArg(cl, dst);
        return cl;
    }

    protected void execute(List<Job> jobs) throws MojoExecutionException {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(3);
        Properties p = getBuildNumberProperties();
        long elapsed = 0;
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            Commandline cl = job.getCommandLine();
            getLog().info(StringUtils.leftPad((i + 1) + "", 3) + " : " + cl.toString());
            long s1 = System.currentTimeMillis();
            int exitValue = executeRsync(cl);
            long s2 = System.currentTimeMillis();
            elapsed += (s2 - s1);
            getLog().info("Sync time: " + nf.format((s2 - s1) / 1000D) + "s");
            validateExitValue(exitValue);
            p.setProperty(job.getName(), job.getBuildNumber() + "");
            updateTrackedBuildNumberProperties(p);
        }
        getLog().info("Total Sync time: " + nf.format(elapsed / 1000D) + "s");
    }

    protected void updateTrackedBuildNumberProperties(Properties p) {
        File file = new File(trackedBuildNumbers);
        try {
            store(p, file);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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
        File file = new File(trackedBuildNumbers);
        store(new Properties(), file);
    }

    protected Properties getBuildNumberProperties() {
        try {
            File file = new File(trackedBuildNumbers);
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
        List<File> wsDirs = helper.getWorkspaceDirs(localJobsDir);
        getLog().info("Located " + wsDirs.size() + " jobs containing workspaces");
        List<Job> trackedJobs = getTrackedJobs();
        getLog().info("Loaded build number info on " + trackedJobs.size() + " jobs that have been sync'd previously");
        List<Job> jobs = getJobs(wsDirs);
        List<Job> syncJobs = getSyncJobs(jobs, trackedJobs);
        int skipped = jobs.size() - syncJobs.size();
        getLog().info("Skipping " + skipped + " jobs that have not run since the last sync");
        execute(syncJobs);
    }

    protected List<Job> getSyncJobs(List<Job> allJobs, List<Job> trackedJobs) {
        List<String> ignoreList = Arrays.asList(PropertiesUtils.splitAndTrim(ignoreJobs, ","));
        List<Job> syncJobs = new ArrayList<Job>();
        for (Job job : allJobs) {
            boolean newBuildNumber = isNewBuildNumber(job, trackedJobs);
            boolean ignore = ignoreList.contains(job.getName());

            // Add it to the list if the force sync flag is true
            // OR if we are not ignoring this job AND it has been run again since the last sync
            boolean sync = forceSync || (newBuildNumber && !ignore);
            if (sync) {
                syncJobs.add(job);
            }
        }
        return syncJobs;
    }

    protected boolean isNewBuildNumber(Job job, List<Job> trackedJobs) {
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
        String prefix = localJobsDir.getAbsolutePath();
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
        cl.setWorkingDirectory(localJobsDir);
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

    public String getWorkspaceServerDir() {
        return workspaceServerDir;
    }

    public void setWorkspaceServerDir(String destination) {
        this.workspaceServerDir = destination;
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

    public File getLocalJobsDir() {
        return localJobsDir;
    }

    public void setLocalJobsDir(File basedir) {
        this.localJobsDir = basedir;
    }

    public String getWorkspaceServerHostname() {
        return workspaceServerHostname;
    }

    public void setWorkspaceServerHostname(String destinationHostname) {
        this.workspaceServerHostname = destinationHostname;
    }

    public String getWorkspaceServerUser() {
        return workspaceServerUser;
    }

    public void setWorkspaceServerUser(String destinationUser) {
        this.workspaceServerUser = destinationUser;
    }

    public String getTrackedBuildNumbers() {
        return trackedBuildNumbers;
    }

    public void setTrackedBuildNumbers(String buildNumberTracker) {
        this.trackedBuildNumbers = buildNumberTracker;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isStats() {
        return stats;
    }

    public void setStats(boolean stats) {
        this.stats = stats;
    }

    public String getIgnoreJobs() {
        return ignoreJobs;
    }

    public void setIgnoreJobs(String ignoreJobs) {
        this.ignoreJobs = ignoreJobs;
    }

    public boolean isForceSync() {
        return forceSync;
    }

    public void setForceSync(boolean forceSync) {
        this.forceSync = forceSync;
    }
}
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.maven.plugins.jenkins.helper.RsyncHelper;

/**
 * @goal syncworkspaces
 */
public class SyncWorkspacesMojo extends AbstractMojo {
    RsyncHelper helper = new RsyncHelper();

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

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
     * @parameter expression="${jenkins.destination}" default-value="/tmp/workspace"
     * @required
     *
     */
    private String destination;

    /**
     * The <code>rsync</code> executable
     *
     * @parameter expression="${jenkins.executable}" default-value="rsync"
     * @required
     */
    private String executable;

    @Override
    public void execute() throws MojoExecutionException {
        List<File> wsDirs = helper.getWorkspaceDirs(basedir);
        getLog().info("Workspace Count: " + wsDirs.size());
        List<String> names = getJobNames(wsDirs);
        List<Commandline> executions = new ArrayList<Commandline>();
        for (String name : names) {
            String src = basedir.getAbsolutePath() + "/" + name + "/workspace/";
            String dst = destination + "/" + name;
            Commandline cl = getCommandLine();
            addArg(cl, "-av");
            addArg(cl, "--stats");
            addArg(cl, "--delete");
            addArg(cl, src);
            addArg(cl, dst);
            executions.add(cl);
        }
        for (int i = 0; i < executions.size(); i++) {
            Commandline cl = executions.get(i);
            getLog().info(StringUtils.leftPad(i + "", 3) + " Executing " + cl.toString());
            long start = System.currentTimeMillis();
            int exitValue = 0;// executeRsync();
            long elapsed = System.currentTimeMillis() - start;
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
            nf.setMinimumFractionDigits(3);
            getLog().info("Sync time: " + nf.format(elapsed / 1000D) + "s");
            validateExitValue(exitValue);
        }
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

    protected int executeRsync() throws MojoExecutionException {
        StreamConsumer stdout = new DefaultConsumer();
        StreamConsumer stderr = new DefaultConsumer();
        Commandline cl = getCommandLine();
        getLog().info(cl.toString());
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

    public MavenProject getProject() {
        return project;
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
}
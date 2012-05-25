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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.maven.plugins.jenkins.helper.DirectoryFileFilter;
import org.kuali.maven.plugins.jenkins.helper.RsyncHelper;

/**
 * Synchronize a Jenkins workspace to another location using <code>rsync</code>. To use this mojo, the
 * <code>rsync</code> utility must be installed and in your path. If the mojo completes successfully the
 * <code>destination</code> directory will contain the exact same set of files as the <code>source</code> directory. For
 * the purposes of this mojo, "exactly the same" means, all the files on both sides have the same name, the same size,
 * and the same last modified date.
 *
 * @goal syncworkspace
 * @aggregator
 */
public class SyncWorkspaceMojo extends AbstractMojo {
    RsyncHelper helper = new RsyncHelper();

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * If true, the <code>excludeTargetPattern</code> will be used to exclude Maven build directories from the workspace
     * sync
     *
     * @parameter expression="${jenkins.excludeTarget}" default-value="true"
     */
    private boolean excludeTarget;

    /**
     * The pattern to use when matching Maven build directories. The exclude logic omits any directories that end with
     * this value. eg <code>/foo/target</code> will be excluded but <code>/footarget</code> will not
     *
     * @parameter expression="${jenkins.excludeTargetPattern}" default-value="target"
     */
    private String excludeTargetPattern;

    /**
     * If true, the Maven build will fail if <code>rsync</code> returns a non-zero exit value
     *
     * @parameter expression="${jenkins.failOnError}" default-value="true"
     */
    private boolean failOnError;

    /**
     * Comma separated list of integers that the plugin will silently ignore if they are returned as the exit value for
     * <code>rsync</code>
     *
     * @parameter expression="${jenkins.ignoreCodes}"
     */
    private String ignoreCodes;

    /**
     * If true, <code>rsync</code> emits verbose logging. Equivalent to the <code>-vv</code> command line switch
     *
     * @parameter expression="${jenkins.verbose}" default-value="false"
     */
    private boolean verbose;

    /**
     * The file where the list of directories to exclude is aggregated. The full path to this file is passed to
     * <code>rsync</code> with <code>--exclude-from</code>
     *
     * @parameter expression="${jenkins.excludesFile}" default-value="${project.build.directory}/jenkins/rsync-excludes"
     */
    private File excludesFile;

    /**
     * The working directory for the plugin
     *
     * @parameter expression="${jenkins.workingDir}" default-value="${project.build.directory}/jenkins"
     */
    private File workingDir;

    /**
     * The base directory to scan for Maven build directories
     *
     * @parameter expression="${jenkins.basedir}" default-value="${project.basedir}"
     */
    private File basedir;

    /**
     * The source directory <code>rsync</code> pulls files from. For <code>rsync</code> the trailing slash is
     * significant. A trailing slash on the <code>source</code> directory instructs <code>rsync</code> to place files
     * directly into the <code>destination</code> directory instead of creating a sub-directory under the
     * <code>destination</code> directory.
     *
     * @parameter expression="${jenkins.source}" default-value="${project.basedir}/"
     * @required
     */
    private String source;

    /**
     * The destination directory <code>rsync</code> pushes files to
     *
     * @parameter expression="${jenkins.destination}"
     * @required
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
        getLog().info("Src - " + source);
        getLog().info("Dst - " + destination);
        getLog().info("Exclude - " + excludeTarget);
        if (excludeTarget) {
            getLog().info("Base Dir - " + basedir.getAbsolutePath());
            getLog().info("Exclude Pattern - " + excludeTargetPattern);
            getLog().info("Excludes File - " + excludesFile);
        }
        prepareFileSystem();
        long start = System.currentTimeMillis();
        int exitValue = executeRsync();
        long elapsed = System.currentTimeMillis() - start;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(3);
        getLog().info("Sync time: " + nf.format(elapsed / 1000D) + "s");
        validateExitValue(exitValue);
    }

    protected List<String> getArgs() {
        List<String> args = new ArrayList<String>();
        args.add("-az");
        if (verbose) {
            args.add("-vv");
        }
        args.add("--stats");
        args.add("--delete");
        if (excludeTarget) {
            args.add("--delete-excluded");
            args.add("--exclude-from");
            args.add(excludesFile.getAbsolutePath());
        }
        args.add(source);
        args.add(destination);
        return args;
    }

    protected Commandline getCommandLine() {
        Commandline cl = new Commandline();
        cl.setExecutable(executable);
        cl.setWorkingDirectory(basedir);
        addArgs(cl, getArgs());
        return cl;
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

    protected List<Integer> getAllowedCodes() {
        List<Integer> codes = new ArrayList<Integer>();
        codes.add(0);
        if (!StringUtils.isBlank(ignoreCodes)) {
            String[] tokens = StringUtils.split(ignoreCodes, ",");
            for (String token : tokens) {
                codes.add(new Integer(token.trim()));
            }
        }
        return codes;
    }

    protected boolean isFail(int exitValue) {
        List<Integer> codes = getAllowedCodes();
        for (Integer code : codes) {
            if (exitValue == code.intValue()) {
                return false;
            }
        }
        return failOnError;
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
        try {
            FileUtils.touch(excludesFile);
            if (excludeTarget) {
                DirectoryFileFilter dff = new DirectoryFileFilter();
                List<File> excludeDirs = helper.getMatchingDirs(basedir, basedir, excludeTargetPattern, dff);
                List<String> excludes = helper.getExcludesList(basedir, excludeDirs);
                int size = excludes.size();
                if (size == 1) {
                    getLog().info("Excluding " + excludes.size() + " directory");
                } else {
                    getLog().info("Excluding " + excludes.size() + " directories");
                }
                FileUtils.writeLines(excludesFile, excludes);
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Error preparing file system", e);
        }
    }

    public MavenProject getProject() {
        return project;
    }

    public boolean isExcludeTarget() {
        return excludeTarget;
    }

    public void setExcludeTarget(boolean excludeTarget) {
        this.excludeTarget = excludeTarget;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
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

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public File getExcludesFile() {
        return excludesFile;
    }

    public void setExcludesFile(File excludesFile) {
        this.excludesFile = excludesFile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public String getExcludeTargetPattern() {
        return excludeTargetPattern;
    }

    public void setExcludeTargetPattern(String excludeTargetPattern) {
        this.excludeTargetPattern = excludeTargetPattern;
    }

    public File getBasedir() {
        return basedir;
    }

    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }

    public String getIgnoreCodes() {
        return ignoreCodes;
    }

    public void setIgnoreCodes(String ignoreCodes) {
        this.ignoreCodes = ignoreCodes;
    }

}
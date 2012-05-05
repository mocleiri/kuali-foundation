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
package org.kuali.maven.plugins.mvn;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.Arg;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.maven.common.ResourceUtils;

/**
 * @goal mvn
 */
public class MvnMojo extends AbstractMojo {

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * The working directory for the plugin
     *
     * @parameter expression="${mvn.workingDir}" default-value="${project.build.directory}/mvn"
     * @required
     */
    private File workingDir;

    /**
     * The Maven executable
     *
     * @parameter expression="${mvn.executable}" default-value="mvn"
     * @required
     */
    private String executable;

    /**
     * The pom to supply to the mvn invocation. Supports any url Spring resource loading can understand <br>
     *
     * eg classpath:pom.xml
     *
     * @parameter expression="${mvn.pom}"
     */
    private String pom;

    /**
     * Arguments to supply to the mvn invocation
     *
     * @parameter
     * @required
     */
    private List<String> args;

    /**
     * If true, the System environment is passed to the mvn invocation
     *
     * @parameter expression="${mvn.addSystemEnvironment}" default-value="false"
     */
    private boolean addSystemEnvironment;

    /**
     * If true, the Maven build will fail if the mvn invocation returns a non-zero exit value, otherwise the Maven build
     * will continue
     *
     * @parameter expression="${mvn.failOnError}" default-value="true"
     * @required
     */
    private boolean failOnError;

    @Override
    public void execute() throws MojoExecutionException {
        int exitValue = -1;
        try {
            prepareFileSystem();
            StreamConsumer stdout = new DefaultConsumer();
            StreamConsumer stderr = new DefaultConsumer();
            Commandline cl = getCommandLine();
            getLog().info("Invoking " + cl.toString());
            exitValue = CommandLineUtils.executeCommandLine(cl, stdout, stderr);
        } catch (Exception e) {
            throw new MojoExecutionException("Error invoking mvn", e);
        }
        validateExitValue(exitValue);
    }

    protected void prepareFileSystem() throws IOException {
        ResourceUtils ru = new ResourceUtils();
        FileUtils.forceMkdir(workingDir);
        if (!StringUtils.isBlank(pom)) {
            String filename = workingDir + File.separator + "pom.xml";
            ru.copy(pom, filename);
        }
    }

    protected Commandline getCommandLine() throws Exception {
        Commandline cl = new Commandline();
        cl.setExecutable(executable);
        cl.setWorkingDirectory(workingDir);
        if (addSystemEnvironment) {
            cl.addSystemEnvironment();
        }
        addArgs(cl, args);
        return cl;
    }

    protected void addArgs(Commandline cl, List<String> args) {
        if (args == null || args.size() == 0) {
            return;
        }
        for (String arg : args) {
            Arg newArg = cl.createArg();
            newArg.setValue(arg);
        }
    }

    protected boolean isFail(int exitValue) {
        return exitValue != 0 && failOnError;
    }

    protected void validateExitValue(int exitValue) throws MojoExecutionException {
        if (isFail(exitValue)) {
            throw new MojoExecutionException("Non-zero exit value");
        }
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public boolean isFailOnError() {
        return failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public MavenProject getProject() {
        return project;
    }

    public String getPom() {
        return pom;
    }

    public void setPom(String pom) {
        this.pom = pom;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public boolean isAddSystemEnvironment() {
        return addSystemEnvironment;
    }

    public void setAddSystemEnvironment(boolean addSystemEnvironment) {
        this.addSystemEnvironment = addSystemEnvironment;
    }

}
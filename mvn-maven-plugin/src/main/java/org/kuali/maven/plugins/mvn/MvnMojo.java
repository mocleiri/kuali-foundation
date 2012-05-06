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
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.MvnContext;
import org.kuali.maven.common.MvnExecutor;

/**
 * Invoke mvn from Maven.
 *
 * @goal mvn
 */
public class MvnMojo extends AbstractMojo implements MvnContext {
    MvnExecutor executor = new MvnExecutor();

    @Override
    public Properties getProjectProperties() {
        return project.getProperties();
    }

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * The working directory where the plugin makes a local copy of the pom (if a pom is supplied)
     *
     * @parameter expression="${mvn.workingDir}" default-value="${project.build.directory}/mvn"
     * @required
     */
    private File workingDir;

    /**
     * The base directory for the new mvn invocation.
     *
     * @parameter expression="${mvn.basedir}" default-value="${project.build.directory}/mvn"
     *
     * @required
     */
    private File basedir;

    /**
     * The Maven executable. By default, the executable to use is located via the ${maven.home} system property. This
     * causes the new mvn invocation to mirror the one that is currently executing (same version, etc). You can override
     * this behavior by supplying your own executable
     *
     * @parameter expression="${mvn.executable}"
     */
    private String executable;

    /**
     * The pom to supply to the new mvn invocation. This can be a file or any url Spring resource loading can understand
     *
     * eg classpath:pom.xml
     *
     * @parameter expression="${mvn.pom}"
     */
    private String pom;

    /**
     * If true, the pom will be filtered using properties from the current project before being invoked
     *
     * @parameter expression="${mvn.filterPom}" default-value="false"
     */
    private boolean filterPom;

    /**
     * Arguments to supply to the new mvn invocation eg "clean install"
     *
     * @parameter
     * @required
     */
    private List<String> args;

    /**
     * List of properties from the current project to propagate to the new mvn invocation
     *
     * @parameter
     */
    private List<String> properties;

    /**
     * If true, the current environment is passed to the new mvn invocation
     *
     * @parameter expression="${mvn.addEnvironment}" default-value="false"
     */
    private boolean addEnvironment;

    /**
     * If true, the environment variable MAVEN_OPTS (if set) is passed to the new mvn invocation
     *
     * @parameter expression="${mvn.addMavenOpts}" default-value="true"
     */
    private boolean addMavenOpts;

    /**
     * If true, the original Maven build will fail if the new mvn invocation returns a non-zero exit value, otherwise
     * the Maven build will continue
     *
     * @parameter expression="${mvn.failOnError}" default-value="true"
     * @required
     */
    private boolean failOnError;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            executor.execute(this);
        } catch (Exception e) {
            throw new MojoExecutionException("Error invoking mvn", e);
        }
    }

    @Override
    public File getWorkingDir() {
        return workingDir;
    }

    @Override
    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    @Override
    public boolean isFailOnError() {
        return failOnError;
    }

    @Override
    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public MavenProject getProject() {
        return project;
    }

    @Override
    public String getPom() {
        return pom;
    }

    @Override
    public void setPom(String pom) {
        this.pom = pom;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public String getExecutable() {
        return executable;
    }

    @Override
    public void setExecutable(String executable) {
        this.executable = executable;
    }

    @Override
    public boolean isAddEnvironment() {
        return addEnvironment;
    }

    @Override
    public void setAddEnvironment(boolean addSystemEnvironment) {
        this.addEnvironment = addSystemEnvironment;
    }

    @Override
    public List<String> getProperties() {
        return properties;
    }

    @Override
    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean isFilterPom() {
        return filterPom;
    }

    @Override
    public void setFilterPom(boolean filter) {
        this.filterPom = filter;
    }

    @Override
    public boolean isAddMavenOpts() {
        return addMavenOpts;
    }

    @Override
    public void setAddMavenOpts(boolean addMavenOpts) {
        this.addMavenOpts = addMavenOpts;
    }

    @Override
    public File getBasedir() {
        return basedir;
    }

    @Override
    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }

}
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

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
     */
    private List<String> args;

    /**
     * If true, the Maven build will fail if the mvn invocation returns a non-zero exit value, otherwise the Maven build
     * will continue
     *
     * @parameter expression="${mvn.failOnError}" default-value="true"
     * @required
     */
    private boolean failOnError;

    @Override
    public void execute() {
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

}
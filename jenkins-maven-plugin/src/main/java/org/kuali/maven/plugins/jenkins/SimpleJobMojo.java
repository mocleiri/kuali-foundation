/**
 * Copyright 2004-2011 The Kuali Foundation
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

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Mojo for executing 'simple' Jenkins CLI commands related to a single Jenkins job. 'Simple' in this context means the
 * CLI command requires no input and produces no output.
 */
public abstract class SimpleJobMojo extends CliMojo {

    protected abstract String getJobCmd();

    /**
     * The type of job. Maven GAV info is combined with 'type' to derive the complete job name eg
     * 'jenkins-maven-plugin-1.0-publish'
     *
     * @parameter expression="${jenkins.type}" default-value="publish"
     * @required
     */
    private String type;

    /**
     * The name of the job. If name is provided, 'type' is ignored
     *
     * @parameter expression="${jenkins.name}"
     */
    private String name;

    public String[] getArgs(String jobName) {
        return new String[] { getJobCmd(), jobName };
    }

    @Override
    public void execute() throws MojoExecutionException {
        helper.executeSimpleJobMojo(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
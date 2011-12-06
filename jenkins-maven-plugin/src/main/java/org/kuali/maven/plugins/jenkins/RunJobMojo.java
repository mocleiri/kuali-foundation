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

import java.util.Arrays;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.plugins.jenkins.context.MavenContext;
import org.kuali.maven.plugins.jenkins.helper.Helper;

/**
 * Connect to a Jenkins server and kick off a job
 *
 * @goal runjob
 * @requiresDependencyResolution test
 */
public class RunJobMojo extends CliMojo {

    /**
     * The Jenkins CLI command for running a job
     *
     * @parameter expression="${jenkins.runJobCmd}" default-value="build"
     * @required
     */
    private String runJobCmd;

    /**
     * The type of job. Maven GAV info is combined with 'type' to derive the complete job name eg
     * 'jenkins-maven-plugin-1.0-publish'
     *
     * @parameter expression="${jenkins.type}" default-value="publish"
     * @required
     */
    private String type;

    /**
     * The explicit name of a job. If name is provided, 'type' is ignored
     *
     * @parameter expression="${jenkins.name}"
     */
    private String name;

    @Override
    public void execute() throws MojoExecutionException {
        MavenContext context = helper.getMavenContext(this);
        String jobName = helper.getJobName(context, name, type);
        String[] args = { runJobCmd, jobName };
        Command command = new Command();
        command.setArgs(Arrays.asList(args));
        super.setCommands(Helper.toList(command));
        helper.executeCli(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRunJobCmd() {
        return runJobCmd;
    }

    public void setRunJobCmd(String cmd) {
        this.runJobCmd = cmd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
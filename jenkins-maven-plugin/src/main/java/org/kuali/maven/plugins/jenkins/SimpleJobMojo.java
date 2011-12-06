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
 *
 */
public abstract class SimpleJobMojo extends CliMojo {

    protected abstract String getJobCmd();

    /**
     * The type of job to delete. Maven GAV info is combined with 'type' to derive the complete job name eg
     * 'jenkins-maven-plugin-1.0-publish'
     *
     * @parameter expression="${jenkins.type}" default-value="publish"
     * @required
     */
    private String type;

    /**
     * The name of the job to delete. If name is provided, 'type' is ignored
     *
     * @parameter expression="${jenkins.name}"
     */
    private String name;

    @Override
    public void execute() throws MojoExecutionException {
        MavenContext context = helper.getMavenContext(this);
        String jobName = helper.getJobName(context, name, type);
        String[] args = { getJobCmd(), jobName };
        Command command = new Command();
        command.setArgs(Arrays.asList(args));
        super.setCommands(Helper.toList(command));
        helper.executeCli(this);
    }
}
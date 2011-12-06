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
 * Connect to a Jenkins server and create a Jenkins job
 *
 * @goal createjobs
 * @requiresDependencyResolution test
 */
public class CreateJobsMojo extends AbstractJobConfigMojo {

    /**
     * The Jenkins CLI command for creating a job
     *
     * @parameter expression="${jenkins.createJobCmd}" default-value="create-job"
     * @required
     */
    private String createJobCmd;

    @Override
    public void execute() throws MojoExecutionException {
        helper.pushJobs(this, createJobCmd);
    }

    public String getCreateJobCmd() {
        return createJobCmd;
    }

    public void setCreateJobCmd(String createJobCmd) {
        this.createJobCmd = createJobCmd;
    }

}
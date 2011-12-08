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


/**
 * Connect to a Jenkins server and update one or more Jenkins job
 *
 * @goal updatejobs
 * @requiresDependencyResolution test
 */
public class UpdateJobsMojo extends BaseMojo {

    /**
     * The Jenkins CLI command for creating a job
     *
     * @parameter expression="${jenkins.updateJobCmd}" default-value="update-job"
     * @required
     */
    private String updateJobCmd;

    @Override
    public void execute() {
        helper.pushJobs(this, updateJobCmd);
    }

    public String getUpdateJobCmd() {
        return updateJobCmd;
    }

    public void setUpdateJobCmd(String updateJobCmd) {
        this.updateJobCmd = updateJobCmd;
    }

}
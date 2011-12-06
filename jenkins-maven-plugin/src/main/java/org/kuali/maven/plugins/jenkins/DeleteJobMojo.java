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
 * Connect to a Jenkins server and delete a job
 *
 * @goal deletejob
 * @requiresDependencyResolution test
 */
public class DeleteJobMojo extends SimpleJobMojo {
    /**
     * The Jenkins CLI command for running a job
     *
     * @parameter expression="${jenkins.deleteJobCmd}" default-value="delete-job"
     * @required
     */
    private String deleteJobCmd;

    @Override
    protected String getJobCmd() {
        return this.deleteJobCmd;
    }

    public String getDeleteJobCmd() {
        return deleteJobCmd;
    }

    public void setDeleteJobCmd(String deleteJobCmd) {
        this.deleteJobCmd = deleteJobCmd;
    }

}
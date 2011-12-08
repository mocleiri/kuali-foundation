/**
 * Copyright 2011-2011 The Kuali Foundation
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
 * Connect to a Jenkins server and retrieve an XML document describing the job configuration
 *
 * @goal getjob
 * @requiresDependencyResolution test
 */
public class GetJobMojo extends BaseMojo {

    /**
     * The command issued to Jenkins CLI
     *
     * @parameter expression="${jenkins.getJobCmd}" default-value="get-job"
     * @required
     */
    private String getJobCmd;

    /**
     * The name of the job to retrieve.
     *
     * @parameter expression="${jenkins.name}"
     */
    private String name;

    @Override
    public void execute() {
        helper.execute(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGetJobCmd() {
        return getJobCmd;
    }

    public void setGetJobCmd(String getJobCmd) {
        this.getJobCmd = getJobCmd;
    }

}
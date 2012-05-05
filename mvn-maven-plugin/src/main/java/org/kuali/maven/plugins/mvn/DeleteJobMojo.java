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

/**
 * Connect to a Jenkins server and delete a job
 *
 * @goal deletejob
 * @threadSafe
 * @requiresDependencyResolution test
 */
public class DeleteJobMojo extends SimpleJobMojo {

    /**
     * The Jenkins CLI command for deleting a job
     *
     * @parameter expression="${jenkins.cmd}" default-value="delete-job"
     * @required
     */
    private String cmd;

    /**
     * Comma separated list of Jenkins CLI exit values to ignore. Jenkins CLI returns "1" when you attempt to delete a
     * job that does not exist. By default, that error code is ignored.
     *
     * @parameter expression="${jenkins.ignoreCodes}" default-value="1"
     * @required
     */
    private String ignoreCodes;

    @Override
    protected void executeMojo() {
        helper.execute(this);
    }

    @Override
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getIgnoreCodes() {
        return ignoreCodes;
    }

    public void setIgnoreCodes(String ignoreCodes) {
        this.ignoreCodes = ignoreCodes;
    }

}
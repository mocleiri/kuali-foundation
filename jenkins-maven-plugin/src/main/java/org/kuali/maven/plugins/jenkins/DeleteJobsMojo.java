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

import java.util.List;

/**
 * Connect to a Jenkins server and delete one or more jobs
 *
 * @goal deletejobs
 * @threadSafe
 * @requiresDependencyResolution test
 */
public class DeleteJobsMojo extends BaseMojo {

    /**
     * The command issued to Jenkins CLI
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

    /**
     * Comma delimited list of job names to retrieve
     *
     * @parameter expression="${jenkins.names}" default-value="publish,unit,license,release"
     */
    private String names;

    /**
     * List of job names to retrieve. If 'nameList' is provided, 'names' is ignored
     *
     * @parameter
     */
    private List<String> nameList;

    @Override
    protected void executeMojo() {
        helper.execute(this);
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public String getIgnoreCodes() {
        return ignoreCodes;
    }

    public void setIgnoreCodes(String ignoreCodes) {
        this.ignoreCodes = ignoreCodes;
    }

}
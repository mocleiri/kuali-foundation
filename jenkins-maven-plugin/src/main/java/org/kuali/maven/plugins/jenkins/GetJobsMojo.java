/**
 * Copyright 2011-2013 The Kuali Foundation
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
 * Connect to a Jenkins server and retrieve XML documents describing jobs
 *
 * @goal getjobs
 * @threadSafe
 * @requiresDependencyResolution test
 */
public class GetJobsMojo extends BaseMojo {

    /**
     * The Jenkins CLI command for getting a job
     *
     * @parameter expression="${jenkins.cmd}" default-value="get-job"
     * @required
     */
    private String cmd;

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

}
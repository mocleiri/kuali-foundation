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
 * Connect to a Jenkins server and retrieve XML documents describing one or more job configurations
 *
 * @goal getjobs
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
     * The list of job names to get
     *
     * @parameter
     */
    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

}
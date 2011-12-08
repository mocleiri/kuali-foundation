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

import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Connect to a Jenkins server and kick off a job
 *
 * @goal runjob
 * @requiresDependencyResolution test
 */
public class RunJobMojo extends SimpleJobMojo {

    /**
     * The Jenkins CLI command for running a job
     *
     * @parameter expression="${jenkins.runJobCmd}" default-value="build"
     * @required
     */
    private String runJobCmd;

    /**
     * If true, wait for the job to complete before continuing.
     *
     * @parameter expression="${jenkins.wait}" default-value="false"
     * @required
     */
    private boolean wait;

    /**
     * If true, check for changes before running the job. If nothing has changed, do not run the job.
     *
     * @parameter expression="${jenkins.skipIfNoChanges}" default-value="false"
     * @required
     */
    private boolean skipIfNoChanges;

    /**
     * Comma delimited list of key=value pairs to pass to the Jenkins job as build parameters
     *
     * @parameter expression="${jenkins.params}"
     */
    private String params;

    /**
     * key=value pairs to pass to the Jenkins job as build parameters
     *
     * @parameter
     */
    private Map<String, String> paramMap;

    @Override
    public String getCmd() {
        return getRunJobCmd();
    }

    @Override
    public void execute() throws MojoExecutionException {
        helper.execute(this);
    }

    public String getRunJobCmd() {
        return runJobCmd;
    }

    public void setRunJobCmd(String cmd) {
        this.runJobCmd = cmd;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public boolean isSkipIfNoChanges() {
        return skipIfNoChanges;
    }

    public void setSkipIfNoChanges(boolean skipIfNoChanges) {
        this.skipIfNoChanges = skipIfNoChanges;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
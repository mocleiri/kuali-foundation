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

import java.util.ArrayList;
import java.util.List;

import org.kuali.maven.plugins.jenkins.helper.Helper;

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
     * It true, wait for the job to complete before continuing.
     *
     * @parameter expression="${jenkins.wait}" default-value="false"
     * @required
     */
    private boolean wait;

    /**
     * It true, check for changes before running the job. If nothing has changed, do not run the job.
     *
     * @parameter expression="${jenkins.skipIfNoChanges}" default-value="false"
     * @required
     */
    private boolean skipIfNoChanges;

    @Override
    protected String[] getArgs(String jobName) {
        List<String> args = new ArrayList<String>();
        args.add(jobName);
        if (skipIfNoChanges) {
            args.add("-c");
        }
        if (wait) {
            args.add("-s");
        }
        return Helper.toArray(args);
    }

    @Override
    protected String getJobCmd() {
        return getRunJobCmd();
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

}
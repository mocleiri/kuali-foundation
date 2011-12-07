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

import java.util.Map;

public class RunJobCommand {
    String command;
    String jobName;
    boolean isWait;
    boolean isSkipIfNoChanges;
    Map<String, String> params;

    public boolean isWait() {
        return isWait;
    }

    public void setWait(boolean isWait) {
        this.isWait = isWait;
    }

    public boolean isSkipIfNoChanges() {
        return isSkipIfNoChanges;
    }

    public void setSkipIfNoChanges(boolean isSkipIfNoChanges) {
        this.isSkipIfNoChanges = isSkipIfNoChanges;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}

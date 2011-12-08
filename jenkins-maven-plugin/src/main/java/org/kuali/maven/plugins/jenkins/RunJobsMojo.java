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

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Connect to a Jenkins server and kick off one or more jobs
 *
 * @goal runjobs
 * @requiresDependencyResolution test
 */
public class RunJobsMojo extends BaseMojo {

    /**
     * @parameter
     */
    private List<RunJobCommand> commands;;

    @Override
    public void execute() throws MojoExecutionException {
        helper.execute(this);
    }

    public List<RunJobCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<RunJobCommand> commands) {
        this.commands = commands;
    }

}
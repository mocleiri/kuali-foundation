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

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Connect to a Jenkins server and execute one or more Jenkins CLI commands
 *
 * @goal cli
 * @requiresDependencyResolution test
 */
public class CliMojo extends BaseMojo {

    /**
     * The command to issue to Jenkins CLI eg "help", "version", "who-ami-i" etc
     *
     * @parameter expression="${jenkins.cmd}" default-value="version"
     * @required
     */
    private String cmd;

    /**
     * List of commands to issue to Jenkins CLI eg "help", "version", "who-ami-i" etc
     *
     * If 'cmds' is provided 'cmd' is ignored
     *
     * @parameter expression="${jenkins.cmds}"
     */
    private List<String> cmds;

    @Override
    public void execute() throws MojoExecutionException {
        helper.executeCli(this);
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public List<String> getCmds() {
        return cmds;
    }

    public void setCmds(List<String> cmds) {
        this.cmds = cmds;
    }

}
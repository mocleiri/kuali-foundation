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
import org.kuali.maven.plugins.jenkins.context.Command;

/**
 * Connect to a Jenkins server and execute one or more Jenkins CLI commands. Commands executed by this mojo cannot
 * require input from stdin and any output (normal or err) is logged to stdout
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
     * Anything supplied here is provided as input to the standard input stream of Jenkins CLI
     *
     * @parameter expression="${jenkins.input}"
     */
    private String input;

    /**
     * This can be a file on the file system, a classpath resource using Spring's "classpath:" notation, or any other
     * url Spring 3.0 resource loading can understand. If supplied, the content of the url is provided as input to the
     * standard input stream of Jenkins CLI. If 'inputUrl' is supplied 'input' is ignored.
     *
     * @parameter expression="${jenkins.inputUrl}"
     */
    private String inputUrl;

    /**
     * List of commands to issue to Jenkins CLI eg "help", "version", "who-ami-i" etc
     *
     * If 'cmds' is provided 'cmd' is ignored
     *
     * @parameter
     */
    private List<Command> cmds;

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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInputUrl() {
        return inputUrl;
    }

    public void setInputUrl(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    public List<Command> getCmds() {
        return cmds;
    }

    public void setCmds(List<Command> cmds) {
        this.cmds = cmds;
    }

}
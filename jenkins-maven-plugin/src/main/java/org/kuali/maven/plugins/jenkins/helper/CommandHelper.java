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
package org.kuali.maven.plugins.jenkins.helper;

import java.util.Arrays;
import java.util.List;

import org.kuali.maven.plugins.jenkins.CliMojo;
import org.kuali.maven.plugins.jenkins.Command;
import org.kuali.maven.plugins.jenkins.SimpleJobCommand;

public class CommandHelper {
    public static final String SPACE = " ";

    public List<Command> getCmds(CliMojo mojo) {
        if (mojo.getCommands() != null) {
            return mojo.getCommands();
        } else {
            Command command = getCommand(mojo.getCmd(), mojo.getStdin(), mojo.getStdinUrl());
            return Helper.toList(command);
        }
    }

    public Command getCommand(SimpleJobCommand sjc) {
        List<String> args = Helper.toList(sjc.getJenkinsCommand());
        args.add(sjc.getJobName());
        Command command = new Command();
        command.setArgs(args);
        return command;
    }

    protected Command getCommand(String cmd, String stdin, String stdinUrl) {
        String[] args = Helper.splitAndTrim(cmd, SPACE);
        Command command = new Command();
        command.setArgs(Arrays.asList(args));
        command.setStdin(stdin);
        command.setStdin(stdinUrl);
        return command;
    }
}

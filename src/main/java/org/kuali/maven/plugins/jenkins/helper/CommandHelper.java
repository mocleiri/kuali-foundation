/**
 * Copyright 2011-2012 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.maven.plugins.jenkins.CliMojo;
import org.kuali.maven.plugins.jenkins.Command;
import org.kuali.maven.plugins.jenkins.RunJobCommand;
import org.kuali.maven.plugins.jenkins.SimpleJobCommand;

public class CommandHelper {
    public static final String SPACE = " ";
    public static final String SKIP_IF_NO_CHANGES_ARG = "-c";
    public static final String WAIT_FOR_JOB_TO_FINISH_ARG = "-s";
    public static final String PARAMS_ARG = "-p";

    public List<Command> getCommands(CliMojo mojo) {
        if (!Helper.isEmpty(mojo.getCommands())) {
            return mojo.getCommands();
        } else {
            Command command = getCommand(mojo);
            return Helper.toList(command);
        }
    }

    protected Command getCommand(CliMojo mojo) {
        String[] args = Helper.splitAndTrim(mojo.getCmd(), SPACE);
        Command command = new Command();
        command.setArgs(Arrays.asList(args));
        command.setStdin(mojo.getStdin());
        command.setStdin(mojo.getStdinUrl());
        return command;
    }

    public List<String> toArgs(RunJobCommand command) {
        List<String> args = new ArrayList<String>();
        args.addAll(toArgs((SimpleJobCommand) command));
        if (command.isSkipIfNoChanges()) {
            args.add(SKIP_IF_NO_CHANGES_ARG);
        }
        if (command.isWait()) {
            args.add(WAIT_FOR_JOB_TO_FINISH_ARG);
        }
        if (!Helper.isEmpty(command.getParams())) {
            args.add(PARAMS_ARG);
            args.addAll(Helper.toKeyValueList(command.getParams()));
        }
        return args;
    }

    public List<String> toArgs(SimpleJobCommand command) {
        String jenkinsCommand = command.getCommand();
        String jobName = command.getName();
        return Helper.toList(jenkinsCommand, jobName);
    }

}

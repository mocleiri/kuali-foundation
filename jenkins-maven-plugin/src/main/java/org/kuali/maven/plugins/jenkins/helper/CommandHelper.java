package org.kuali.maven.plugins.jenkins.helper;

import java.util.Arrays;
import java.util.List;

import org.kuali.maven.plugins.jenkins.CliMojo;
import org.kuali.maven.plugins.jenkins.Command;

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

    protected Command getCommand(String cmd, String stdin, String stdinUrl) {
        String[] args = Helper.splitAndTrim(cmd, SPACE);
        Command command = new Command();
        command.setArgs(Arrays.asList(args));
        command.setStdin(stdin);
        command.setStdin(stdinUrl);
        return command;
    }
}

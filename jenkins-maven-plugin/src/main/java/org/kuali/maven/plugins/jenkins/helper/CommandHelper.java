package org.kuali.maven.plugins.jenkins.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.kuali.maven.plugins.jenkins.CliMojo;
import org.kuali.maven.plugins.jenkins.context.Command;

public class CommandHelper {
    public static final String COMMAND = "command";
    public static final String ARGS = "args";
    public static final String ARG = "arg";
    public static final String STDIN = "stdin";
    public static final String STDIN_URL = "stdinUrl";
    public static final String SPACE = " ";

    public List<Command> getCmds(CliMojo mojo) {
        if (mojo.getCommands() != null) {
            return getCommands(mojo.getCommands());
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

    public List<Command> getCommands(PlexusConfiguration plexusCommands) {
        List<Command> commands = new ArrayList<Command>();
        PlexusConfiguration[] commandChildren = plexusCommands.getChildren(COMMAND);
        for (PlexusConfiguration plexusCommand : commandChildren) {
            Command command = getCommand(plexusCommand);
            commands.add(command);
        }
        return commands;
    }

    protected Command getCommand(PlexusConfiguration plexusCommand) {
        List<String> args = getArgs(plexusCommand.getChild(ARGS));
        String input = getValue(plexusCommand.getChild(STDIN));
        String inputUrl = getValue(plexusCommand.getChild(STDIN_URL));
        Command command = new Command();
        command.setArgs(args);
        command.setStdin(input);
        command.setStdinUrl(inputUrl);
        return command;
    }

    protected List<String> getArgs(PlexusConfiguration plexusArgs) {
        if (plexusArgs == null) {
            return new ArrayList<String>();
        }
        PlexusConfiguration[] argChildren = plexusArgs.getChildren(ARG);
        String[] argValues = getValues(argChildren);
        return Arrays.asList(argValues);
    }

    protected String[] getValues(PlexusConfiguration[] plexusConfigs) {
        if (plexusConfigs == null) {
            return new String[] {};
        } else {
            String[] values = new String[plexusConfigs.length];
            for (int i = 0; i < values.length; i++) {
                values[i] = getValue(plexusConfigs[i]);
            }
            return values;
        }
    }

    protected String getValue(PlexusConfiguration plexusConfig) {
        if (plexusConfig != null) {
            return plexusConfig.getValue();
        } else {
            return null;
        }
    }

}

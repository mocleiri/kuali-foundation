package org.kuali.maven.plugins.jenkins.context;

/**
 * Provides context for executing an external process.
 *
 * executable - the name of the executable to run<br>
 * args - command line arguments for the executable<br>
 * input - anything provided here gets piped to standard in of the executable<br>
 */
public class ProcessContext {
    String executable;
    String[] args;
    String input;

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}

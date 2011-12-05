package org.kuali.maven.plugins.jenkins.context;

public class ProcessContext {
    String[] args;
    String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

}

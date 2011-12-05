package org.kuali.maven.plugins.jenkins.context;

public class ProcessResult {
    int exitValue;
    String input;
    String output;
    long elapsed;

    public int getExitValue() {
        return exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public long getMillis() {
        return elapsed;
    }

    public void setMillis(long millis) {
        this.elapsed = millis;
    }

}

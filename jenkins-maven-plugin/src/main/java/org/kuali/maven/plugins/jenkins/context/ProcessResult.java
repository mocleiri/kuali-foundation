package org.kuali.maven.plugins.jenkins.context;

import java.util.List;

public class ProcessResult {
    int exitValue;
    String input;
    List<String> output;
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

    public long getMillis() {
        return elapsed;
    }

    public void setMillis(long millis) {
        this.elapsed = millis;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

}

package org.kuali.maven.plugins.jenkins.context;

import java.util.List;

public class ProcessResult {
    ProcessContext context;
    int exitValue;
    List<String> outputLines;
    String output;
    long elapsed;
    long start;
    long stop;

    public ProcessContext getContext() {
        return context;
    }

    public void setContext(ProcessContext context) {
        this.context = context;
    }

    public int getExitValue() {
        return exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public List<String> getOutputLines() {
        return outputLines;
    }

    public void setOutputLines(List<String> outputLines) {
        this.outputLines = outputLines;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStop() {
        return stop;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }

}

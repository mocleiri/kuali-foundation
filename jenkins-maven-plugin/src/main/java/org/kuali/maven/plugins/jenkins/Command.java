package org.kuali.maven.plugins.jenkins;

import java.util.List;

public class Command {
    private List<String> args;
    private String stdin;
    private String stdinUrl;

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }

    public String getStdinUrl() {
        return stdinUrl;
    }

    public void setStdinUrl(String stdinUrl) {
        this.stdinUrl = stdinUrl;
    }
}

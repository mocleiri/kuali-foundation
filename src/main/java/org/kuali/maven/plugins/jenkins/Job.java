package org.kuali.maven.plugins.jenkins;

import org.codehaus.plexus.util.cli.Commandline;

public class Job implements Comparable<Job> {
    String name;
    int buildNumber;
    String src;
    String dst;
    Commandline commandLine;

    @Override
    public int compareTo(Job other) {
        return this.getName().compareTo(other.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public Commandline getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(Commandline commandLine) {
        this.commandLine = commandLine;
    }

}

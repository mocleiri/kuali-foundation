package org.kuali.maven.mojo;

import org.apache.maven.project.MavenProject;

public class JobContext {
    String filename;
    String jobType;
    MavenProject project;
    String scmType;
    String scmUrl;
    String majorVersion;
    String buildTimestampFormat;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String type) {
        this.jobType = type;
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public String getScmType() {
        return scmType;
    }

    public void setScmType(String scmType) {
        this.scmType = scmType;
    }

    public String getScmUrl() {
        return scmUrl;
    }

    public void setScmUrl(String scmUrl) {
        this.scmUrl = scmUrl;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }

    public String getBuildTimestampFormat() {
        return buildTimestampFormat;
    }

    public void setBuildTimestampFormat(String buildTimestampFormat) {
        this.buildTimestampFormat = buildTimestampFormat;
    }
}

package org.kuali.maven.common;

import java.io.File;
import java.util.List;
import java.util.Properties;

public interface MvnContext {
    public static final String MAVEN_OPTS = "MAVEN_OPTS";

    public abstract Properties getProjectProperties();

    public abstract File getWorkingDir();

    public abstract void setWorkingDir(File workingDir);

    public abstract File getBasedir();

    public abstract void setBasedir(File basedir);

    public abstract String getExecutable();

    public abstract void setExecutable(String executable);

    public abstract String getPom();

    public abstract void setPom(String pom);

    public abstract boolean isFilterPom();

    public abstract void setFilterPom(boolean filterPom);

    public abstract List<String> getArgs();

    public abstract void setArgs(List<String> args);

    public abstract List<String> getProperties();

    public abstract void setProperties(List<String> properties);

    public abstract boolean isAddEnvironment();

    public abstract void setAddEnvironment(boolean addEnvironment);

    public abstract boolean isAddMavenOpts();

    public abstract void setAddMavenOpts(boolean addMavenOpts);

    public abstract boolean isFailOnError();

    public abstract void setFailOnError(boolean failOnError);

}
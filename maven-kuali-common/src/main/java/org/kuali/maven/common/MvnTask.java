/**
 * Copyright 2004-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Invoke mvn from Ant
 *
 */
public class MvnTask extends Task implements MvnContext {
    MvnExecutor executor = new MvnExecutor();

    @Override
    public Properties getProjectProperties() {
        Hashtable<?, ?> hashTable = getProject().getProperties();
        Properties properties = new Properties();
        Set<?> keys = hashTable.keySet();
        for (Object key : keys) {
            Object value = hashTable.get(key);
            properties.setProperty(key.toString(), value.toString());
        }
        return properties;
    }

    /**
     * The working directory where the task makes a local copy of the pom (if a pom is supplied)
     */
    private File workingDir;

    /**
     * The base directory for the new mvn invocation.
     */
    private File basedir;

    /**
     * The Maven executable. By default, the executable to use is located via the ${maven.home} system property. This
     * causes the new mvn invocation to mirror what is currently executing (same version, etc). You can override this
     * behavior by supplying your own executable
     */
    private String executable;

    /**
     * The pom to supply to the new mvn invocation. This can be a file or any url Spring resource loading can understand
     */
    private String pom;

    /**
     * If true, the pom will be filtered using properties from the current project before being invoked
     */
    private boolean filterPom = false;

    /**
     * Arguments to supply to the new mvn invocation eg "clean install"
     */
    private List<String> args = new ArrayList<String>();

    /**
     * List of properties from the current project to propagate to the new mvn invocation
     */
    private List<String> properties = new ArrayList<String>();

    /**
     * If true, the current environment is passed to the new mvn invocation
     */
    private boolean addEnvironment = false;

    /**
     * If true, the environment variable MAVEN_OPTS (if set) is passed to the new mvn invocation
     */
    private boolean addMavenOpts = true;

    /**
     * If true, the Ant build will fail if the new mvn invocation returns a non-zero exit value, otherwise the Ant build
     * will continue
     */
    private boolean failOnError = true;

    public void addConfiguredArg(Arg arg) {
        args.add(arg.getValue());
    }

    public void addConfiguredProperty(Property property) {
        properties.add(property.getKey());
    }

    protected void configure() {
        if (workingDir == null) {
            workingDir = new File(getProject().getBaseDir().getAbsolutePath() + File.separator + "target"
                    + File.separator + "mvn");
        }
        if (basedir == null) {
            basedir = getProject().getBaseDir();
        }
    }

    @Override
    public void execute() throws BuildException {
        try {
            configure();
            executor.execute(this);
        } catch (Exception e) {
            throw new BuildException("Error invoking mvn", e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#getWorkingDir()
     */
    @Override
    public File getWorkingDir() {
        return workingDir;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setWorkingDir(java.io.File)
     */
    @Override
    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#getBasedir()
     */
    @Override
    public File getBasedir() {
        return basedir;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setBasedir(java.io.File)
     */
    @Override
    public void setBasedir(File basedir) {
        this.basedir = basedir;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#getExecutable()
     */
    @Override
    public String getExecutable() {
        return executable;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setExecutable(java.lang.String)
     */
    @Override
    public void setExecutable(String executable) {
        this.executable = executable;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#getPom()
     */
    @Override
    public String getPom() {
        return pom;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setPom(java.lang.String)
     */
    @Override
    public void setPom(String pom) {
        this.pom = pom;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#isFilterPom()
     */
    @Override
    public boolean isFilterPom() {
        return filterPom;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setFilterPom(boolean)
     */
    @Override
    public void setFilterPom(boolean filterPom) {
        this.filterPom = filterPom;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#getArgs()
     */
    @Override
    public List<String> getArgs() {
        return args;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setArgs(java.util.List)
     */
    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#getProperties()
     */
    @Override
    public List<String> getProperties() {
        return properties;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setProperties(java.util.List)
     */
    @Override
    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#isAddEnvironment()
     */
    @Override
    public boolean isAddEnvironment() {
        return addEnvironment;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setAddEnvironment(boolean)
     */
    @Override
    public void setAddEnvironment(boolean addEnvironment) {
        this.addEnvironment = addEnvironment;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#isAddMavenOpts()
     */
    @Override
    public boolean isAddMavenOpts() {
        return addMavenOpts;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setAddMavenOpts(boolean)
     */
    @Override
    public void setAddMavenOpts(boolean addMavenOpts) {
        this.addMavenOpts = addMavenOpts;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#isFailOnError()
     */
    @Override
    public boolean isFailOnError() {
        return failOnError;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.kuali.maven.common.MvnContext2#setFailOnError(boolean)
     */
    @Override
    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

}

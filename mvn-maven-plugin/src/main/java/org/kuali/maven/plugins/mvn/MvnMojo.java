/**
 * Copyright 2011-2012 The Kuali Foundation
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
package org.kuali.maven.plugins.mvn;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.common.ResourceUtils;

/**
 * Invoke mvn from Maven. The default behavior is to use the ${maven.home} system property to look up the corresponding
 *
 * @goal mvn
 */
public class MvnMojo extends AbstractMojo {
    ResourceUtils resourceUtils = new ResourceUtils();
    PropertiesUtils propertiesUtils = new PropertiesUtils();

    private static final String MAVEN_OPTS = "MAVEN_OPTS";

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * The working directory where the plugin makes a local copy of the pom (if a pom is supplied)
     *
     * @parameter expression="${mvn.workingDir}" default-value="${project.build.directory}/mvn"
     * @required
     */
    private File workingDir;

    /**
     * The Maven executable. Located via the ${maven.home} system property by default. This causes the new mvn
     * invocation to mirror the one that is currently executing (same version, etc). You can override this behavior by
     * supplying your own executable
     *
     * @parameter expression="${mvn.executable}"
     */
    private String executable;

    /**
     * The pom to supply to the mvn invocation. This can be a file or any url Spring resource loading can understand
     *
     * eg classpath:pom.xml
     *
     * @parameter expression="${mvn.pom}"
     */
    private String pom;

    /**
     * If true, the pom will be filtered using properties from the current project BEFORE mvn is invoked on it
     *
     * @parameter expression="${mvn.filterPom}" default-value="false"
     */
    private boolean filterPom;

    /**
     * Arguments to supply to the mvn invocation eg "clean install"
     *
     * @parameter
     * @required
     */
    private List<String> args;

    /**
     * List of properties from the current project to propagate to the mvn invocation
     *
     * @parameter
     */
    private List<String> properties;

    /**
     * If true, the current environment is passed to the mvn invocation
     *
     * @parameter expression="${mvn.addSystemEnvironment}" default-value="false"
     */
    private boolean addSystemEnvironment;

    /**
     * If true, the environment variable MAVEN_OPTS is passed to the mvn invocation (if it is set)
     *
     * @parameter expression="${mvn.addMavenOpts}" default-value="true"
     */
    private boolean addMavenOpts;

    /**
     * If true, the original Maven build will fail if the mvn invocation returns a non-zero exit value, otherwise the
     * Maven build will continue
     *
     * @parameter expression="${mvn.failOnError}" default-value="true"
     * @required
     */
    private boolean failOnError;

    @Override
    public void execute() throws MojoExecutionException {
        int exitValue = -1;
        try {
            StreamConsumer stdout = new DefaultConsumer();
            StreamConsumer stderr = new DefaultConsumer();
            Commandline cl = getCommandLine();
            prepareFileSystem(cl);
            getLog().info("Invoking " + cl.toString());
            exitValue = CommandLineUtils.executeCommandLine(cl, stdout, stderr);
        } catch (Exception e) {
            throw new MojoExecutionException("Error invoking mvn", e);
        }
        validateExitValue(exitValue);
    }

    protected Commandline getCommandLine() throws Exception {
        Commandline cl = new Commandline();
        cl.setExecutable(executable);
        cl.setWorkingDirectory(workingDir);
        if (addSystemEnvironment) {
            cl.addSystemEnvironment();
        }
        addMavenOpts(cl);
        addArgs(cl, args);
        addProperties(cl, properties);
        return cl;
    }

    protected void addMavenOpts(Commandline cl) {
        String mavenOpts = System.getenv(MAVEN_OPTS);
        if (!StringUtils.isBlank(mavenOpts) && addMavenOpts) {
            cl.addEnvironment(MAVEN_OPTS, mavenOpts);
        }
    }

    protected void prepareFileSystem(Commandline cl) throws IOException {
        FileUtils.forceMkdir(workingDir);
        if (StringUtils.isBlank(pom)) {
            return;
        }
        getLog().info("POM: " + pom);
        String s = resourceUtils.read(pom);
        if (filterPom) {
            Properties props = getAllProperties();
            s = propertiesUtils.getResolvedValue(s, props);
        }
        File file = File.createTempFile("pom.", ".xml", workingDir);
        resourceUtils.write(file.getCanonicalPath(), s);
        cl.createArg().setValue("-f");
        cl.createArg().setValue(file.getName());
    }

    protected Properties getAllProperties() {
        Properties props = new Properties();
        // Load project properties first
        props.putAll(project.getProperties());
        // Environment properties are all prefixed with "env"
        props = propertiesUtils.getEnvironmentProperties();
        // System properties override everything
        props.putAll(System.getProperties());
        return props;

    }

    protected void addArgs(Commandline cl, List<String> args) {
        if (args == null || args.size() == 0) {
            return;
        }
        for (String arg : args) {
            cl.createArg().setValue(arg);
        }
    }

    protected void addProperties(Commandline cl, List<String> properties) {
        if (properties == null || properties.size() == 0) {
            return;
        }
        for (String key : properties) {
            String value = getProperty(key);
            addProperty(cl, key, value);
        }
    }

    protected String getProperty(String key) {
        String sys = System.getProperty(key);
        if (!StringUtils.isBlank(sys)) {
            return sys;
        } else {
            return project.getProperties().getProperty(key);
        }
    }

    protected void addProperty(Commandline cl, String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }
        cl.createArg().setValue("-D" + key + "=" + value);
    }

    protected boolean isFail(int exitValue) {
        return exitValue != 0 && failOnError;
    }

    protected void validateExitValue(int exitValue) throws MojoExecutionException {
        if (isFail(exitValue)) {
            throw new MojoExecutionException("Non-zero exit value");
        }
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public boolean isFailOnError() {
        return failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public MavenProject getProject() {
        return project;
    }

    public String getPom() {
        return pom;
    }

    public void setPom(String pom) {
        this.pom = pom;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public boolean isAddSystemEnvironment() {
        return addSystemEnvironment;
    }

    public void setAddSystemEnvironment(boolean addSystemEnvironment) {
        this.addSystemEnvironment = addSystemEnvironment;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public boolean isFilterPom() {
        return filterPom;
    }

    public void setFilterPom(boolean filter) {
        this.filterPom = filter;
    }

    public boolean isAddMavenOpts() {
        return addMavenOpts;
    }

    public void setAddMavenOpts(boolean addMavenOpts) {
        this.addMavenOpts = addMavenOpts;
    }

}
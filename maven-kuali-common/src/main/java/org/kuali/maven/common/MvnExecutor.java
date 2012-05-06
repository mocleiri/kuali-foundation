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
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.interpolation.os.Os;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.DefaultConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Invoke mvn from Maven.
 */
public class MvnExecutor {
    private static final Logger log = LoggerFactory.getLogger(MvnExecutor.class);
    ResourceUtils resourceUtils = new ResourceUtils();
    PropertiesUtils propertiesUtils = new PropertiesUtils();

    public void execute(MvnContext context) throws Exception {
        StreamConsumer stdout = new DefaultConsumer();
        StreamConsumer stderr = new DefaultConsumer();
        Commandline cl = getCommandLine(context);
        showConfig(context, cl);
        prepareFileSystem(context, cl);
        log.info(cl.toString());
        int exitValue = CommandLineUtils.executeCommandLine(cl, stdout, stderr);
        validateExitValue(context, exitValue);
    }

    protected String getMavenArgs(Commandline cl) {
        StringBuilder sb = new StringBuilder();
        String[] args = cl.getArguments();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (i != 0) {
                sb.append(" ");
            }
            sb.append(arg);
        }
        return sb.toString();
    }

    protected void showConfig(MvnContext context, Commandline cl) {
        if (!StringUtils.isBlank(context.getPom())) {
            log.info("Maven POM - " + context.getPom());
        }
        String args = getMavenArgs(cl);
        log.info("Maven Args - " + args);
        if (isAddMavenOpts(context)) {
            log.info(MvnContext.MAVEN_OPTS + '=' + System.getenv(MvnContext.MAVEN_OPTS));
        }
    }

    protected String getMvnExecutable(String executable) {
        if (!StringUtils.isBlank(executable)) {
            return executable;
        }
        String mavenHome = System.getProperty("maven.home");
        if (StringUtils.isBlank(mavenHome)) {
            log.info("${maven.home} not set.  Using default executable '" + getActualExecutable() + "'");
            return getActualExecutable();
        } else {
            return mavenHome + File.separator + "bin" + File.separatorChar + getActualExecutable();
        }
    }

    protected String getActualExecutable() {
        if (!Os.isFamily(Os.FAMILY_WINDOWS)) {
            return "mvn";
        } else {
            return "mvn.bat";
        }
    }

    protected Commandline getCommandLine(MvnContext context) throws Exception {
        Commandline cl = new Commandline();
        cl.setExecutable(getMvnExecutable(context.getExecutable()));
        cl.setWorkingDirectory(context.getBasedir());
        if (context.isAddEnvironment()) {
            cl.addSystemEnvironment();
        }
        addMavenOpts(context, cl);
        addArgs(cl, context.getArgs());
        addProperties(context, cl, context.getProperties());
        return cl;
    }

    protected boolean isAddMavenOpts(MvnContext context) {
        String mavenOpts = System.getenv(MvnContext.MAVEN_OPTS);
        return context.isAddMavenOpts() && !StringUtils.isBlank(mavenOpts);
    }

    protected void addMavenOpts(MvnContext context, Commandline cl) {
        if (isAddMavenOpts(context)) {
            String mavenOpts = System.getenv(MvnContext.MAVEN_OPTS);
            cl.addEnvironment(MvnContext.MAVEN_OPTS, mavenOpts);
        }
    }

    protected void prepareFileSystem(MvnContext context, Commandline cl) throws IOException {
        FileUtils.forceMkdir(context.getWorkingDir());
        if (StringUtils.isBlank(context.getPom())) {
            return;
        }
        String s = resourceUtils.read(context.getPom());
        if (context.isFilterPom()) {
            Properties props = getAllProperties(context.getProjectProperties());
            s = propertiesUtils.getResolvedValue(s, props);
        }
        File file = File.createTempFile("pom.", ".xml", context.getWorkingDir());
        resourceUtils.write(file, s);
        cl.createArg().setValue("-f");
        cl.createArg().setValue(getRelativePath(context.getBasedir(), file));
    }

    protected String getRelativePath(File dir, File file) throws IOException {
        String s1 = dir.getCanonicalPath();
        String s2 = file.getCanonicalPath();
        int pos = s2.indexOf(s1);
        if (pos == -1) {
            return s2;
        } else {
            return s2.substring(s1.length() + 1);
        }
    }

    protected Properties getAllProperties(Properties projectProperties) {
        Properties props = new Properties();
        // Load project properties first
        props.putAll(projectProperties);
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

    protected void addProperties(MvnContext context, Commandline cl, List<String> properties) {
        if (properties == null || properties.size() == 0) {
            return;
        }
        for (String key : properties) {
            String value = getProperty(context, key);
            addProperty(cl, key, value);
        }
    }

    protected String getProperty(MvnContext context, String key) {
        String sys = System.getProperty(key);
        if (!StringUtils.isBlank(sys)) {
            return sys;
        } else {
            return context.getProjectProperties().getProperty(key);
        }
    }

    protected void addProperty(Commandline cl, String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }
        cl.createArg().setValue("-D" + key + "=" + value);
    }

    protected boolean isFail(MvnContext context, int exitValue) {
        return exitValue != 0 && context.isFailOnError();
    }

    protected void validateExitValue(MvnContext context, int exitValue) throws MojoExecutionException {
        if (isFail(context, exitValue)) {
            throw new MojoExecutionException("Non-zero exit value");
        }
    }

}
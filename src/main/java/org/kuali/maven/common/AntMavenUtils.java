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
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.codehaus.plexus.util.StringUtils;

public class AntMavenUtils {
    public static final String MVN_COMPILE_CLASSPATH_KEY = "maven.compile.classpath";
    public static final String MVN_RUNTIME_CLASSPATH_KEY = "maven.runtime.classpath";
    public static final String MVN_TEST_CLASSPATH_KEY = "maven.test.classpath";
    public static final String MVN_PLUGIN_CLASSPATH_KEY = "maven.plugin.classpath";

    /**
     * Copy properties from the ant project to the maven project.
     *
     * @param antProject
     *            not null
     * @param mavenProject
     *            not null
     */
    public void copyProperties(Project antProject, MavenProject mavenProject, Log log) {
        log.debug("Propagated Ant properties to Maven properties");
        Hashtable<?, ?> antProps = antProject.getProperties();

        Iterator<?> iter = antProps.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();

            Properties mavenProperties = mavenProject.getProperties();
            if (mavenProperties.getProperty(key) != null) {
                log.debug("Ant property '" + key + "=" + mavenProperties.getProperty(key)
                        + "' clashes with an existing Maven property, SKIPPING this Ant property propagation.");
                continue;
            }
            mavenProperties.setProperty(key, antProps.get(key).toString());
        }
    }

    /**
     * @param artifacts
     * @param antProject
     * @return a path
     * @throws DependencyResolutionRequiredException
     */
    public Path getPathFromArtifacts(Collection<Artifact> artifacts, Project antProject)
            throws DependencyResolutionRequiredException {
        if (artifacts == null) {
            return new Path(antProject);
        }

        List<String> list = new ArrayList<String>(artifacts.size());
        for (Iterator<?> i = artifacts.iterator(); i.hasNext();) {
            Artifact a = (Artifact) i.next();
            File file = a.getFile();
            if (file == null) {
                throw new DependencyResolutionRequiredException(a);
            }
            list.add(file.getPath());
        }

        Path p = new Path(antProject);
        p.setPath(StringUtils.join(list.iterator(), File.pathSeparator));
        return p;
    }

    /**
     * Copy properties from the maven project to the ant project.
     *
     * @param mavenProject
     * @param antProject
     */
    public void copyProperties(MavenProject mavenProject, Project antProject, String prefix, Log log,
            ArtifactRepository localRepo) {
        Properties mavenProps = mavenProject.getProperties();
        Iterator<?> iter = mavenProps.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            antProject.setProperty(key, mavenProps.getProperty(key));
        }

        // Set the POM file as the ant.file for the tasks run directly in Maven.
        antProject.setProperty("ant.file", mavenProject.getFile().getAbsolutePath());

        // Add some of the common maven properties
        log.debug("Setting properties with prefix: " + prefix);
        antProject.setProperty((prefix + "project.groupId"), mavenProject.getGroupId());
        antProject.setProperty((prefix + "project.artifactId"), mavenProject.getArtifactId());
        antProject.setProperty((prefix + "project.name"), mavenProject.getName());
        if (mavenProject.getDescription() != null) {
            antProject.setProperty((prefix + "project.description"), mavenProject.getDescription());
        }
        antProject.setProperty((prefix + "project.version"), mavenProject.getVersion());
        antProject.setProperty((prefix + "project.packaging"), mavenProject.getPackaging());
        antProject.setProperty((prefix + "project.build.directory"), mavenProject.getBuild().getDirectory());
        antProject.setProperty((prefix + "project.basedir"), mavenProject.getBasedir().getAbsolutePath());
        antProject
                .setProperty((prefix + "project.build.outputDirectory"), mavenProject.getBuild().getOutputDirectory());
        antProject.setProperty((prefix + "project.build.testOutputDirectory"), mavenProject.getBuild()
                .getTestOutputDirectory());
        antProject
                .setProperty((prefix + "project.build.sourceDirectory"), mavenProject.getBuild().getSourceDirectory());
        antProject.setProperty((prefix + "project.build.testSourceDirectory"), mavenProject.getBuild()
                .getTestSourceDirectory());
        antProject.setProperty((prefix + "localRepository"), localRepo.toString());
        antProject.setProperty((prefix + "settings.localRepository"), localRepo.getBasedir());

        // Add properties for dependency artifacts
        Set<?> depArtifacts = mavenProject.getArtifacts();
        for (Iterator<?> it = depArtifacts.iterator(); it.hasNext();) {
            Artifact artifact = (Artifact) it.next();

            String propName = artifact.getDependencyConflictId();

            antProject.setProperty(prefix + propName, artifact.getFile().getPath());
        }

    }

    /**
     * Setup an Ant BuildLogger
     */
    public BuildLogger getBuildLogger(Log logger) {
        DefaultLogger antLogger = new DefaultLogger();
        antLogger.setOutputPrintStream(System.out);
        antLogger.setErrorPrintStream(System.err);

        if (logger.isDebugEnabled()) {
            antLogger.setMessageOutputLevel(Project.MSG_DEBUG);
        } else if (logger.isInfoEnabled()) {
            antLogger.setMessageOutputLevel(Project.MSG_INFO);
        } else if (logger.isWarnEnabled()) {
            antLogger.setMessageOutputLevel(Project.MSG_WARN);
        } else if (logger.isErrorEnabled()) {
            antLogger.setMessageOutputLevel(Project.MSG_ERR);
        } else {
            antLogger.setMessageOutputLevel(Project.MSG_VERBOSE);
        }
        return antLogger;
    }

    /**
     * Create the Ant equivalent of the Maven classpath's for compile, runtime, test, and for the plugin
     */
    public Map<String, Path> getPathRefs(Project ant, MavenProject mvn, List<Artifact> pluginArtifacts)
            throws DependencyResolutionRequiredException {

        Map<String, Path> pathRefs = new HashMap<String, Path>();

        // compile
        Path mcp = new Path(ant);
        mcp.setPath(StringUtils.join(mvn.getCompileClasspathElements().iterator(), File.pathSeparator));
        pathRefs.put(MVN_COMPILE_CLASSPATH_KEY, mcp);

        // runtime
        Path mrp = new Path(ant);
        mrp.setPath(StringUtils.join(mvn.getRuntimeClasspathElements().iterator(), File.pathSeparator));
        pathRefs.put(MVN_RUNTIME_CLASSPATH_KEY, mrp);

        // test
        Path mtp = new Path(ant);
        mtp.setPath(StringUtils.join(mvn.getTestClasspathElements().iterator(), File.pathSeparator));
        pathRefs.put(MVN_TEST_CLASSPATH_KEY, mtp);

        // plugin
        Path mpp = getPathFromArtifacts(pluginArtifacts, ant);
        pathRefs.put(MVN_PLUGIN_CLASSPATH_KEY, mpp);
        return pathRefs;
    }

    /**
     * Add this map of objects to the Ant projects as named references
     */
    public void addRefs(Project antProject, Map<String, ?> refs) {
        for (Map.Entry<String, ?> pair : refs.entrySet()) {
            antProject.addReference(pair.getKey(), pair.getValue());
        }
    }

    /**
     * Set paths as properties on the Ant project
     */
    public void setPathProperties(Project antProject, Map<String, Path> paths) {
        for (Map.Entry<String, ?> pair : paths.entrySet()) {
            antProject.setProperty(pair.getKey(), pair.getValue().toString());
        }
    }

}

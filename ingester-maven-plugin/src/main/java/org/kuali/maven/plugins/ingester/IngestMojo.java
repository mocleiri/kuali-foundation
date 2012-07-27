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
package org.kuali.maven.plugins.ingester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.rice.kew.batch.MyXMLPollerServiceImpl;

/**
 * Ingest workflow documents into Rice
 * 
 * @goal ingest
 */
public class IngestMojo extends AbstractMojo {

    /**
     * The Maven project object
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * The type of database (mysql, oracle, etc)
     * 
     * @parameter expression="${ingester.jdbcVendor}" default-value="mysql"
     * @required
     */
    private String jdbcVendor;

    /**
     * The jdbc connect url
     * 
     * @parameter expression="${ingester.jdbcUrl}"
     * @required
     */
    private String jdbcUrl;

    /**
     * The database username
     * 
     * @parameter expression="${ingester.jdbcUsername}"
     * @required
     */
    private String jdbcUsername;

    /**
     * The database password for the specified username
     * 
     * @parameter expression="${ingester.jdbcPassword}"
     * @required
     */
    private String jdbcPassword;

    /**
     * Jdbc driver to use. This is optional because a default driver for the specified <code>jdbcVendor</code> is
     * usually the correct one to use. If a driver is provided here, it overrides the default.
     * 
     * @parameter expression="${ingester.jdbcDriver}"
     */
    private String jdbcDriver;

    /**
     * Namespace for Rice
     * 
     * @parameter expression="${ingester.namespace}"
     * @required
     */
    private String namespace;

    /**
     * The directory containing documents to ingest
     * 
     * @parameter expression="${ingester.sourceDir}" default-value="${project.basedir}/src/main/resources"
     * @required
     */
    private File sourceDir;

    /**
     * The working directory where the documents to ingest are copied. The plugin loads them from here, and transitions
     * them from the "pending" directory to either the "completed" or "problem" directories.
     * 
     * @parameter expression="${ingester.workingDir}" default-value="${project.build.directory}/ingester"
     * @required
     */
    private File workingDir;

    /**
     * System property key the plugin uses to locate an external properties file to load
     * 
     * @parameter expression="${ingester.propsKey}" default-value="ingester.config.location"
     */
    private String propsKey;

    /**
     * Location of the external properties file to load
     * 
     * @parameter expression="${ingester.propsLoc}"
     *            default-value="${project.build.directory}/ingester/config/ingester.properties"
     */
    private File propsLoc;

    /**
     * Inclusion patterns. By default &#42;&#42;/&#42;.xml is included
     * 
     * @parameter
     */
    private String[] includes = { "**/*.xml" };

    /**
     * Exclusion patterns. By default, nothing is excluded.
     * 
     * @parameter
     */
    private String[] excludes;

    @Override
    public void execute() throws MojoExecutionException {
        showConfig();
        List<File> files = getFiles();
        if (!validate(files)) {
            return;
        }
        DirectoryStructure ds = getDirectoryStructure();
        prepareFileSystem(ds, files);
        prepareProperties();
        ingest(ds);
    }

    protected boolean validate(List<File> files) {
        if (files.size() == 0) {
            getLog().info("Skipping execution.  No matching files found");
            return false;
        } else {
            int paddingSize = (files.size() + "").length();
            getLog().info("Located " + files.size() + " documents to ingest");
            int sequence = 0;
            for (File file : files) {
                sequence++;
                String prefix = StringUtils.leftPad(sequence + "", paddingSize, "0");
                getLog().debug(prefix + " - " + getRelativePath(project.getBasedir(), file));
            }
            return true;
        }

    }

    protected DirectoryStructure getDirectoryStructure() {
        DirectoryStructure ds = new DirectoryStructure();
        ds.setPendingDir(new File(workingDir.getAbsolutePath() + File.separatorChar + "pending"));
        ds.setCompletedDir(new File(workingDir.getAbsolutePath() + File.separatorChar + "completed"));
        ds.setProblemDir(new File(workingDir.getAbsolutePath() + File.separatorChar + "problem"));
        return ds;
    }

    protected void prepareFileSystem(DirectoryStructure ds, List<File> files) throws MojoExecutionException {
        try {
            mkdirs(ds);
            getLog().info("Copying " + files.size() + " files to the pending directory");
            copyToDir(ds.getPendingDir(), files);
        } catch (IOException e) {
            throw new MojoExecutionException("Error preparing directory structure", e);
        }
    }

    protected void prepareProperties() throws MojoExecutionException {
        String value = System.getProperty(propsKey);
        if (!StringUtils.isBlank(value)) {
            // They have provided a location to pull properties from.
            // We will do nothing further. They are on their own to make sure the configuration is correct
            return;
        }

        String path = getPath(propsLoc);
        System.setProperty(propsKey, path);
        getLog().info("Setting " + propsKey + "=" + path);

        String jdbcVendorValue = System.getProperty("jdbc.vendor");
        if (StringUtils.isBlank(jdbcVendorValue)) {
            System.setProperty("jdbc.vendor", jdbcVendor);
        }

        Properties properties = new Properties();
        properties.setProperty("app.namespace", namespace);
        properties.setProperty("jdbc.url", jdbcUrl);
        properties.setProperty("jdbc.vendor", jdbcVendor);
        properties.setProperty("jdbc.username", jdbcUsername);
        properties.setProperty("jdbc.password", jdbcPassword);
        if (!StringUtils.isBlank(jdbcDriver)) {
            properties.setProperty("jdbc.driver", jdbcDriver);
        }
        try {
            getLog().info("Creating " + path);
            PropertyUtils.store(properties, propsLoc);
        } catch (IOException e) {
            throw new MojoExecutionException("Error storing properties", e);
        }
    }

    protected void ingest(DirectoryStructure ds) throws MojoExecutionException {
        SpringContextForWorkflowImporter.initializeApplicationContext();
        MyXMLPollerServiceImpl parser = new MyXMLPollerServiceImpl();
        parser.setXmlPendingLocation(ds.getPendingDir().getAbsolutePath());
        parser.setXmlCompletedLocation(ds.getCompletedDir().getAbsolutePath());
        parser.setXmlProblemLocation(ds.getProblemDir().getAbsolutePath());
        parser.run();
        try {
            SpringContextForWorkflowImporter.close();
        } catch (Exception e) {
            throw new MojoExecutionException("Error closing spring context", e);
        }
    }

    protected void copyToDir(File dir, List<File> files) throws IOException {
        int paddingSize = (files.size() + "").length();
        int sequence = 0;
        for (File file : files) {
            sequence++;
            String prefix = StringUtils.leftPad(sequence + "", paddingSize, "0");
            String filename = dir.getAbsolutePath() + File.separatorChar + prefix + "-" + file.getName();
            File newFile = new File(filename);
            String rp1 = getRelativePath(project.getBasedir(), newFile);
            getLog().info(prefix + " - " + rp1);
            FileUtils.copyFile(file, newFile);
        }
    }

    protected String getRelativePath(File dir, File file) {
        String dirPath = dir.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        String s = StringUtils.replace(filePath, dirPath, "");
        return s.substring(1);
    }

    protected void mkdirs(DirectoryStructure ds) throws IOException {
        FileUtils.forceMkdir(ds.getPendingDir());
        FileUtils.forceMkdir(ds.getCompletedDir());
        FileUtils.forceMkdir(ds.getProblemDir());
    }

    /**
     * Return the list of files to ingest
     */
    protected List<File> getFiles() {
        SimpleScanner scanner = new SimpleScanner(sourceDir, includes, excludes, false);
        String[] filenames = scanner.getSelectedFiles();
        List<File> fileList = new ArrayList<File>();
        for (String filename : filenames) {
            File file = new File(sourceDir, filename);
            fileList.add(file);
        }
        Collections.sort(fileList);
        return fileList;
    }

    protected String toCSV(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    protected void showConfig() throws MojoExecutionException {
        try {
            getLog().info("Namespace - " + namespace);
            getLog().info("Directory - " + sourceDir.getCanonicalPath());
            getLog().info("Includes - " + toCSV(includes));
            getLog().info("Excludes - " + toCSV(excludes));
            getLog().info("JDBC Vendor - " + jdbcVendor);
            getLog().info("JDBC Url - " + jdbcUrl);
            getLog().info("JDBC Username - " + jdbcUsername);
        } catch (IOException e) {
            throw new MojoExecutionException("Error showing config", e);
        }
    }

    protected String getPath(File file) throws MojoExecutionException {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new MojoExecutionException("Error obtaining canonical path", e);
        }
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public File getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(File sourceDir) {
        this.sourceDir = sourceDir;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File outputDir) {
        this.workingDir = outputDir;
    }

    public MavenProject getProject() {
        return project;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getPropsKey() {
        return propsKey;
    }

    public void setPropsKey(String propsKey) {
        this.propsKey = propsKey;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcVendor() {
        return jdbcVendor;
    }

    public void setJdbcVendor(String jdbcVendor) {
        this.jdbcVendor = jdbcVendor;
    }

}

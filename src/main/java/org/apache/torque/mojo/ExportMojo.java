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
package org.apache.torque.mojo;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.torque.util.JdbcConfigurer;
import org.kuali.core.db.torque.DumpTask;
import org.kuali.core.db.torque.PropertyHandlingException;
import org.kuali.core.db.torque.StringFilter;

/**
 * Base Mojo for export related functionality
 */
public abstract class ExportMojo extends AntTaskMojo {

    /**
     * Additional properties for the JDBC driver
     *
     * @parameter
     */
    private Properties driverProperties;

    /**
     * If true, files will be exported in a format that the Ant impex tasks can understand
     *
     * @parameter expression="${antCompatibilityMode}" default-value="false"
     */
    private boolean antCompatibilityMode;

    /**
     * The Maven artifactId. Included here as a simple property of the mojo itself to facilitate usage of
     * <code>BeanUtils.copyProperties()</code> to copy properties between Mojo's and Ant tasks
     *
     * @parameter expression="${artifactId}" default-value="${project.artifactId}"
     * @required
     */
    private String artifactId;

    /**
     * By default the version of the maven plugin being used during the build will be included as part of a comment in
     * the XML. Set this to false to prevent this. Useful if you are committing files to SCM and don't want a change in
     * the version of the plugin to show up as a change to the data.
     *
     * @parameter expression="${includeVersionInComment}" default-value="true"
     * @required
     */
    private boolean includeVersionInComment;

    /**
     * Additional comment that gets placed into the generated XML document. Anything included here gets appended to the
     * default value <br>
     * <b>Default value is:</b> Auto-generated by the maven-impex-plugin v1.0.2<br>
     *
     * @parameter expression="${comment}"
     */
    private String comment;

    /**
     * Comma separated list of regular expressions for tables to include in the export
     *
     * @parameter expression="${includes}"
     */
    private String includes;

    /**
     * Comma separated list of regular expressions for tables to exclude from the export
     *
     * @parameter expression="${excludes}"
     */
    private String excludes;

    /**
     * The type of database we are targeting (eg oracle, mysql). This is optional if <code>url</code> is supplied as the
     * database type will be automatically detected based on the <code>url</code>. If targetDatabase is explicitly
     * supplied it will override the type selected by the automatic detection logic.
     *
     * @parameter expression="${targetDatabase}"
     */
    private String targetDatabase;

    /**
     * The schema to export. This parameter is optional, as the schema to export is automatically derived from platform
     * specific logic that converts the artifactId.<br>
     * <br>
     * For example:<br>
     * ks-embedded-db becomes KSEMBEDDED for Oracle, and ksembedded for MySQL If <code>schema</code> is supplied, the
     * supplied value will be used instead of the value derived from the artifactId
     *
     * @parameter expression="${schema}"
     */
    private String schema;

    /**
     * Database driver classname. This parameter is optional, as the correct JDBC driver to use is detected from the
     * <code>url</code> in almost all cases (works for Oracle, MySQL, Derby, PostGresSQL, DB2, H2, HSQL, SQL Server). If
     * a driver is explicitly supplied, it will be used in place of the JDBC driver the automatic detection logic would
     * have chosen.
     *
     * @parameter expression="${driver}"
     */
    private String driver;

    /**
     * The connect URL of the database.
     *
     * @parameter expression="${url}"
     * @required
     */
    private String url;

    /**
     * The user name to connect to the database. If not specified this is automatically generated by platform specific
     * logic for converting the artifact id.<br>
     * <br>
     * For example:<br>
     * ks-embedded-db becomes KSEMBEDDED for Oracle and ksembedded for MySQL
     *
     * @parameter expression="${username}"
     */
    private String username;

    /**
     * The password for the database user. If not specified this is automatically generated by platform specific logic
     * for converting the artifact id.<br>
     * <br>
     * For example:<br>
     * ks-embedded-db becomes KSEMBEDDED for Oracle and ksembedded for MySQL
     *
     * @parameter expression="${password}"
     */
    private String password;

    /**
     * Returns the fully qualified class name of the database driver.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Sets the fully qualified class name of the database driver.
     *
     * @param driver
     *            the fully qualified class name of the database driver.
     */
    public void setDriver(final String driver) {
        this.driver = driver;
    }

    /**
     * Returns the password of the database user.
     *
     * @return the password of the database user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the database user.
     *
     * @param password
     *            the password of the database user.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Returns the connect URL to the database.
     *
     * @return the connect URL to the database.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the connect URL to the database.
     *
     * @param url
     *            the connect URL to the database.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(final String schema) {
        this.schema = schema;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(final String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(final String includePatterns) {
        this.includes = includePatterns;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(final String excludePatterns) {
        this.excludes = excludePatterns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    protected String getUpdatedComment() {
        PluginDescriptor descriptor = (PluginDescriptor) this.getPluginContext().get("pluginDescriptor");
        if (descriptor == null) {
            // This is null for Maven 2.x
            return " Auto-generated by the maven-impex-plugin " + getComment();
        }
        String name = descriptor.getName();
        String version = descriptor.getVersion();
        String comment = " Auto-generated by the " + name;
        if (isIncludeVersionInComment()) {
            comment += " v" + version + " ";
        }
        if (!StringUtils.isEmpty(getComment())) {
            comment += getComment();
        }
        return comment;
    }

    @Override
    protected void configureTask() throws MojoExecutionException {
        setComment(getUpdatedComment());
        try {
            JdbcConfigurer configurer = new JdbcConfigurer();
            configurer.updateConfiguration(this);
            configurer.validateConfiguration(this);
        } catch (PropertyHandlingException e) {
            throw new MojoExecutionException("Error handling properties", e);
        }
        super.configureTask();
        DumpTask task = (DumpTask) super.getAntTask();
        task.setIncludePatterns(StringFilter.getListFromCSV(getIncludes()));
        task.setExcludePatterns(StringFilter.getListFromCSV(getExcludes()));
        task.setDriverProperties(driverProperties);
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(final String artifactId) {
        this.artifactId = artifactId;
    }

    public boolean isAntCompatibilityMode() {
        return antCompatibilityMode;
    }

    public void setAntCompatibilityMode(final boolean antCompatibilityMode) {
        this.antCompatibilityMode = antCompatibilityMode;
    }

    /**
     * @return the includeVersionInComment
     */
    public boolean isIncludeVersionInComment() {
        return includeVersionInComment;
    }

    /**
     * @param includeVersionInComment
     *            the includeVersionInComment to set
     */
    public void setIncludeVersionInComment(final boolean includeVersionInComment) {
        this.includeVersionInComment = includeVersionInComment;
    }

    public Properties getDriverProperties() {
        return driverProperties;
    }

    public void setDriverProperties(Properties driverProperties) {
        this.driverProperties = driverProperties;
    }
}

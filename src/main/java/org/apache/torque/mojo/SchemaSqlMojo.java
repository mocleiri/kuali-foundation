package org.apache.torque.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.torque.util.ChangeDetector;
import org.kuali.core.db.torque.KualiTorqueSQLTask;
import org.kuali.core.db.torque.StringFilter;

/**
 * Generates platform specific SQL from database agnostic XML files.<br>
 * <br>
 * There are two types of SQL files created by this goal:<br>
 * <br>
 * Type 1: DDL statements for creating tables, primary keys, indexes, unique constraints, sequences, and views. Does not
 * contain DDL for enforcing relationships between tables.<br>
 * Type 2: DDL statements for creating and enforcing relationships between tables<br>
 * <br>
 * This allows data to be imported into multiple tables concurrently. Running the first type of SQL file will create the
 * empty tables without any foreign key constraints. Data can then be loaded concurrently into the tables (using
 * optimized high speed tools if desired) without needing to worry about the order in which the tables are loaded. After
 * data has been loaded, the second type of SQL file can be run to add the relationships between the tables. As long as
 * the data set is consistent and correct, all the relationships will get created correctly.<br>
 * <br>
 * The database platform to generate SQL for is determined by ${targetDatabase}. See also <code>impex:datasql</code>
 * 
 * @goal schemasql
 * @phase generate-sources
 */
public class SchemaSqlMojo extends SqlMojoBase {

    /**
     * Comma delimited list of regular expressions for view names to include
     * 
     * @parameter property="viewIncludes" expression="${viewIncludes}"
     */
    private String viewIncludes;

    /**
     * Comma delimited list of regular expressions for view names to exclude
     * 
     * @parameter property="viewExcludes" expression="${viewExcludes}"
     */
    private String viewExcludes;

    /**
     * Comma delimited list of regular expressions for sequence names to include
     * 
     * @parameter property="sequenceIncludes" expression="${sequenceIncludes}"
     */
    private String sequenceIncludes;

    /**
     * Comma delimited list of regular expressions for sequence names to exclude
     * 
     * @parameter property="sequenceExcludes" expression="${sequenceExcludes}"
     */
    private String sequenceExcludes;
    /**
     * Comma delimited list of regular expressions for table names to include
     * 
     * @parameter property="tableIncludes" expression="${tableIncludes}"
     */
    private String tableIncludes;

    /**
     * Comma delimited list of regular expressions for table names to exclude
     * 
     * @parameter property="tableExcludes" expression="${tableExcludes}"
     */
    private String tableExcludes;

    /**
     * The directory in which the SQL will be generated.
     * 
     * @parameter property="outputDir" expression="${outputDir}" default-value="${project.build.directory}/classes/sql"
     */
    @SuppressWarnings("unused")
    private String dummy1;

    /**
     * The location where the report file will be generated.
     * 
     * @parameter property="reportFile" expression="${reportFile}" default-value=
     *            "../../../reports/report.${project.artifactId}.sql.generation"
     */
    @SuppressWarnings("unused")
    private String dummy2;

    /**
     * The location where the context property file for velocity will be generated.
     * 
     * @parameter property="contextPropertiesPath" expression="${contextPropertiesPath}"
     *            default-value="${project.build.directory}/reports/context.sql.properties"
     */
    @SuppressWarnings("unused")
    private String dummy3;

    /**
     * The suffix of the generated sql files.
     * 
     * @parameter property="suffix" expression="${suffix}"
     */
    @SuppressWarnings("unused")
    private String dummy4;

    protected void showConfig() {
        getLog().info("Schema Dir: " + getSchemaDir());
        getLog().info("Includes: " + getSchemaIncludes());
        getLog().info("Excludes: " + getSchemaExcludes());
        getLog().info("Table Includes: " + getTableIncludes());
        getLog().info("Table Excludes: " + getTableExcludes());
        getLog().info("View Includes: " + getViewIncludes());
        getLog().info("View Excludes: " + getViewExcludes());
        getLog().info("Sequence Includes: " + getSequenceIncludes());
        getLog().info("Sequence Excludes: " + getSequenceExcludes());
    }

    /**
     * Generate SQL from schema XML files
     */
    @Override
    public void executeMojo() throws MojoExecutionException {
        updateConfiguration();
        validateConfiguration();
        generateContextProperties();
        configureTask();
        KualiTorqueSQLTask task = (KualiTorqueSQLTask) super.getGeneratorTask();
        task.setTblIncludes(StringFilter.getListFromCSV(tableIncludes));
        task.setTblExcludes(StringFilter.getListFromCSV(tableExcludes));
        task.setvIncludes(StringFilter.getListFromCSV(viewIncludes));
        task.setvExcludes(StringFilter.getListFromCSV(viewExcludes));
        task.setsIncludes(StringFilter.getListFromCSV(sequenceIncludes));
        task.setsExcludes(StringFilter.getListFromCSV(sequenceExcludes));
        addTargetDatabaseToOutputDir();
        addTargetDatabaseToReportFile();
        showConfig();
        ChangeDetector detector = new ChangeDetector(getCanonicalReportFile(), getSchemaFiles());
        if (!detector.isChanged() && isRunOnlyOnSchemaChange()) {
            getLog().info("Schema has not changed.  Skipping generation");
            return;
        }
        getLog().info("------------------------------------------------------------------------");
        getLog().info("Generating SQL for " + getTargetDatabase() + " from schema XML files");
        getLog().info("------------------------------------------------------------------------");
        getAntTask().execute();
    }

    public String getTableIncludes() {
        return tableIncludes;
    }

    public void setTableIncludes(String tableIncludes) {
        this.tableIncludes = tableIncludes;
    }

    public String getTableExcludes() {
        return tableExcludes;
    }

    public void setTableExcludes(String tableExcludes) {
        this.tableExcludes = tableExcludes;
    }

    public String getViewIncludes() {
        return viewIncludes;
    }

    public void setViewIncludes(String viewIncludes) {
        this.viewIncludes = viewIncludes;
    }

    public String getSequenceIncludes() {
        return sequenceIncludes;
    }

    public void setSequenceIncludes(String sequenceIncludes) {
        this.sequenceIncludes = sequenceIncludes;
    }

    public String getViewExcludes() {
        return viewExcludes;
    }

    public void setViewExcludes(String viewExcludes) {
        this.viewExcludes = viewExcludes;
    }

    public String getSequenceExcludes() {
        return sequenceExcludes;
    }

    public void setSequenceExcludes(String sequenceExcludes) {
        this.sequenceExcludes = sequenceExcludes;
    }
}

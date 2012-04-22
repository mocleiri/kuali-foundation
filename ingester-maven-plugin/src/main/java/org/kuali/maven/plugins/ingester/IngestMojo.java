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
import org.kuali.rice.kew.batch.XmlPollerServiceImpl;

/**
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
     * The type of database documents are being ingested into (mysql, oracle, etc)
     *
     * @parameter expression="${ingester.targetDatabase}"
     * @required
     */
    private String targetDatabase;

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
     * @parameter expression="${ingester.propsKey}" default-value="ingester.config.location"
     */
    private String propsKey;

    /**
     * @parameter expression="${ingester.propsLoc}"
     *            default-value="${project.build.directory}/ingester/config/ingester.properties"
     */
    private String propsLoc;

    /**
     * Inclusion patterns. By default *.xml is included
     *
     * @parameter
     */
    private String[] includes = { "**/*.xml" };

    /**
     * Exclusion patterns.
     *
     * @parameter
     */
    private String[] excludes;

    @Override
    public void execute() throws MojoExecutionException {
        List<File> files = getFiles();
        if (files.size() == 0) {
            getLog().info("Skipping execution.  No matching files found");
            return;
        } else {
            getLog().info("Located " + files.size() + " documents to ingest");
        }

        DirectoryStructure ds = getDirectoryStructure();
        prepareFileSystem(ds, files);
        prepareProperties();
        ingest(ds);
    }

    protected void prepareProperties() throws MojoExecutionException {
        String value = System.getProperty(propsKey);
        if (!StringUtils.isBlank(value)) {
            // They have provided a location to pull properties from.
            // We will do nothing further. They are on their own to make sure the configuration is correct
            return;
        }
        System.setProperty(propsKey, propsLoc);
        Properties properties = new Properties();
        properties.setProperty("app.namespace", namespace);
        File file = new File(propsLoc);
        try {
            getLog().info("Creating " + file);
            PropertiesUtils.storeProperties(properties, file);
        } catch (IOException e) {
            throw new MojoExecutionException("Error storing properties", e);
        }
    }

    protected void ingest(DirectoryStructure ds) throws MojoExecutionException {
        SpringContextForWorkflowImporter.initializeApplicationContext();
        XmlPollerServiceImpl parser = new XmlPollerServiceImpl();
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

    protected void prepareFileSystem(DirectoryStructure ds, List<File> files) throws MojoExecutionException {
        try {
            mkdirs(ds);
            copyToDir(ds.getPendingDir(), files);
        } catch (IOException e) {
            throw new MojoExecutionException("Error preparing directory structure", e);
        }
    }

    protected DirectoryStructure getDirectoryStructure() {
        DirectoryStructure ds = new DirectoryStructure();
        ds.setPendingDir(new File(workingDir.getAbsolutePath() + File.separatorChar + "pending"));
        ds.setCompletedDir(new File(workingDir.getAbsolutePath() + File.separatorChar + "completed"));
        ds.setProblemDir(new File(workingDir.getAbsolutePath() + File.separatorChar + "problem"));
        return ds;
    }

    protected void copyToDir(File dir, List<File> files) throws IOException {
        int size = (files.size() + "").length();
        int sequence = 0;
        for (File file : files) {
            sequence++;
            String prefix = StringUtils.leftPad(sequence + "", size, "0");
            String filename = dir.getAbsolutePath() + File.separatorChar + prefix + "-" + file.getName();
            FileUtils.copyFile(file, new File(filename));
        }
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

    public String getPropsLoc() {
        return propsLoc;
    }

    public void setPropsLoc(String propsLoc) {
        this.propsLoc = propsLoc;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

}

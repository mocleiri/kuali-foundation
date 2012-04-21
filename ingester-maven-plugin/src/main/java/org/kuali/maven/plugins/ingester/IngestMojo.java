package org.kuali.maven.plugins.ingester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.rice.kew.batch.XmlPollerServiceImpl;

/**
 * @goal ingest
 */
public class IngestMojo extends AbstractMojo {

    /**
     * The directory containing documents to ingest
     *
     * @parameter expression="${ingester.sourceDir}" default-value="${project.basedir}/src/main/resources/workflow"
     */
    private File sourceDir;

    /**
     * The working directory where documents are copied and transition from pending to either completed or failed.
     *
     * @parameter expression="${ingester.outputDir}" default-value="${project.build.directory}/ingester"
     */
    private File outputDir;

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
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<File> files = getFileList();
        if (files.size() == 0) {
            getLog().info("Skipping execution.  No matching files found");
        } else {
            getLog().info("Ingesting " + files.size() + " documents");
        }
        DirectoryStructure ds = getDirectoryStructure();
        prepareDirs(ds, files);

        SpringContextForWorkflowImporter.initializeApplicationContext();
        XmlPollerServiceImpl parser = new XmlPollerServiceImpl();
        parser.setXmlPendingLocation(ds.getPendingDir().getAbsolutePath());
        parser.setXmlCompletedLocation(ds.getCompletedDir().getAbsolutePath());
        parser.setXmlProblemLocation(ds.getProblemDir().getAbsolutePath());
        parser.run();
    }

    protected void prepareDirs(DirectoryStructure ds, List<File> files) throws MojoExecutionException {
        try {
            mkdirs(ds);
            copyToDir(ds.getPendingDir(), files);
        } catch (IOException e) {
            throw new MojoExecutionException("Error preparing directory structure", e);
        }
    }

    protected DirectoryStructure getDirectoryStructure() {
        DirectoryStructure ds = new DirectoryStructure();
        ds.setPendingDir(new File(outputDir.getAbsolutePath() + File.pathSeparator + "pending"));
        ds.setCompletedDir(new File(outputDir.getAbsolutePath() + File.pathSeparator + "completed"));
        ds.setProblemDir(new File(outputDir.getAbsolutePath() + File.pathSeparator + "problem"));
        return ds;
    }

    protected void copyToDir(File dir, List<File> files) throws IOException {
        for (File file : files) {
            FileUtils.copyFile(file, new File(dir.getAbsolutePath() + File.pathSeparator + file.getName()));
        }
    }

    protected void mkdirs(DirectoryStructure ds) throws IOException {
        FileUtils.forceMkdir(ds.getPendingDir());
        FileUtils.forceMkdir(ds.getCompletedDir());
        FileUtils.forceMkdir(ds.getProblemDir());
    }

    /**
     * Return the list of files we need to inspect for BOM's
     */
    protected List<File> getFileList() {
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

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

}

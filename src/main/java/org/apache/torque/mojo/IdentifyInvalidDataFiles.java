/**
 * Copyright 2004-2014 The Kuali Foundation
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.kuali.core.db.torque.SetUtils;
import org.kuali.core.db.torque.Utils;

/**
 * This mojo identifies data files that are present on the file system but are not present in schema.xml. This can
 * happen if a table is removed from the schema.
 * 
 * It sets a project property called "impex.data.invalid". This property is a comma delimited list of filenames that
 * have no match in the db schema.
 * 
 * If it finds any invalid files it will also set the project property "impex.found.invalid=true"
 * 
 * @goal id-invalid-data-files
 */
public class IdentifyInvalidDataFiles extends BaseMojo {
    private static final String FS = System.getProperty("file.separator");

    /**
     * @parameter expression="${extension}" default-value=".xml"
     * @required
     */
    private String extension;

    /**
     * @parameter expression="${dataDir}" default-value="${project.basedir}/src/main/impex"
     * @required
     */
    private File dataDir;

    /**
     * @parameter expression="${dataDirIncludes}" default-value="*.xml"
     */
    private String dataDirIncludes;

    /**
     * @parameter expression="${dataDirExcludes}" default-value="schema.xml"
     */
    private String dataDirExcludes;

    /**
     * @parameter expression="${schemaXMLFile}" default-value="src/main/impex/schema.xml"
     */
    private String schemaXMLFile;

    /**
     * @parameter expression="${targetDatabase}" default-value="oracle"
     */
    private String targetDatabase;

    /**
     * Any invalid files are listed in this file. One file per line
     * 
     * @parameter expression="${impex.markedForRemoval}" default-value="${project.build.directory}/impex/invalid.txt"
     */
    private File markedForRemoval;

    protected Set<File> getExportedFiles() throws MojoExecutionException {
        String filename = getProject().getBuild().getDirectory() + "/impex/exported-tables.txt";
        File file = new File(filename);
        if (!file.exists()) {
            getLog().warn("Could not locate " + file.getAbsolutePath());
            return new HashSet<File>();
        }
        List<String> tablenames = getContents(file);
        return getExportedFiles(tablenames);
    }

    protected Set<File> getExportedFiles(List<String> tablenames) {
        Set<File> files = new TreeSet<File>();
        for (String tablename : tablenames) {
            String filename = dataDir.getAbsolutePath() + FS + tablename + extension;
            File file = new File(filename);
            files.add(file);
        }
        return files;
    }

    @SuppressWarnings("unchecked")
    protected List<String> getContents(File file) throws MojoExecutionException {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return IOUtils.readLines(in);
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected error", e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    @Override
    protected void executeMojo() throws MojoExecutionException, MojoFailureException {
        Utils utils = new Utils();
        try {
            getLog().info("Examining " + dataDir.getAbsolutePath());
            Database db = utils.getDatabase(schemaXMLFile, targetDatabase);
            DirectoryScanner ds = getDirectoryScanner();
            Set<File> existing = getExistingFiles(ds);
            Set<File> exported = getExportedFiles();
            Set<File> allowed = getDatabaseFiles(db);
            if (exported.size() > 0) {
                allowed = exported;
            }
            Set<File> invalid = SetUtils.difference(existing, allowed);
            getLog().info(existing.size() + " data files currently exist");
            getLog().info(invalid.size() + " of those are invalid");
            StringBuilder sb = new StringBuilder();
            int count = 0;
            StringBuilder invalidFiles = new StringBuilder();
            for (File file : invalid) {
                if (count != 0) {
                    sb.append(",");
                }
                sb.append("**/src/main/impex/" + file.getName());
                invalidFiles.append(file.getCanonicalPath() + "\n");
                getLog().info("Marked for removal: " + file.getName());
                count++;
            }
            Properties properties = getProject().getProperties();
            properties.setProperty("impex.data.invalid", sb.toString());
            if (count > 0) {
                properties.setProperty("impex.found.invalid", Boolean.TRUE.toString());
                createFile(markedForRemoval, invalidFiles.toString());
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Error executing mojo", e);
        }
    }

    protected void createFile(File file, String contents) throws IOException {
        OutputStream out = null;
        try {
            out = FileUtils.openOutputStream(file);
            IOUtils.write(contents, out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    protected Set<File> getDatabaseFiles(Database db) {
        List<?> tables = db.getTables();
        Set<File> files = new TreeSet<File>();
        for (Object object : tables) {
            Table table = (Table) object;
            String tableName = table.getName();
            String filename = dataDir.getAbsolutePath() + FS + tableName + extension;
            File file = new File(filename);
            files.add(file);
        }
        return files;
    }

    protected Set<File> getExistingFiles(DirectoryScanner ds) {
        ds.scan();
        String[] relativeFilenames = ds.getIncludedFiles();
        Set<File> files = new TreeSet<File>();
        for (int i = 0; i < relativeFilenames.length; i++) {
            String filename = ds.getBasedir().getAbsolutePath() + FS + relativeFilenames[i];
            File file = new File(filename);
            files.add(file);
        }
        return files;
    }

    protected DirectoryScanner getDirectoryScanner() {
        DirectoryScanner ds = new DirectoryScanner();
        ds.setBasedir(dataDir);
        ds.setIncludes(new String[] { dataDirIncludes });
        ds.setExcludes(new String[] { dataDirExcludes });
        return ds;
    }

    protected File getFile(Table table) {
        String tableName = table.getName();
        String filename = dataDir.getAbsolutePath() + FS + tableName + extension;
        File file = new File(filename);
        return file;
    }

    public File getDataDir() {
        return dataDir;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    public String getDataDirIncludes() {
        return dataDirIncludes;
    }

    public void setDataDirIncludes(String dataDirIncludes) {
        this.dataDirIncludes = dataDirIncludes;
    }

    public String getDataDirExcludes() {
        return dataDirExcludes;
    }

    public void setDataDirExcludes(String dataDirExcludes) {
        this.dataDirExcludes = dataDirExcludes;
    }

    public String getSchemaXMLFile() {
        return schemaXMLFile;
    }

    public void setSchemaXMLFile(String schemaXMLFile) {
        this.schemaXMLFile = schemaXMLFile;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public File getMarkedForRemoval() {
        return markedForRemoval;
    }

    public void setMarkedForRemoval(File markedForRemoval) {
        this.markedForRemoval = markedForRemoval;
    }

}

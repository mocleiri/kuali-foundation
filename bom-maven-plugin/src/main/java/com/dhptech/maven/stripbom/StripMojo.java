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
package com.dhptech.maven.stripbom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Goal to detect and strip BOM's from files.
 *
 * @goal strip
 * @phase process-sources
 * @requiresProject false
 */
public class StripMojo extends AbstractMojo {

    protected static final byte[] UTF8_BOM = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
    protected static final byte[] UTF16_BIG_ENDIAN_BOM = new byte[] { (byte) 0xFE, (byte) 0xFF };
    protected static final byte[] UTF16_LITTLE_ENDIAN_BOM = new byte[] { (byte) 0xFF, (byte) 0xFE };

    /**
     * Directory where a backup copy of any file having its BOM removed is created
     *
     * @parameter expression="${bom.workingDir}" default-value="${project.build.directory}/bom"
     */
    private File workingDir;

    /**
     * The directory to recursively scan for files containing BOM's
     *
     * @parameter expression="${bom.directory}" default-value="${project.basedir}"
     */
    private File directory;

    /**
     * Inclusion patterns. By default, all files are included
     *
     * @parameter
     */
    private String[] includes;

    /**
     * Exclusion patterns. By default no files (other than those matching the default excludes) are excluded
     *
     * @parameter
     */
    private String[] excludes;

    /**
     * Flag indicating whether or not to use the default exclude patterns. These are known patterns like
     * <code>&#042;&#042;/.svn/&#042;</code> and <code>&#042;&#042;/&#042;.jar</code> See the
     * <code>DEFAULT_EXCLUDES</code> field in the <code>SimpleScanner</code> class for the exact list of default
     * excludes.
     *
     * @parameter expression="${bom.useDefaultExcludes}" default-value="true"
     */
    private boolean useDefaultExcludes;

    /**
     * If true, any file where a BOM is detected has its BOM stripped out.
     *
     * @parameter expression="${bom.strip}" default-value="true"
     */
    private boolean strip = true;

    /**
     * If true, the Maven build will fail if a BOM is detected
     *
     * @parameter expression="${bom.failBuild}" default-value="false"
     */
    private boolean failBuild = true;

    /**
     *
     */
    public boolean isStrip() {
        return strip;
    }

    /**
     *
     */
    public void setStrip(boolean strip) {
        this.strip = strip;
    }

    /**
     * True if the build should fail when BOMs are found in any of the files processed.
     *
     * @return the value of the failBuild property.
     */
    public boolean isFailBuild() {
        return failBuild;
    }

    /**
     * Sets the failBuild property.
     *
     * @see #isFailBuild
     * @param failBuild
     *            the new value for the failBuild property
     */
    public void setFailBuild(boolean failBuild) {
        this.failBuild = failBuild;
    }

    /**
     * Return true if the file begins with the same sequence of bytes as the BOM passed in
     */
    protected boolean containsBom(File file, byte[] bom) throws IOException {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[bom.length];
            in.read(bytes);
            return Arrays.equals(bytes, bom);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * Return -1 if the file does not begin with a BOM, otherwise return the index of the BOM the file starts with
     */
    protected int containsBom(File file, List<byte[]> boms) throws IOException {
        for (int i = 0; i < boms.size(); i++) {
            if (containsBom(file, boms.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return the list of files we need to inspect for BOM's
     */
    protected List<File> getFileList() {
        SimpleScanner scanner = new SimpleScanner(directory, includes, excludes, useDefaultExcludes);
        String[] filenames = scanner.getSelectedFiles();
        List<File> fileList = new ArrayList<File>();
        for (String filename : filenames) {
            File file = new File(filename);
            fileList.add(file);
        }
        return fileList;
    }

    /**
     * Return the list of known BOM's we need to look for
     */
    protected List<byte[]> getBoms() {
        List<byte[]> boms = new ArrayList<byte[]>();
        boms.add(UTF8_BOM);
        boms.add(UTF16_LITTLE_ENDIAN_BOM);
        boms.add(UTF16_BIG_ENDIAN_BOM);
        return boms;
    }

    /**
     * Create BomMarker objects for any files that start with a bom
     */
    protected List<BomMarker> getBomMarkers(List<File> fileList, List<byte[]> boms) throws IOException {
        List<BomMarker> bomMarkers = new ArrayList<BomMarker>();
        for (File file : fileList) {
            getLog().debug("Examining " + file.getAbsolutePath());
            int index = containsBom(file, boms);
            if (index != -1) {
                logBomFound(file);
                int skipBytes = boms.get(index).length;
                BomMarker bm = new BomMarker();
                bm.setFile(file);
                bm.setSkipBytes(skipBytes);
                bomMarkers.add(bm);
            }
        }
        return bomMarkers;
    }

    protected void logBomFound(File file) {
        if (failBuild) {
            getLog().error("BOM located in " + file.getAbsolutePath());
        } else {
            if (strip) {
                getLog().info("BOM located in " + file.getAbsolutePath());
            } else {
                getLog().warn("BOM located in " + file.getAbsolutePath());
            }
        }
    }

    /**
     * Check for BOM's
     *
     * @throws MojoExecutionException
     */
    @Override
    public void execute() throws MojoExecutionException {
        try {
            List<byte[]> boms = getBoms();
            List<File> fileList = getFileList();
            getLog().info("Examining " + fileList.size() + " files for BOM's");
            List<BomMarker> bomMarkers = getBomMarkers(fileList, boms);
            if (bomMarkers.size() == 0) {
                getLog().info("No BOM's detected");
            }
            if (strip && bomMarkers.size() > 0) {
                getLog().info("Removing BOM's from " + bomMarkers.size() + " files");
                stripBoms(bomMarkers);
            }
            if (failBuild && bomMarkers.size() > 0) {
                throw new MojoExecutionException(bomMarkers.size() + " BOM's were detected");
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Unexpected IO error", e);
        }
    }

    protected void stripBoms(List<BomMarker> bomMarkers) throws IOException {
        FileUtils.forceMkdir(workingDir);
        int count = 1;
        for (BomMarker bomMarker : bomMarkers) {
            stripBom(bomMarker, count++);
        }
    }

    protected void stripBom(BomMarker bm, int count) throws IOException {
        getLog().info(count + " --------");
        File backup = File.createTempFile(bm.getFile().getName() + ".", ".bak", workingDir);
        getLog().info("Creating " + backup.getAbsolutePath());
        FileUtils.copyFile(bm.getFile(), backup);
        byte[] bytes = FileUtils.readFileToByteArray(bm.getFile());
        int length = bytes.length - bm.getSkipBytes();
        byte[] newBytes = new byte[length];
        System.arraycopy(bytes, bm.getSkipBytes(), newBytes, 0, newBytes.length);
        getLog().info("Rewriting " + bm.getFile().getAbsolutePath());
        FileUtils.writeByteArrayToFile(bm.getFile(), newBytes);
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File basedir) {
        this.directory = basedir;
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

    public boolean isUseDefaultExcludes() {
        return useDefaultExcludes;
    }

    public void setUseDefaultExcludes(boolean useDefaultExcludes) {
        this.useDefaultExcludes = useDefaultExcludes;
    }
}

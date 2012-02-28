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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Goal to detect and strip BOMs from files.
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
     * Location of a single file to strip the BOM from.
     *
     * @parameter expression="${project.basedir}"
     */
    private File basedir;

    /**
     * Inclusion patterns. By default, all files are included
     *
     * @parameter
     */
    private String[] includes;

    /**
     * Exclusion patterns. By default nothing is excluded
     *
     * @parameter
     */
    private String[] excludes;

    /**
     * Use default excludes
     *
     * @parameter expression="${bom.useDefaultExcludes}" default-value="true"
     */
    private boolean useDefaultExcludes;

    /**
     * Set to true if you want to remove any BOM's detected by the plugin
     *
     * @parameter expression="${bom.strip}" default-value="false"
     */
    private boolean strip = false;

    /**
     * Set to true if you want the build to fail if a BOM is found.
     *
     * @parameter expression="${bom.failBuild}" default-value="false"
     */
    private boolean failBuild = true;

    /**
     * True if we should only warn about BOMs and not actually strip them.
     *
     * @return true if warnOnly is true.
     */
    public boolean isStrip() {
        return strip;
    }

    /**
     * Set to true to warn only on finding a BOM.
     *
     * @param warnOnly
     *            true if we should only warn when finding a BOM.
     */
    public void setStrip(boolean warnOnly) {
        this.strip = warnOnly;
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
        SimpleScanner scanner = new SimpleScanner(basedir, includes, excludes, useDefaultExcludes);
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
            if (index == -1) {
                continue;
            } else {
                if (failBuild) {
                    getLog().error("BOM located in " + file.getAbsolutePath());
                } else {
                    getLog().warn("BOM located in " + file.getAbsolutePath());
                }
                int skipBytes = boms.get(index).length;
                BomMarker bm = new BomMarker();
                bm.setFile(file);
                bm.setSkipBytes(skipBytes);
                bomMarkers.add(bm);
            }
        }
        return bomMarkers;
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
            if (strip && bomMarkers.size() > 0) {
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
        File backup = File.createTempFile(bm.getFile().getName() + ".", ".bak", workingDir);
        getLog().info(count + " --------");
        getLog().info("Backing up " + bm.getFile().getAbsolutePath());
        getLog().info("Creating " + backup.getAbsolutePath());
        FileUtils.copyFile(bm.getFile(), backup);
        byte[] bytes = FileUtils.readFileToByteArray(bm.getFile());
        int length = bytes.length - bm.getSkipBytes();
        byte[] newBytes = new byte[length];
        System.arraycopy(bytes, bm.getSkipBytes(), newBytes, 0, newBytes.length);
        getLog().info("Rewriting " + bm.getFile().getAbsolutePath());
        FileUtils.writeByteArrayToFile(bm.getFile(), newBytes);
        backup.delete();
        getLog().info("Removing " + backup.getAbsolutePath());
    }

    /**
     * Strips the BOM, if found from a single file.
     *
     * @param file
     *            the file to remove the BOM from, if it is there.
     *
     * @throws IOException
     * @throws MojoExecutionException
     */
    protected boolean stripBOM(File file) throws IOException, MojoExecutionException {
        if (getLog().isDebugEnabled()) {
            getLog().debug("Checking for BOM in " + file);
        }
        InputStream in = new FileInputStream(file);
        byte[] bom = new byte[3];
        in.read(bom);
        if (Arrays.equals(bom, UTF8_BOM)) {
            if (getLog().isWarnEnabled()) {
                getLog().warn("Found BOM in " + file + ", " + (strip ? "not " : "") + "removing.");
            }
            if (strip) {
                return true; // BOM Found
            }

            File tempFile = File.createTempFile(file.getName(), ".tmp", file.getParentFile());
            OutputStream out = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int cnt = -1;
            while ((cnt = in.read(buffer)) >= 0) {
                out.write(buffer, 0, cnt);
            }
            out.close();
            File backupFile = File.createTempFile(file.getName(), ".bak", file.getParentFile());
            if (!file.renameTo(backupFile)) {
                throw new MojoExecutionException("Could not rename target file, " + file + ", to backup file, "
                        + backupFile);
            }
            if (!tempFile.renameTo(file)) {
                if (!backupFile.renameTo(file)) {
                    getLog().error("Could not undo rename of backup file, " + backupFile + ", to target file, " + file);
                }
                throw new MojoExecutionException("Could not rename temp file, " + tempFile + ", to target file, "
                        + file);
            }
            backupFile.delete();
            return true; // BOM Found
        }
        return false; // No BOM Found
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public File getBasedir() {
        return basedir;
    }

    public void setBasedir(File basedir) {
        this.basedir = basedir;
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

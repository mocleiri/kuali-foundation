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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.shared.model.fileset.FileSet;
import org.apache.maven.shared.model.fileset.util.FileSetManager;

/**
 * Goal to detect and strip BOMs from files.
 *
 * @goal bom
 * @phase process-sources
 * @requiresProject false
 */
public class ByteOrderMarkMojo extends AbstractMojo {

    protected static final byte[] UTF8_BOM = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
    protected static final byte[] UTF16_BIG_ENDIAN_BOM = new byte[] { (byte) 0xFE, (byte) 0xFF };
    protected static final byte[] UTF16_LITTLE_ENDIAN_BOM = new byte[] { (byte) 0xFF, (byte) 0xFE };

    /**
     * Locations of a single file to strip the BOM from.
     *
     * @parameter expression="${file}"
     */
    private File file;

    /**
     * Locations of the files to strip BOMs.
     *
     * @parameter
     */
    private List<FileSet> files;

    /**
     * Set to true if you only want to issue a warning message when a file contains a BOM instead of altering the file
     * contents by removing the BOM.
     *
     * @parameter expression="${strip-bom.warnOnly}" default-value="true"
     */
    private boolean warnOnly = true;

    /**
     * Set to true if you want the build to fail if a BOM is found.
     *
     * @parameter expression="${strip-bom.failBuild}" default-value="true"
     */
    private boolean failBuild = true;

    /**
     * The list of fileSets that will specify the files to examine
     *
     * @return the file sets.
     */
    public List<FileSet> getFiles() {
        return files;
    }

    /**
     * Sets the file set list.
     *
     * @param files
     *            the file sets.
     */
    public void setFiles(List<FileSet> files) {
        this.files = files;
    }

    /**
     * Get the single file to strip.
     *
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the single file to strip.
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * True if we should only warn about BOMs and not actually strip them.
     *
     * @return true if warnOnly is true.
     */
    public boolean isWarnOnly() {
        return warnOnly;
    }

    /**
     * Set to true to warn only on finding a BOM.
     *
     * @param warnOnly
     *            true if we should only warn when finding a BOM.
     */
    public void setWarnOnly(boolean warnOnly) {
        this.warnOnly = warnOnly;
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

    protected boolean containsBom(File file, List<byte[]> boms) throws IOException {
        for (byte[] bom : boms) {
            if (containsBom(file, bom)) {
                return true;
            }
        }
        return false;
    }

    protected List<File> getFileList() {
        if (file != null) {
            List<File> fileList = new ArrayList<File>();
            fileList.add(file);
            return fileList;
        }
        List<File> fileList = new ArrayList<File>();
        FileSetManager fsm = new FileSetManager(getLog());
        Iterator<FileSet> fileSetIter = files.iterator();
        while (fileSetIter.hasNext()) {
            FileSet fileSet = fileSetIter.next();
            String[] fileNames = fsm.getIncludedFiles(fileSet);
            for (int f = 0; f < fileNames.length; f++) {
                String fileName = fileNames[f];
                File currFile = new File(fileSet.getDirectory(), fileName);
                fileList.add(currFile);
            }
        }
        return fileList;
    }

    protected List<byte[]> getBoms() {
        List<byte[]> boms = new ArrayList<byte[]>();
        boms.add(UTF8_BOM);
        boms.add(UTF16_LITTLE_ENDIAN_BOM);
        boms.add(UTF16_BIG_ENDIAN_BOM);
        return boms;
    }

    protected List<File> getBomFiles(List<File> fileList, List<byte[]> boms) throws MojoExecutionException {
        List<File> bomFiles = new ArrayList<File>();
        try {
            for (File file : fileList) {
                getLog().debug("Examining " + file);
                if (containsBom(file, boms)) {
                    getLog().warn("BOM located in " + file);
                    bomFiles.add(file);
                }
            }
            return bomFiles;
        } catch (IOException e) {
            throw new MojoExecutionException("Error when looking for BOM's", e);
        }
    }

    /**
     * Check for BOM's
     *
     * @throws MojoExecutionException
     */
    @Override
    public void execute() throws MojoExecutionException {
        List<byte[]> boms = getBoms();
        List<File> fileList = getFileList();
        getLog().info("Examining " + files.size() + " files for BOM's");
        List<File> bomFiles = getBomFiles(fileList, boms);
        if (failBuild && bomFiles.size() > 0) {
            throw new MojoExecutionException("BOM's were detected");
        }
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
    private boolean stripBOM(File file) throws IOException, MojoExecutionException {
        if (getLog().isDebugEnabled()) {
            getLog().debug("Checking for BOM in " + file);
        }
        InputStream in = new FileInputStream(file);
        byte[] bom = new byte[3];
        in.read(bom);
        if (Arrays.equals(bom, UTF8_BOM)) {
            if (getLog().isWarnEnabled()) {
                getLog().warn("Found BOM in " + file + ", " + (warnOnly ? "not " : "") + "removing.");
            }
            if (warnOnly) {
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
}

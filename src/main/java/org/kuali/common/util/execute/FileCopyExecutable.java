/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.execute;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SimpleScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * File copying executable that uses simple patterns to copy files from one directory to another
 *
 * @author andrewlubbers
 */
public class FileCopyExecutable implements Executable {

    private static final Logger logger = LoggerFactory.getLogger(FileCopyExecutable.class);

    private String sourceDir;
    private String filePatterns;
    private String destinationDir;

    @Override
    public void execute() {
        Assert.isTrue(LocationUtils.exists(sourceDir));
        Assert.notNull(destinationDir);
        Assert.notNull(filePatterns);

        String sourceDirPath = LocationUtils.getCanonicalPath(new File(sourceDir));

        logger.info("Starting File Copy");
        logger.info("From: " + sourceDirPath);
        logger.info("To: " + destinationDir);
        logger.info("Using patterns: " + filePatterns);

        List<String> patterns = CollectionUtils.getTrimmedListFromCSV(filePatterns);

        SimpleScanner scanner = new SimpleScanner();
        scanner.setBasedir(sourceDir);
        scanner.setIncludes(patterns.toArray(new String[patterns.size()]));

        List<File> sourceFiles = scanner.getFiles();
        logger.info("Found " + sourceFiles.size() + " matching source files.");


        List<String> sourceLocations = new ArrayList<String>(sourceFiles.size());
        List<File> destinationFiles = new ArrayList<File>(sourceFiles.size());
        for(File f : sourceFiles) {
            String sourcePath = LocationUtils.getCanonicalPath(f);
            sourceLocations.add(sourcePath);

            String destinationPath = sourcePath.replace(sourceDirPath, (destinationDir + File.separator));
            destinationFiles.add(new File(destinationPath));
        }

        LocationUtils.copyLocationsToFiles(sourceLocations, destinationFiles);

        logger.info("File Copy Complete");
    }

    public String getDestinationDir() {
        return destinationDir;
    }

    public void setDestinationDir(String destinationDir) {
        this.destinationDir = destinationDir;
    }

    public String getFilePatterns() {
        return filePatterns;
    }

    public void setFilePatterns(String filePatterns) {
        this.filePatterns = filePatterns;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }
}

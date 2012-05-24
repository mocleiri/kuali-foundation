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
package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RsyncTest {
    private static final Logger logger = LoggerFactory.getLogger(RsyncTest.class);

    @Test
    public void testGetTargetDirectoriesQuickly() {
        String target = "/target";
        File basedir = new File("/Users/jeffcaddel/ws/rice-2.1");
        String basedirPath = basedir.getAbsolutePath();
        long now = System.currentTimeMillis();
        List<File> files = getDirs(basedir, target, new DirectoryFileFilter());
        long elapsed = System.currentTimeMillis() - now;
        logger.info("Dirs " + files.size() + " Elapsed: " + elapsed);
        Set<String> targetDirectories = new TreeSet<String>();
        for (File file : files) {
            String path = file.getAbsolutePath();
            int pos = path.indexOf(target);
            String token = path.substring(basedirPath.length() + 1, pos + target.length());
            targetDirectories.add(token);
        }
        StringBuilder sb = new StringBuilder();
        for (String targetDirectory : targetDirectories) {
            sb.append(targetDirectory + "\n");
        }
        File rsyncExcludes = new File("/Users/jeffcaddel/ws/rsync-excludes");
        try {
            logger.info("Creating " + rsyncExcludes);
            FileUtils.write(rsyncExcludes, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Recursively scan the file system starting at <code>dir</code> and return a list of directories matching the
     * pattern passed in
     *
     * @param dir
     * @param pattern
     * @param filter
     * @return
     */
    protected List<File> getDirs(File dir, String pattern, DirectoryFileFilter filter) {
        List<File> fileList = new ArrayList<File>();
        String path = dir.getAbsolutePath();
        int pos = path.indexOf(pattern);
        if (pos == -1) {
            File[] files = dir.listFiles(filter);
            for (File file : files) {
                fileList.addAll(getDirs(file, pattern, filter));
            }
        } else {
            fileList.add(dir);
        }
        return fileList;
    }

    @Test
    public void testGetTargetDirectoriesViaScanning() {
        String target = "/target/";
        File basedir = new File("/Users/jeffcaddel/ws/rice-2.1");
        String basedirPath = basedir.getAbsolutePath();
        long now = System.currentTimeMillis();
        SimpleScanner ss = new SimpleScanner(basedir, "**" + target + "**", null);
        logger.info("Scanning " + basedir.getAbsolutePath());
        List<File> files = ss.getFiles();
        long elapsed = System.currentTimeMillis() - now;
        logger.info("Located " + files.size() + " unique files.  Elapsed: " + elapsed);
        Set<String> targetDirectories = new TreeSet<String>();
        for (File file : files) {
            String path = file.getAbsolutePath();
            int pos = path.indexOf(target);
            String token = path.substring(basedirPath.length() + 1, pos + target.length() - 1);
            targetDirectories.add(token);
        }
        logger.info("Located " + targetDirectories.size() + " unique target directories");
        for (String targetDirectory : targetDirectories) {
            logger.debug(targetDirectory);
        }
    }

}

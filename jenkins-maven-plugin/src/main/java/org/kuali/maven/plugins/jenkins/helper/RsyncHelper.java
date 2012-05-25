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
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RsyncHelper {
    private static final Logger logger = LoggerFactory.getLogger(RsyncHelper.class);

    /**
     * Recursively scan the file system starting at <code>dir</code> and return a list of directories matching the
     * pattern passed in
     *
     * @param dir
     * @param pattern
     * @param filter
     * @return
     */
    public List<File> getMatchingDirs(File basedir, File dir, String endsWithPattern, DirectoryFileFilter filter) {
        List<File> fileList = new ArrayList<File>();
        String path = dir.getAbsolutePath();
        boolean match = path.endsWith(File.separator + endsWithPattern);
        if (!match) {
            File[] dirs = dir.listFiles(filter);
            for (File newDir : dirs) {
                fileList.addAll(getMatchingDirs(basedir, newDir, endsWithPattern, filter));
            }
        } else {
            fileList.add(dir);
        }
        return fileList;
    }

    /**
     * Return a list of rsync friendly exclude patterns based on a base directory and directories underneath it that
     * should be excluded.
     *
     * @param basedir
     * @param dirs
     * @param pattern
     * @return
     */
    public List<String> getExcludesList(File basedir, List<File> excludeDirs) {
        String basedirPath = basedir.getAbsolutePath();
        List<String> excludes = new ArrayList<String>();
        for (File excludeDir : excludeDirs) {
            String path = excludeDir.getAbsolutePath();
            logger.debug("path=" + path);
            if (excludeDir.equals(basedir)) {
                continue;
            }
            logger.debug("basedirPath=" + basedirPath);
            String token = path.substring(basedirPath.length() + 1);
            excludes.add(token);
        }
        return excludes;
    }

}

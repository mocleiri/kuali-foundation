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
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RsyncHelperTest {
    private static final Logger logger = LoggerFactory.getLogger(RsyncHelperTest.class);
    RsyncHelper helper = new RsyncHelper();

    @Test
    public void testGetTargetDirectoriesQuickly() {
        File basedir = new File("/Users/jeffcaddel/ws/rice-2.1");
        long now = System.currentTimeMillis();
        List<File> excludeDirs = helper.getMatchingDirs(basedir, "/target", new DirectoryFileFilter());
        long elapsed = System.currentTimeMillis() - now;
        logger.info("Elapsed: " + elapsed);
        List<String> excludes = helper.getExcludesList(basedir, excludeDirs);
        StringBuilder sb = new StringBuilder();
        for (String exclude : excludes) {
            sb.append(exclude + "\n");
        }
        File rsyncExcludes = new File("/Users/jeffcaddel/ws/rsync-excludes");
        try {
            logger.info("Creating " + rsyncExcludes);
            FileUtils.write(rsyncExcludes, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

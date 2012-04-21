/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.ingester;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.kuali.rice.kew.batch.XmlPollerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkflowImporter {
    protected static final Logger LOG = LoggerFactory.getLogger(WorkflowImporter.class);

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            args = new String[1];
            args[0] = "/Users/jeffcaddel/sts/2.8.1/ws/ingester-maven-plugin/src/test/resources/workflow";
        }
        try {
            SpringContextForWorkflowImporter.initializeApplicationContext();

            XmlPollerServiceImpl parser = new XmlPollerServiceImpl();

            File baseDir = new File(args[0]);
            File[] dirs = baseDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory() && !pathname.getName().startsWith(".");
                }
            });
            if (dirs == null) {
                LOG.error("Unable to find any subdirectories under " + baseDir.getAbsolutePath() + " - ABORTING!");
                System.err.println("Unable to find any subdirectories under " + baseDir.getAbsolutePath()
                        + " - ABORTING!");
                return;
            }
            Arrays.sort(dirs);

            for (File dir : dirs) {
                LOG.info("Processing Directory: " + dir.getAbsolutePath());
                File[] xmlFiles = dir.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isFile() && pathname.getName().endsWith(".xml");
                    }
                });
                if (xmlFiles.length == 0) {
                    LOG.info("Directory was empty - skipping.");
                    continue;
                }
                File pendingDir = new File(dir, "pending");
                if (!pendingDir.exists()) {
                    pendingDir.mkdir();
                }
                File completedDir = new File(dir, "completed");
                if (!completedDir.exists()) {
                    completedDir.mkdir();
                }
                File failedDir = new File(dir, "problem");
                if (!failedDir.exists()) {
                    failedDir.mkdir();
                }

                Arrays.sort(xmlFiles);

                for (File xmlFile : xmlFiles) {
                    LOG.info("Copying to pending: " + xmlFile.getName());
                    FileUtils.copyFile(xmlFile, new File(pendingDir, xmlFile.getName()));
                }

                parser.setXmlPendingLocation(pendingDir.getAbsolutePath());
                parser.setXmlCompletedLocation(completedDir.getAbsolutePath());
                parser.setXmlProblemLocation(failedDir.getAbsolutePath());

                LOG.info("Reading XML files from     : " + pendingDir.getAbsolutePath());
                LOG.info("Completed Files will go to : " + completedDir.getAbsolutePath());
                LOG.info("Failed files will go to    : " + failedDir.getAbsolutePath());

                parser.run();
            }

            SpringContextForWorkflowImporter.close();
            return;
        } catch (Throwable t) {
            System.err.println("ERROR: Exception caught: ");
            t.printStackTrace(System.err);
            return;
        }
    }

}

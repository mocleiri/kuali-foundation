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
package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;

/**
 * Connect to a Jenkins server and create a Jenkins job
 *
 * @goal hackconfig
 * @threadSafe
 */
public class HackConfigMojo extends AbstractMojo {

    @Override
    public void execute() {
        try {
            List<File> files = getPinnedBuilds();
            getLog().info("Pinned Builds:" + files.size());
            // rewriteConfig(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void rewriteConfig(List<File> files) throws IOException {
        for (File f : files) {
            String content = FileUtils.readFileToString(f);
            String s = StringUtils.substringBetween(content, "<assignedNode>", "</assignedNode>");
            String searchString = "<assignedNode>" + s + "</assignedNode>";
            String replacement = "<assignedNode>master</assignedNode>";
            String newContent = StringUtils.replace(content, searchString, replacement);
            File bakFile = new File(f.getAbsolutePath() + ".bak");
            getLog().info("Creating " + bakFile.getAbsolutePath());
            FileUtils.writeStringToFile(bakFile, content);
            FileUtils.writeStringToFile(f, newContent);
        }
    }

    protected List<File> getPinnedBuilds() throws IOException {
        List<File> pinnedBuilds = new ArrayList<File>();

        String basedir = "/var/lib/jenkins/workspace";
        File file = new File(basedir);
        File[] files = file.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                continue;
            }
            File configFile = new File(f.getAbsolutePath() + "/config.xml");
            if (!configFile.exists()) {
                continue;
            }
            String content = FileUtils.readFileToString(configFile);
            int pos = content.indexOf(">master<");
            if (pos != -1) {
                pinnedBuilds.add(configFile);
                String s = StringUtils.substringBetween(content, "<assignedNode>", "</assignedNode>");
                getLog().info(f.getPath() + " " + s);
            }
        }

        return pinnedBuilds;

    }
}

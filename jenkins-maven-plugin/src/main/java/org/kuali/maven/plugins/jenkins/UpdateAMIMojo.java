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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.ResourceUtils;

/**
 * Update the AMI being used by the EC2 plugin inside config.xml for Jenkins
 *
 * @goal updateami
 */
public class UpdateAMIMojo extends AbstractMojo {
    ResourceUtils utils = new ResourceUtils();

    /**
     * @parameter expression="${jenkins.configFile}" default-value="/var/lib/jenkins/config.xml"
     * @required
     */
    private File configFile;

    /**
     * @parameter expression="${jenkins.newAmi}"
     * @required
     */
    private String newAmi;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            String oldContent = utils.read(configFile.getAbsolutePath());
            String open = "<hudson.plugins.ec2.SlaveTemplate>";
            String close = "</hudson.plugins.ec2.SlaveTemplate>";
            String slaveTemplate = StringUtils.substringBetween(oldContent, open, close);
            String oldAmi = StringUtils.substringBetween(slaveTemplate, "<ami>", "</ami>");
            String oldText = "<ami>" + oldAmi + "</ami>";
            String newText = "<ami>" + newAmi + "</ami>";
            String newContent = oldContent.replace(oldText, newText);
            File bak = new File(configFile.getAbsolutePath() + ".bak");
            getLog().info("Old AMI: " + oldAmi);
            getLog().info("New AMI: " + newAmi);
            getLog().info("Backing up original to " + bak.getAbsolutePath());
            getLog().info("Rewriting " + configFile.getAbsolutePath());
            FileUtils.write(bak, oldContent);
            FileUtils.write(configFile, newContent);
        } catch (Exception e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public String getNewAmi() {
        return newAmi;
    }

    public void setNewAmi(String newAmi) {
        this.newAmi = newAmi;
    }

}
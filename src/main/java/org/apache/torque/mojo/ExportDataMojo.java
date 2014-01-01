/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.core.db.torque.KualiTorqueDataDumpTask;

/**
 * Reads the content of tables from a database and exports the data in a database agnostic format to XML files.
 * 
 * @goal exportdata
 * @phase generate-sources
 */
public class ExportDataMojo extends ExportMojo {

    /**
     * The format to use for dates/timestamps
     * 
     * @parameter expression="${dateFormat}" default-value="yyyyMMddHHmmss z"
     * @required
     */
    private String dateFormat;

    /**
     * The directory where data XML files will be written
     * 
     * @parameter expression="${dataXMLDir}" default-value="${basedir}/src/main/impex"
     * @required
     */
    private File dataXMLDir;

    /**
     * Configure the Ant task
     */
    @Override
    protected void configureTask() throws MojoExecutionException {
        KualiTorqueDataDumpTask task = new KualiTorqueDataDumpTask();
        task.setBuildDirectory(new File(getProject().getBuild().getDirectory()));
        setAntTask(task);
        super.configureTask();
        makeOutputDir();
    }

    protected void makeOutputDir() throws MojoExecutionException {
        if (getDataXMLDir().exists()) {
            return;
        }
        try {
            FileUtils.forceMkdir(getDataXMLDir());
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating output directory", e);
        }
    }

    public File getDataXMLDir() {
        return dataXMLDir;
    }

    public void setDataXMLDir(File outputDir) {
        this.dataXMLDir = outputDir;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}

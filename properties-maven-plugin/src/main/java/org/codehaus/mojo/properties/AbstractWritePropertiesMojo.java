/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.codehaus.mojo.properties;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @author <a href="mailto:zarars@gmail.com">Zarar Siddiqi</a>
 * @version $Id: AbstractWritePropertiesMojo.java 8861 2009-01-21 15:35:38Z pgier $
 */
public abstract class AbstractWritePropertiesMojo extends AbstractMojo {

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    /**
     * The properties file that will be used when writing properties.
     * 
     * @parameter
     * @required
     */
    protected File outputFile;

    protected void writeProperties(Properties properties, File file) throws MojoExecutionException {
        OutputStream out = null;
        try {
            out = FileUtils.openOutputStream(outputFile);
            properties.store(out, "Properties");
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating properties file", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}

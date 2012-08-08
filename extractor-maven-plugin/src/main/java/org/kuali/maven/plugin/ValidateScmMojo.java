/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.Extractor;

/**
 * Validate that the URL provided in the <code>scm</code> section of the pom matches up with a property that contains
 * the correct SCM URL.
 * 
 * @goal validatescm
 */
public class ValidateScmMojo extends AbstractMojo {

    /**
     * The Maven project this plugin runs in.
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * Property containing the correct SCM URL. This can be a project property, system property, or environment
     * variable.
     * 
     * @parameter expression="${extractor.scmUrlProperty}" default-value="scm.url"
     * @required
     * @readonly
     */
    private String scmUrlProperty;

    /**
     * If true, the plugin will operate silently without generating any logging output
     * 
     * @parameter expression="${extractor.silent}" default-value="false"
     * @required
     */
    private boolean silent;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Extractor extractor = new Extractor();
        String pomUrl = extractor.getScmUrl(project.getScm());
        String actualUrl = extractor.getActualUrl(project, scmUrlProperty);
        extractor.validateUrls(pomUrl, actualUrl);
        if (!silent) {
            getLog().info("SCM URL is valid [" + pomUrl + "]");
        }
    }

    public MavenProject getProject() {
        return project;
    }

    public String getScmUrlProperty() {
        return scmUrlProperty;
    }

    public void setScmUrlProperty(String svnUrlProperty) {
        this.scmUrlProperty = svnUrlProperty;
    }
}

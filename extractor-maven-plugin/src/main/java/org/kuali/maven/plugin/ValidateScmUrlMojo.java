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
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.Extractor;

/**
 * @goal validatescmurl
 * @aggregator
 */
public class ValidateScmUrlMojo extends AbstractMojo {
    Extractor extractor = new Extractor();

    /**
     * The Maven project this plugin runs in.
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * 
     * @parameter expression="${extractor.scmUrlProperty}" default-value="scm.url"
     * @required
     * @readonly
     */
    private String scmUrlProperty;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Extractor e = new Extractor();
        String pomUrl = e.getScmUrl(project.getScm());
        String actualUrl = project.getProperties().getProperty(scmUrlProperty);
        if (StringUtils.isBlank(pomUrl)) {
            throw new MojoExecutionException("Unable to extract the scm url from the pom");
        }
        if (StringUtils.isBlank(actualUrl)) {
            throw new MojoExecutionException("Unable to extract the actual scm url from the project property '"
                    + scmUrlProperty + "'");
        }
        if (pomUrl.equals(actualUrl)) {
            getLog().info("scm url validation successful. " + pomUrl);
        } else {
            throw new MojoExecutionException("SCM url mismatch.  URL in the pom is " + pomUrl + " Actual URL is "
                    + actualUrl);
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

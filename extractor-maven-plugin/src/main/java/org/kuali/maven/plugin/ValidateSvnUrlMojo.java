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
 * @goal validatesvnurl
 * @aggregator
 */
public class ValidateSvnUrlMojo extends AbstractMojo {
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
     * @parameter expression="${extractor.svnUrlProperty}" default-value="svn.url"
     * @required
     * @readonly
     */
    private String svnUrlProperty;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Extractor e = new Extractor();
        String pomUrl = e.getScmUrl(project.getScm());
        String actualUrl = project.getProperties().getProperty(svnUrlProperty);
        if (StringUtils.isBlank(pomUrl)) {
            throw new MojoExecutionException("Unable to extract the SVN url from the pom");
        }
        if (StringUtils.isBlank(actualUrl)) {
            throw new MojoExecutionException("Unable to extract the actual SVN url from the project property '"
                    + svnUrlProperty + "'");
        }
        if (pomUrl.equals(actualUrl)) {
            getLog().info("SVN url validation successful. " + pomUrl);
        } else {
            throw new MojoExecutionException("SVN url mismatch.  URL in the pom is " + pomUrl + " Actual URL is "
                    + actualUrl);
        }
    }

    public MavenProject getProject() {
        return project;
    }

    public String getSvnUrlProperty() {
        return svnUrlProperty;
    }

    public void setSvnUrlProperty(String svnUrlProperty) {
        this.svnUrlProperty = svnUrlProperty;
    }
}

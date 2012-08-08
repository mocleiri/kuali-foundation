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

import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.maven.common.Extractor;
import org.kuali.maven.common.PropertiesUtils;

/**
 * @goal validatescm
 */
public class ValidateScmMojo extends AbstractMojo {
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

    /**
     * 
     * @parameter expression="${extractor.silent}" default-value="false"
     * @required
     */
    private boolean silent;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        PropertiesUtils utils = new PropertiesUtils();
        Extractor extractor = new Extractor();
        String pomUrl = extractor.getScmUrl(project.getScm());
        Properties mavenProperties = utils.getMavenProperties(project);
        String actualUrl = mavenProperties.getProperty(scmUrlProperty);
        String resolvedUrl = utils.getResolvedValue(actualUrl, mavenProperties);
        validate(pomUrl, resolvedUrl);
    }

    protected void validate(String pomUrl, String actualUrl) throws MojoExecutionException {
        if (StringUtils.isBlank(pomUrl)) {
            throw new MojoExecutionException("Unable to extract the scm url from the pom");
        }
        if (StringUtils.isBlank(actualUrl)) {
            throw new MojoExecutionException("The project property '" + scmUrlProperty + "' is blank");
        }
        if (pomUrl.equals(actualUrl)) {
            if (!silent) {
                getLog().info("Validated SCM URL [" + pomUrl + "]");
            }
        } else {
            throw new MojoExecutionException("SCM url mismatch.  POM=[" + pomUrl + "] Actual=[" + actualUrl + "]");
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

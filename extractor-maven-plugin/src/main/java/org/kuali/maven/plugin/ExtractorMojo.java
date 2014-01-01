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
package org.kuali.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.Extractor;

/**
 * Extracts information contained in the pom and exposes it as project properties
 *
 * eg major version, scm type, scm url
 *
 * @goal extract
 */
public class ExtractorMojo extends AbstractMojo {
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
     * The project property where the major version will be stored. eg for a project with a version "1.0.0" this mojo
     * considers "1.0" to be the major version
     *
     * @parameter expression="${extractor.majorVersionProperty}" default-value="extractor.majorVersion"
     * @required
     */
    private String majorVersionProperty;

    /**
     * The project property where the scm type will be stored eg "svn", "git"
     *
     * @parameter expression="${extractor.scmTypeProperty}" default-value="extractor.scmType"
     * @required
     */
    private String scmTypeProperty;

    /**
     * The project property where the scm url (minus the Maven prefix eg "scm:svn", "scm:git") will be stored
     *
     * @parameter expression="${extractor.scmUrlProperty}" default-value="extractor.scmUrl"
     * @required
     */
    private String scmUrlProperty;

    /**
     * The project property where the Subversion specific tag base will be stored. The logic here examines the scm url
     * for the last occurrence of "/branches" or "/trunk". It then takes whatever is to the left of that and adds
     * "/tags"
     *
     * @parameter expression="${extractor.svnTagBaseProperty}" default-value="extractor.svnTagBase"
     * @required
     */
    private String svnTagBaseProperty;

    /**
     * The project property where the Subversion specific branch will be stored. The logic here examines the scm url for
     * the last occurrence of "/branches" or "/trunk". If it finds the string "/branches" it stores whatever is to the
     * right of that under the indicated property, if it finds the string "/trunk" it stores "trunk" under the indicated
     * property
     *
     * @parameter expression="${extractor.svnBranchProperty}" default-value="extractor.svnBranch"
     * @required
     */
    private String svnBranchProperty;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        extractor.handleMajorVersion(this, project, majorVersionProperty);
        extractor.handleScmType(this, project, scmTypeProperty);
        extractor.handleScmUrl(this, project, scmUrlProperty);
        extractor.handleSVNTagBase(this, project, svnTagBaseProperty);
        extractor.handleSVNBranch(this, project, svnBranchProperty);
    }

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public String getMajorVersionProperty() {
        return majorVersionProperty;
    }

    public void setMajorVersionProperty(String majorVersionProperty) {
        this.majorVersionProperty = majorVersionProperty;
    }

    public String getScmTypeProperty() {
        return scmTypeProperty;
    }

    public void setScmTypeProperty(String scmTypeProperty) {
        this.scmTypeProperty = scmTypeProperty;
    }

    public String getScmUrlProperty() {
        return scmUrlProperty;
    }

    public void setScmUrlProperty(String scmUrlProperty) {
        this.scmUrlProperty = scmUrlProperty;
    }

    public String getSvnTagBaseProperty() {
        return svnTagBaseProperty;
    }

    public void setSvnTagBaseProperty(String scmTagBaseProperty) {
        this.svnTagBaseProperty = scmTagBaseProperty;
    }

    public String getSvnBranchProperty() {
        return svnBranchProperty;
    }

    public void setSvnBranchProperty(String svnBranchProperty) {
        this.svnBranchProperty = svnBranchProperty;
    }
}

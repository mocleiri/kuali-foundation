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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @goal syncworkspace
 * @threadSafe
 * @aggregator
 */
public class SyncWorkspaceMojo extends AbstractMojo {

    /**
     * The Maven project object
     *
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * @parameter expression="${jenkins.excludeTarget}" default-value="true"
     */
    private boolean excludeTarget;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Hello world");
    }

    public MavenProject getProject() {
        return project;
    }

    public boolean isExcludeTarget() {
        return excludeTarget;
    }

    public void setExcludeTarget(boolean excludeTarget) {
        this.excludeTarget = excludeTarget;
    }

}
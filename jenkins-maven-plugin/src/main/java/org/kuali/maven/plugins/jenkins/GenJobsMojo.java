/**
 * Copyright 2004-2011 The Kuali Foundation
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

/**
 * Generate XML config files in the format required for Jenkins jobs
 *
 * @goal genjobs
 * @requiresDependencyResolution test
 */
public class GenJobsMojo extends BaseMojo {

    /**
     * The types of jobs to retrieve. Maven GAV info is combined with 'type' to derive the complete job name eg
     * 'jenkins-maven-plugin-1.0-publish'
     *
     * @parameter expression="${jenkins.type}" default-value="publish,unit,license,release"
     * @required
     */
    private String types;

    @Override
    public void execute() {
        helper.execute(this);
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
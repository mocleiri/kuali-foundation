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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @goal get-gav-property
 */
public class GetGAVProperty extends AbstractMojo {

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * @parameter expression="${properties.property}"
     */
    private String property;

    /**
     * @parameter expression="${properties.defaultValue}"
     */
    private String defaultValue;

    @Override
    public void execute() throws MojoExecutionException {
        List<String> keys = getKeys(project);
        String value = getValue(project, keys);
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        project.getProperties().setProperty(property + ".gav", value);
    }

    protected String getValue(MavenProject project, List<String> keys) {
        for (String key : keys) {
            String value = getProperty(key);
            if (!StringUtils.isBlank(value)) {
                return value;
            }
        }
        return null;
    }

    protected List<String> getKeys(MavenProject project) {
        String groupId = project.getGroupId();
        String artifactId = project.getArtifactId();
        String version = project.getVersion();

        List<String> keys = new ArrayList<String>();
        keys.add(property + "." + groupId + "." + artifactId + "." + version);
        keys.add(property + "." + groupId + "." + artifactId);
        keys.add(property + "." + groupId);
        keys.add(property);
        return keys;
    }

    protected String getProperty(String key) {
        String sys = System.getProperty(key);
        String proj = project.getProperties().getProperty(key);
        if (!StringUtils.isBlank(sys)) {
            return sys;
        } else {
            return proj;
        }

    }

    public MavenProject getProject() {
        return project;
    }

}

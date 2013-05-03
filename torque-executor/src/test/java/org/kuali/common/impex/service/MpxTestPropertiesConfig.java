/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.service;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.spring.GeneratorPropertiesConfig;
import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.spring.ConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcPropertiesConfig.class, GeneratorPropertiesConfig.class })
public abstract class MpxTestPropertiesConfig {

    // Simulate the properties supplied by Maven for KS
    public static final Properties KS_MAVEN_PROPS = getKSMavenProperties();

    @Bean
    public ProjectProperties ksProjectProperties() {
        Project project = ProjectUtils.getProject(KS_MAVEN_PROPS);
        return ConfigUtils.getProjectProperties(project, propertyLocations());
    }

    @Bean
    protected abstract List<String> propertyLocations();

    private static Properties getKSMavenProperties() {
        Properties p = new Properties();
        p.setProperty("project.groupId", "org.kuali.student");
        p.setProperty("project.artifactId", "ks-impex-rice-db");
        p.setProperty("project.version", "2.0.0-SNAPSHOT");
        p.setProperty("project.encoding", "UTF-8");
        p.setProperty("project.orgId", "org.kuali");
        p.setProperty("project.orgId.code", "kuali");
        p.setProperty("project.orgId.path", "org/kuali");

        MavenUtils.augmentProjectProperties(p);

        return p;
    }

}

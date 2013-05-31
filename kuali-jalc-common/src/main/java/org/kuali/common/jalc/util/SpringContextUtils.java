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

package org.kuali.common.jalc.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;

public class SpringContextUtils {


    public static Properties getTestMavenProperties(String location) throws IOException {
        Properties p = new Properties();
        p.load(LocationUtils.getInputStream(location));

        p.setProperty("project.groupId", "org.kuali.common");
        p.setProperty("project.artifactId", "kuali-jalc-producer");
        p.setProperty("project.version", "3.0-SNAPSHOT");
        p.setProperty("project.encoding", "UTF-8");
        p.setProperty("project.orgId", "org.kuali");
        p.setProperty("project.orgId.code", "kuali");
        p.setProperty("project.orgId.path", "org/kuali");

        MavenUtils.augmentProjectProperties(p);

        return p;
    }

    public static void loadSpringService(String propertiesLocation, Class<?> propertySourceConfigClass, List<Class<?>> annotatedClasses) throws IOException {

        // Default Spring service will do what we need
        SpringService ss = new DefaultSpringService();

        // Setup a Spring context that uses maven properties for placeholder resolution
        SpringContext context = MavenUtils.getMavenizedSpringContext(ss, getTestMavenProperties(propertiesLocation), propertySourceConfigClass);

        context.setAnnotatedClasses(annotatedClasses);

        // Execute Spring
        ss.load(context);
    }
}

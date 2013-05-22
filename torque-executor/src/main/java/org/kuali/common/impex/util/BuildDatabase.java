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

package org.kuali.common.impex.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.jdbc.spring.SqlControllerConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MavenUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.springframework.util.ResourceUtils;

public class BuildDatabase {

    public static void main(String[] args) {

        if(args.length < 1) {
            printHelpAndExit();
        }

        String propertyFileName = args[0];
        boolean includeMpxConfig = true;
        if(args.length >= 2) {
            includeMpxConfig = Boolean.parseBoolean(args[1]);
        }

        try {
            Properties props = getTestMavenProperties(propertyFileName);

            // Default Spring service will do what we need
            SpringService ss = new DefaultSpringService();

            // Setup a Spring context that uses maven properties for placeholder resolution
            SpringContext context = MavenUtils.getMavenizedSpringContext(ss, props, UtilMavenPropertySourceConfig.class);

            // Reset the db using annotated config

            List<Class<?>> configClasses;
            if(includeMpxConfig) {
                configClasses = CollectionUtils.asList(MpxSupplierConfig.class, SqlControllerConfig.class);
            }
            else {
                configClasses = CollectionUtils.asList(SqlControllerConfig.class);
            }

            context.setAnnotatedClasses(configClasses);

            // Execute Spring
            ss.load(context);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static Properties getTestMavenProperties(String fileName) throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(ResourceUtils.getFile(fileName)));

        p.setProperty("project.groupId", "org.kuali.foundation");
        p.setProperty("project.artifactId", "torque-executor");
        p.setProperty("project.version", "2.1.8-SNAPSHOT");
        p.setProperty("project.encoding", "UTF-8");
        p.setProperty("project.orgId", "org.kuali");
        p.setProperty("project.orgId.code", "kuali");
        p.setProperty("project.orgId.path", "org/kuali");

        MavenUtils.augmentProjectProperties(p);

        return p;
    }

    private static void printHelpAndExit() {
        System.out.println("Expects at least one argument, first a property file location.");
        System.out.println("Optionally, a second argument will be interpreted as whether or not to include configuration for Mpx files (default is true)");
        System.exit(1);
    }

}

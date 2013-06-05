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

import java.util.List;

import org.kuali.common.jalc.spring.MpxSupplierConfig;
import org.kuali.common.jdbc.spring.JdbcMavenPropertySourceConfig;
import org.kuali.common.jdbc.spring.SqlControllerConfig;
import org.kuali.common.util.CollectionUtils;

public class BuildDatabaseUtility {

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

            List<Class<?>> configClasses;
            if(includeMpxConfig) {
                configClasses = CollectionUtils.asList(MpxSupplierConfig.class, SqlControllerConfig.class);
            }
            else {
                configClasses = CollectionUtils.asList(SqlControllerConfig.class);
            }

            // Reset the db using annotated config
            SpringContextUtils.loadSpringService(propertyFileName, JdbcMavenPropertySourceConfig.class, configClasses);


        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static void printHelpAndExit() {
        System.out.println("Expects at least one argument, first a property file location.");
        System.out.println("Optionally, a second argument will be interpreted as whether or not to include configuration for Mpx files (default is true)");
        System.exit(1);
    }

}

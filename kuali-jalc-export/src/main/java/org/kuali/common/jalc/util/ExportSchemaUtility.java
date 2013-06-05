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

import org.kuali.common.jalc.spring.ExportSchemaConfig;
import org.kuali.common.jalc.spring.ExportSchemaExecutable;
import org.kuali.common.jalc.spring.LiquibaseModelProviderConfig;
import org.kuali.common.jdbc.spring.JdbcMavenPropertySourceConfig;
import org.kuali.common.jdbc.spring.SqlControllerConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.MavenPropertySourceConfig;

public class ExportSchemaUtility {

    public static void main(String[] args) {

        if(args.length != 1) {
            printHelpAndExit();
        }

        String propertyFileName = args[0];

        try {
            // Reset the db using annotated config
            SpringContextUtils.loadSpringService(propertyFileName, JdbcMavenPropertySourceConfig.class, CollectionUtils.asList(ExportSchemaConfig.class));
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static void printHelpAndExit() {
        System.out.println("Expects one argument, a property file location.");
        System.exit(1);
    }

}

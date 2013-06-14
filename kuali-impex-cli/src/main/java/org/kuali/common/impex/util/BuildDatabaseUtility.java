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

import java.util.List;

import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.impex.spring.SchemaXmlSupplierConfig;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.jdbc.spring.SqlControllerConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.spring.SpringUtils;

public class BuildDatabaseUtility {

	public static void main(String[] args) {

		if (args.length < 1) {
			printHelpAndExit();
		}

		String propertiesLocation = args[0];
		boolean includeMpxConfig = true;
		if (args.length >= 2) {
			includeMpxConfig = Boolean.parseBoolean(args[1]);
		}

		try {

			List<Class<?>> configClasses;
			if (includeMpxConfig) {
				configClasses = CollectionUtils.asList(MpxSupplierConfig.class, SchemaXmlSupplierConfig.class, SqlControllerConfig.class);
			} else {
				configClasses = CollectionUtils.asList(SchemaXmlSupplierConfig.class, SqlControllerConfig.class);
			}

			// Reset the db using annotated config
			JdbcProjectContext project = new JdbcProjectContext();
			SpringExecutable executable = SpringUtils.getSpringExecutable(project, propertiesLocation, configClasses);
			executable.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printHelpAndExit() {
		System.out.println("Expects at least one argument, first a property file location.");
		System.out.println("Optionally, a second argument will be interpreted as whether or not to include configuration for Mpx files (default is true)");
		System.exit(1);
	}

}

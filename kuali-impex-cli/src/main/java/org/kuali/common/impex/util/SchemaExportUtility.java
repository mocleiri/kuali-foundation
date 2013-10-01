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
import java.util.Properties;

import org.kuali.common.impex.config.DumpConfigConstants;
import org.kuali.common.impex.spring.DumpDatabaseExecutableConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.service.DefaultConfigService;
import org.kuali.common.util.config.supplier.ConfigPropertiesSupplier;
import org.kuali.common.util.config.supplier.PropertiesSupplier;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.spring.SpringUtils;

public class SchemaExportUtility {

	public static void main(String[] args) {

		String propertiesLocation = getPropertiesLocation(args);

		if (propertiesLocation == null) {
			printHelpAndExit();
		}

		try {
			List<Class<?>> annotatedClasses = CollectionUtils.asList(DumpDatabaseExecutableConfig.class);
			Properties overrides = PropertyUtils.load(propertiesLocation);
			PropertiesSupplier supplier = new ConfigPropertiesSupplier(DumpConfigConstants.CONFIG_IDS, new DefaultConfigService(), overrides);
			SpringExecutable executable = SpringUtils.getSpringExecutable(supplier, annotatedClasses);
			executable.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected static String getPropertiesLocation(String[] args) {
		if (args == null || args.length < 1) {
			return null;
		} else {
			return args[0];
		}
	}

	private static void printHelpAndExit() {
		System.out.println("Expects exactly one argument, a property file location.");
		System.exit(1);
	}

}

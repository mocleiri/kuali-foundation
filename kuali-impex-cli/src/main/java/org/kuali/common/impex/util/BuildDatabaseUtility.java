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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.KualiImpexProducerConfig;
import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.impex.spring.SchemaXmlSupplierConfig;
import org.kuali.common.jdbc.config.JdbcConfigConstants;
import org.kuali.common.jdbc.spring.SqlControllerExecutableConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.config.service.DefaultConfigService;
import org.kuali.common.util.config.supplier.ConfigPropertiesSupplier;
import org.kuali.common.util.config.supplier.PropertiesSupplier;
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
			List<Class<?>> configClasses = getAnnotatedClasses(includeMpxConfig);
			List<String> configIds = new ArrayList<String>(JdbcConfigConstants.DEFAULT_CONFIG_IDS);
			configIds.add(KualiImpexProducerConfig.SCHEMA_SQL.getConfigId());
			if (includeMpxConfig) {
				configIds.add(KualiImpexProducerConfig.MPX_SQL.getConfigId());
			}

			Properties overrides = PropertyUtils.load(propertiesLocation);
			PropertiesSupplier supplier = new ConfigPropertiesSupplier(configIds, new DefaultConfigService(), overrides);
			SpringExecutable executable = SpringUtils.getSpringExecutable(supplier, configClasses);
			executable.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected static List<Class<?>> getAnnotatedClasses(boolean includeMpxConfig) {
		if (includeMpxConfig) {
			return CollectionUtils.asList(MpxSupplierConfig.class, SchemaXmlSupplierConfig.class, SqlControllerExecutableConfig.class);
		} else {
			return CollectionUtils.asList(SchemaXmlSupplierConfig.class, SqlControllerExecutableConfig.class);
		}
	}

	protected static void printHelpAndExit() {
		System.out.println("Expects at least one argument, first a property file location.");
		System.out.println("Optionally, a second argument will be interpreted as whether or not to include configuration for Mpx files (default is true)");
		System.exit(1);
	}

}

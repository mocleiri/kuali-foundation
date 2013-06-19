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

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.spring.ExportSchemaConfig;
import org.kuali.common.impex.spring.LiquibaseSchemaConfig;
import org.kuali.common.impex.spring.XmlSchemaConfig;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.spring.SpringUtils;

public class ExportSchemaUtility {

	protected final static String LIQUIBASE_KEY = "lb";
	protected final static String XML_KEY = "xml";
	protected final static String CUSTOM_KEY = "custom=";
	protected final static Class<?> DEFAULT_PROVIDER_CONFIG_CLASS = LiquibaseSchemaConfig.class;

	public static void main(String[] args) {

		String propertiesLocation = getPropertiesLocation(args);
		Class<?> providerConfigClass = getProviderConfig(args);

		if (propertiesLocation == null || providerConfigClass == null) {
			printHelpAndExit();
		}

		try {
			ProjectContext project = new JdbcProjectContext();
			List<Class<?>> annotatedClasses = CollectionUtils.asList(ExportSchemaConfig.class, providerConfigClass);
			SpringExecutable executable = SpringUtils.getSpringExecutable(project, propertiesLocation, annotatedClasses);
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

	protected static Class<?> getProviderConfig(String[] args) {
		if (args == null || args.length < 2) {
			return DEFAULT_PROVIDER_CONFIG_CLASS;
		}

		String annotatedClassKey = StringUtils.trimToNull(args[1]);
		if (StringUtils.isBlank(annotatedClassKey)) {
			return null;
		} else {
			return getProviderConfig(annotatedClassKey);
		}
	}

	protected static Class<?> getProviderConfig(String className) {
		if (StringUtils.equals(className, LIQUIBASE_KEY)) {
			return LiquibaseSchemaConfig.class;
		} else if (StringUtils.equals(className, XML_KEY)) {
			return XmlSchemaConfig.class;
		} else if (StringUtils.startsWith(className, CUSTOM_KEY)) {
			className = StringUtils.remove(className, CUSTOM_KEY);
			return ReflectionUtils.getClass(className);
		} else {
			throw new IllegalArgumentException("'" + className + "' is not supported");
		}
	}

	private static void printHelpAndExit() {
		System.out.println("Expects at least one argument, a property file location.");
		System.out.println("Additionally, second argument can be provided to specify the configuration class that should be used to instantiate a Schema");
		System.out.println("Valid values for this argument are: ");
		System.out.println(LIQUIBASE_KEY + "  --  Use LiquibaseSchemaConfig");
		System.out.println(XML_KEY + " --  Use XmlSchemaConfig");
		System.exit(1);
	}

}

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

package org.kuali.common.impex.cli;

import org.kuali.common.impex.DumpProjectContext;
import org.kuali.common.impex.spring.DumpDatabaseExecutableConfig;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.spring.SpringUtils;

public class DumpDatabase {

	public static void main(String[] args) {

		try {
			String props = getPropertiesLocation(args);

			if (props == null) {
				printHelpAndExit();
			}

			ProjectContext jdbc = new JdbcProjectContext();
			ProjectContext dump = new DumpProjectContext();

			SpringExecutable executable = SpringUtils.getSpringExecutable(DumpDatabaseExecutableConfig.class, props, jdbc, dump);
			executable.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printHelpAndExit() {
		System.out.println("Expects exactly one argument, a properties file location.");
		System.exit(1);
	}

	protected static String getPropertiesLocation(String[] args) {
		if (args == null || args.length < 1) {
			return null;
		} else {
			return args[0];
		}
	}
}

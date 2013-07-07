/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;
import org.kuali.common.util.ProjectConstants;
import org.kuali.common.util.ProjectUtils;

public class JdbcProjectContext extends DefaultProjectContext {

	private static final List<String> LOCATIONS = getLocations();
	private static final String ARTIFACT_ID = ProjectConstants.JDBC_ARIFACT_ID;

	public JdbcProjectContext() {
		super(ARTIFACT_ID, LOCATIONS);
	}

	private static final List<String> getLocations() {
		List<String> locations = new ArrayList<String>();

		// Add JDBC properties
		locations.add(ProjectUtils.getCommonClassPathPrefix(ARTIFACT_ID) + "/jdbc.properties");

		// Add SQL properties
		String prefix = ProjectUtils.getCommonClassPathPrefix(ProjectConstants.SQL_ARTIFACT_ID);
		locations.add(prefix + "/sql.xml");
		locations.add(prefix + "/mysql.xml");
		locations.add(prefix + "/oracle.xml");
		locations.add(prefix + "/h2.xml");
		locations.add(prefix + "/derby.xml");
		return locations;
	}

}

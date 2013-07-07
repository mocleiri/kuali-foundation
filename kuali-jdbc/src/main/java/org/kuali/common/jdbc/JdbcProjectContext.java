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
import org.kuali.common.util.MavenConstants;
import org.kuali.common.util.Str;

public class JdbcProjectContext extends DefaultProjectContext {

	private static final List<String> LOCATIONS = getLocations();
	private static final String ARTIFACT_ID = "kuali-jdbc";

	public JdbcProjectContext() {
		super(ARTIFACT_ID, new ArrayList<String>(LOCATIONS));
	}

	private static final List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + "/jdbc/jdbc.properties");
		locations.add("classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + "/sql/sql.xml");
		locations.add("classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + "/sql/mysql.xml");
		locations.add("classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + "/sql/oracle.xml");
		locations.add("classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + "/sql/h2.xml");
		locations.add("classpath:" + Str.getPath(MavenConstants.KUALI_COMMON_GROUP_ID) + "/sql/derby.xml");
		return locations;
	}

}

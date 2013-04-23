/**
 * Copyright 2013 The Kuali Foundation Licensed under the
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

package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.springframework.core.env.Environment;

/**
 * 
 * @author andrewlubbers
 */
public interface GroupedSqlConfig {

	/**
	 * The label for the group, which should be the same as the key to the the location properties
	 * 
	 * @return the group key
	 */
	String getGroupKey();

	SqlMode getSqlMode();

	Environment getEnvironment();

	JdbcCommonConfig getJdbcCommonConfig();

	JdbcDataSourceConfig getDataSourceConfig();

	SqlListener getSqlListener();

}

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
package org.kuali.common.jdbc.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class JdbcConfigConstants {

	/**
	 * Returns the unmodifiable list of default configuration ids needed by JDBC related process
	 */
	public static final List<String> DEFAULT_CONFIG_IDS = getDefaultConfigIds();

	protected static final List<String> getDefaultConfigIds() {
		List<String> configIds = new ArrayList<String>();
		configIds.add(KualiSqlConfig.DEFAULT.getConfigId());
		configIds.add(KualiJdbcConfig.DEFAULT.getConfigId());
		return Collections.unmodifiableList(configIds);
	}

}

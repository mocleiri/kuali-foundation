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

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.Str;

public abstract class JdbcConfigConstants {

	public static final String GROUP_ID = KualiProjectConstants.COMMON_GROUP_ID;
	public static final String ARTIFACT_ID = KualiProjectConstants.JDBC_ARTIFACT_ID;

	public static final String SQL_CONFIG_ID = Str.getId(GROUP_ID, KualiProjectConstants.SQL_ARTIFACT_ID);
	public static final String JDBC_CONFIG_ID = Str.getId(GROUP_ID, ARTIFACT_ID);

	public static final List<String> CONFIG_IDS = CollectionUtils.unmodifiableList(SQL_CONFIG_ID, JDBC_CONFIG_ID);

}

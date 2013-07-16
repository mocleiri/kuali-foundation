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

package org.kuali.common.jdbc;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.ProjectConstants;

public class JdbcConfigConstants {

    public static final String JDBC_CONFIG_ID = ProjectConstants.COMMON_GROUP_ID + ":" + ProjectConstants.JDBC_ARIFACT_ID;

    public static final String SQL_CONFIG_ID = ProjectConstants.COMMON_GROUP_ID + ":" + ProjectConstants.SQL_ARTIFACT_ID;

    public static final List<String> JDBC_PROJECT_CONFIG_IDS = Arrays.asList(SQL_CONFIG_ID, JDBC_CONFIG_ID);

}

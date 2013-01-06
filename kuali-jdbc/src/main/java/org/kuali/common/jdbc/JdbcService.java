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

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlContext;

public interface JdbcService {

	JdbcMetaData getJdbcMetaData(DataSource dataSource);

	SqlMetaData executeSqlString(JdbcContext context, String sql);

	SqlMetaDataList executeSqlStrings(JdbcContext context, List<String> sql);

	SqlMetaData executeSql(JdbcContext context, String location);

	SqlMetaData executeSql(JdbcContext context, String location, String encoding);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations);

	SqlMetaDataList executeSql(JdbcContext context, List<String> locations, String encoding);

	SqlMetaData getMetaDataFromString(SqlContext context, String sql);

	SqlMetaDataList getMetaDataFromStrings(SqlContext context, List<String> sql);

	SqlMetaData getMetaData(SqlContext context, String location);

	SqlMetaData getMetaData(SqlContext context, String location, String encoding);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations);

	SqlMetaDataList getMetaData(SqlContext context, List<String> locations, String encoding);

}

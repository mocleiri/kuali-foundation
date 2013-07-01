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
package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public interface MySqlDumpService extends ExecService {

	public static final String DEFAULT_EXECUTABLE = "mysqldump";
	public static final int DEFAULT_PORT = 3306;
	public static final String PREFIX_50013_DEFINER = "/*!50013 DEFINER=";
	public static final String SUFFIX_SQL_SECURITY_DEFINER = "SQL SECURITY DEFINER */";
	public static final String PREFIX_MYSQL_DUMP = "-- MySQL dump";
	public static final String PREFIX_MYSQL_HOST = "-- Host:";
	public static final String PREFIX_SERVER_VERSION = "-- Server version";
	public static final String PREFIX_DUMP_COMPLETED = "-- Dump completed";

	void dump(MySqlDumpContext context);

	void dump(String username, String password, String hostname, String database, File outputFile);

	void dump(List<String> options, String database, File outputFile);

}

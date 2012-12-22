/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.common.deploy.execute;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String TOMCAT_HOME = "/usr/local/tomcat";
	public static final String TOMCAT_USER = "tomcat";
	public static final String TOMCAT_GROUP = "tomcat";
	public static final String TOMCAT_BIN = TOMCAT_HOME + "/bin";
	public static final String TOMCAT_LIB = TOMCAT_HOME + "/lib";
	public static final String TOMCAT_LOGS = TOMCAT_HOME + "/logs";
	public static final String TOMCAT_CONF = TOMCAT_HOME + "/conf";
	public static final String TOMCAT_WEBAPPS = TOMCAT_HOME + "/webapps";
	public static final String TOMCAT_ROOT_WAR = TOMCAT_WEBAPPS + "/ROOT.war";
	public static final String TOMCAT_ROOT_DIR = TOMCAT_WEBAPPS + "/ROOT";
	public static final String TOMCAT_SETENV = TOMCAT_BIN + "/setenv.sh";
	public static final String TOMCAT_SHUTDOWN = TOMCAT_BIN + "/forced-shutdown.sh";
	public static final String TOMCAT_STARTUP = TOMCAT_BIN + "/startup.sh";
	public static final String TOMCAT_CLEANUP = TOMCAT_BIN + "/cleanup.sh";
	public static final String APP_DYNAMICS_DIR = "/usr/local/server-agent/conf";
	public static final String APP_DYNAMICS_CONTROLLER = APP_DYNAMICS_DIR + "/controller-info.xml";

	// JAR files matching these patterns are removed prior to a fresh deploy
	public static final List<String> JDBC_DRIVER_PATTERNS = Arrays.asList(TOMCAT_LIB + "/mysql*.jar", TOMCAT_LIB + "/ojdbc*.jar", TOMCAT_LIB + "/oracle*.jar");
}

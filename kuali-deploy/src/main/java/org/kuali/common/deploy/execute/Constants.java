package org.kuali.common.deploy.execute;

public class Constants {

	public static final String TOMCAT_HOME = "/usr/local/tomcat";
	public static final String TOMCAT_USER = "tomcat";
	public static final String TOMCAT_BIN = TOMCAT_HOME + "/bin";
	public static final String TOMCAT_LIB = TOMCAT_HOME + "/lib";
	public static final String TOMCAT_LOGS = TOMCAT_HOME + "/logs";
	public static final String TOMCAT_CONF = TOMCAT_HOME + "/conf";
	public static final String TOMCAT_WEBAPPS = TOMCAT_HOME + "/webapps";
	public static final String TOMCAT_SETENV = TOMCAT_BIN + "/setenv.sh";
	public static final String TOMCAT_SHUTDOWN = TOMCAT_BIN + "/forced-shutdown.sh";
	public static final String TOMCAT_STARTUP = TOMCAT_BIN + "/startup.sh";
	public static final String TOMCAT_CLEANUP = TOMCAT_BIN + "/cleanup.sh";
}

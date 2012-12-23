package org.kuali.common.deploy.context;

public class TomcatContext {
	public static final String HOME = "/usr/local/tomcat";
	public static final String BIN = HOME + "/bin";
	public static final String LIB = HOME + "/lib";
	public static final String LOGS = HOME + "/logs";
	public static final String CONF = HOME + "/conf";
	public static final String WEBAPPS = HOME + "/webapps";
	public static final String TOMCAT = "tomcat";
	public static final String USER_HOME = "/home/tomcat";

	String startup = BIN + "/startup.sh";
	String cleanup = BIN + "/cleanup.sh";
	String shutdown = BIN + "/forced-shutdown.sh";
	String setenv = BIN + "/setenv.sh";
	String rootWar = WEBAPPS + "/ROOT.war";
	String rootWarDir = WEBAPPS + "/ROOT";
	String lib = LIB;
	String bin = BIN;
	String conf = CONF;
	String logs = LOGS;
	String webapps = WEBAPPS;
	String owner = TOMCAT;
	String group = TOMCAT;
	String login = TOMCAT;
	String userHome;

	public String getStartup() {
		return startup;
	}

	public void setStartup(String startup) {
		this.startup = startup;
	}

	public String getCleanup() {
		return cleanup;
	}

	public void setCleanup(String cleanup) {
		this.cleanup = cleanup;
	}

	public String getShutdown() {
		return shutdown;
	}

	public void setShutdown(String shutdown) {
		this.shutdown = shutdown;
	}

	public String getSetenv() {
		return setenv;
	}

	public void setSetenv(String setenv) {
		this.setenv = setenv;
	}

	public String getRootWar() {
		return rootWar;
	}

	public void setRootWar(String rootWar) {
		this.rootWar = rootWar;
	}

	public String getRootWarDir() {
		return rootWarDir;
	}

	public void setRootWarDir(String rootWarDir) {
		this.rootWarDir = rootWarDir;
	}

	public String getLib() {
		return lib;
	}

	public void setLib(String lib) {
		this.lib = lib;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public String getWebapps() {
		return webapps;
	}

	public void setWebapps(String webapps) {
		this.webapps = webapps;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}

package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.deploy.AppDynamicsMonitoring;
import org.kuali.common.deploy.ApplicationServer;
import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.deploy.MachineAgent;
import org.kuali.common.deploy.Monitoring;
import org.kuali.common.deploy.ServerAgent;
import org.kuali.common.deploy.SysAdminExecutable;
import org.kuali.common.deploy.TomcatApplicationServer;
import org.kuali.common.http.HttpContext;
import org.kuali.common.http.HttpWaitExecutable;
import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.util.Artifact;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.spring.ArtifactFilenameFactoryBean;
import org.kuali.common.util.spring.ArtifactPathFactoryBean;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ MpxSupplierConfig.class, SqlControllerConfig.class })
public class DeployConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	SqlControllerConfig sqlController;

	protected Artifact getJdbcDriverArtifact() {
		Artifact a = new Artifact();
		a.setGroupId(SpringUtils.getProperty(env, "jdbc.driver.groupId"));
		a.setArtifactId(SpringUtils.getProperty(env, "jdbc.driver.artifactId"));
		a.setVersion(SpringUtils.getProperty(env, "jdbc.driver.version"));
		a.setType("jar");
		return a;
	}

	protected Artifact getpplicationArtifact() {
		Artifact a = new Artifact();
		a.setGroupId(SpringUtils.getProperty(env, "project.groupId"));
		a.setArtifactId(SpringUtils.getProperty(env, "project.artifactId"));
		a.setVersion(SpringUtils.getProperty(env, "project.version"));
		a.setClassifier(SpringUtils.getProperty(env, "project.classifier", "NONE"));
		a.setType("war");
		return a;
	}

	protected DeployContext getDeployContext() {
		DeployContext ctx = new DeployContext();
		ctx.setEnvironment(SpringUtils.getProperty(env, "deploy.env"));
		ctx.setHostname(SpringUtils.getProperty(env, "kdo.channel.hostname"));
		ctx.setUsername(SpringUtils.getProperty(env, "kdo.channel.username"));
		ctx.setJdbcDriver(getJdbcDriverArtifact());
		ctx.setApplication(getpplicationArtifact());
		ctx.setConfig(getApplicationConfig());
		return ctx;
	}

	@Bean
	public SecureChannel kdoSecureChannel() {
		DeployContext ctx = getDeployContext();
		DefaultSecureChannel dsc = new DefaultSecureChannel();
		dsc.setUsername(ctx.getUsername());
		dsc.setHostname(ctx.getHostname());
		dsc.setStrictHostKeyChecking(SpringUtils.getBoolean(env, "kdo.channel.strictHostKeyChecking", false));
		dsc.setUseConfigFile(SpringUtils.getBoolean(env, "kdo.channel.useConfigFile", false));
		dsc.setIncludeDefaultPrivateKeyLocations(SpringUtils.getBoolean(env, "kdo.channel.includeDefaultPrivateKeyLocations", false));
		dsc.setPrivateKeyStrings(Arrays.asList(SpringUtils.getProperty(env, "kdo.channel.privateKey")));
		return dsc;
	}

	protected List<String> getTomcatDeletes() {
		// /usr/local/tomcat/lib
		String lib = SpringUtils.getProperty(env, "tomcat.lib");

		// rm -rf gets invoked on all of these
		List<String> pathsToDelete = new ArrayList<String>();
		pathsToDelete.add(lib + "/mysql*.jar");
		pathsToDelete.add(lib + "/ojdbc*.jar");
		pathsToDelete.add(lib + "/classes12*.jar");
		pathsToDelete.add(lib + "/orai18n*.jar");
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.setenv"));
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.home.org"));
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.home.org.alt"));
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.conf.catalina"));
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.logs"));
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.webapps"));
		pathsToDelete.add(SpringUtils.getProperty(env, "tomcat.work"));
		return pathsToDelete;
	}

	protected List<String> getTomcatCreates() {
		// mkdir -p gets invoked on all of these
		List<String> dirsToCreate = new ArrayList<String>();
		dirsToCreate.add(SpringUtils.getProperty(env, "tomcat.logs"));
		dirsToCreate.add(SpringUtils.getProperty(env, "tomcat.webapps"));
		dirsToCreate.add(SpringUtils.getProperty(env, "tomcat.conf.catalina"));
		return dirsToCreate;
	}

	protected List<String> getTomcatChowns() {
		// chown -L -R gets invoked on all of these
		List<String> pathsToChown = new ArrayList<String>();
		pathsToChown.add(SpringUtils.getProperty(env, "tomcat.base"));
		pathsToChown.add(SpringUtils.getProperty(env, "tomcat.home"));
		return pathsToChown;
	}

	protected List<Deployable> getTomcatDeployables() {
		// Tomcat related files that get deployed
		List<Deployable> deployables = new ArrayList<Deployable>();
		deployables.add(getSetEnv());
		deployables.addAll(getJsps());
		deployables.add(getApplicationConfig());
		deployables.add(getJdbcDriver());
		return deployables;
	}

	@Bean
	public Properties kdoFilterProperties() {
		return SpringUtils.getAllEnumerableProperties(env);
	}

	protected ApplicationServer getApplicationServer() {
		// rm -rf gets invoked on all of these
		List<String> pathsToDelete = getTomcatDeletes();

		// mkdir -p gets invoked on all of these
		List<String> dirsToCreate = getTomcatCreates();

		// chown -L -R gets invoked on all of these
		List<String> pathsToChown = getTomcatChowns();

		// Tomcat related files that get deployed
		List<Deployable> deployables = getTomcatDeployables();

		// The war files can be quite large ~100mb's
		// This flag provides a simple way to skip the overhead of uploading a war file
		boolean skipWar = SpringUtils.getBoolean(env, "tomcat.war.skip", false);
		if (!skipWar) {
			deployables.add(getApplication());
		}

		// If true, skip transferring all tomcat related files to the remote machine
		boolean skipFiles = SpringUtils.getBoolean(env, "tomcat.files.skip", false);

		// Setup Tomcat with what it needs to stop/prepare/start correctly
		TomcatApplicationServer tomcat = new TomcatApplicationServer();
		tomcat.setChannel(kdoSecureChannel());
		tomcat.setUsername(SpringUtils.getProperty(env, "tomcat.user"));
		tomcat.setGroup(SpringUtils.getProperty(env, "tomcat.group"));
		tomcat.setShutdown(SpringUtils.getProperty(env, "tomcat.shutdown"));
		tomcat.setStartup(SpringUtils.getProperty(env, "tomcat.startup"));
		tomcat.setPathsToDelete(pathsToDelete);
		tomcat.setDirsToCreate(dirsToCreate);
		tomcat.setDeployables(deployables);
		tomcat.setPathsToChown(pathsToChown);
		tomcat.setSkipFiles(skipFiles);
		tomcat.setFilterProperties(kdoFilterProperties());
		tomcat.setHttpWait(getHttpWaitExecutable());
		return tomcat;
	}

	protected Deployable getSetEnv() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "tomcat.setenv"));
		d.setLocal(SpringUtils.getProperty(env, "tomcat.setenv.local"));
		d.setFilter(true);
		d.setPermissions("755");
		return d;
	}

	protected Deployable getMachineAgentController() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "appdynamics.ma.controller"));
		d.setLocal(SpringUtils.getProperty(env, "appdynamics.ma.controller.local"));
		d.setFilter(true);
		return d;
	}

	protected Deployable getServerAgentController() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "appdynamics.sa.controller"));
		d.setLocal(SpringUtils.getProperty(env, "appdynamics.sa.controller.local"));
		d.setFilter(true);
		return d;
	}

	protected List<Deployable> getJsps() {
		Deployable environment = new Deployable();
		environment.setRemote(SpringUtils.getProperty(env, "tomcat.jsp.env"));
		environment.setLocal(SpringUtils.getProperty(env, "tomcat.jsp.env.local"));

		Deployable tail = new Deployable();
		tail.setRemote(SpringUtils.getProperty(env, "tomcat.jsp.tail"));
		tail.setLocal(SpringUtils.getProperty(env, "tomcat.jsp.tail.local"));

		List<Deployable> jsps = new ArrayList<Deployable>();
		jsps.add(environment);
		jsps.add(tail);
		return jsps;
	}

	protected String getJdbcDriverFilename() {
		Artifact jdbcDriver = getJdbcDriverArtifact();

		ArtifactFilenameFactoryBean factory = new ArtifactFilenameFactoryBean();
		factory.setGroupId(jdbcDriver.getGroupId());
		factory.setArtifactId(jdbcDriver.getArtifactId());
		factory.setVersion(jdbcDriver.getVersion());
		factory.setType(jdbcDriver.getType());
		return factory.getObject();
	}

	protected String getJdbcDriverPath() {
		Artifact jdbcDriver = getJdbcDriverArtifact();
		ArtifactPathFactoryBean factory = new ArtifactPathFactoryBean();
		factory.setGroupId(jdbcDriver.getGroupId());
		factory.setArtifactId(jdbcDriver.getArtifactId());
		factory.setVersion(jdbcDriver.getVersion());
		factory.setType(jdbcDriver.getType());
		return factory.getObject();
	}

	protected String getApplicationPath() {
		Artifact app = getpplicationArtifact();
		ArtifactPathFactoryBean factory = new ArtifactPathFactoryBean();
		factory.setGroupId(app.getGroupId());
		factory.setArtifactId(app.getArtifactId());
		factory.setVersion(app.getVersion());
		factory.setType(app.getType());
		factory.setMustExist(true);
		return factory.getObject();
	}

	protected Deployable getJdbcDriver() {
		String lib = SpringUtils.getProperty(env, "tomcat.lib");
		Deployable d = new Deployable();
		d.setRemote(lib + "/" + getJdbcDriverFilename());
		d.setLocal(getJdbcDriverPath());
		return d;
	}

	protected Deployable getApplication() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "tomcat.root.war"));
		d.setLocal(getApplicationPath());
		return d;
	}

	protected Deployable getApplicationConfig() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "kdo.config"));
		d.setLocal(SpringUtils.getProperty(env, "kdo.config.local"));
		d.setFilter(SpringUtils.getBoolean(env, "kdo.config.filter", true));
		return d;
	}

	protected HttpWaitExecutable getHttpWaitExecutable() {
		// Extract properties from the environment
		Long overallTimeoutMillis = SpringUtils.getMillis(env, "http.overallTimeout", "30m");
		Long requestTimeoutMillis = SpringUtils.getMillis(env, "http.requestTimeout", "15s");
		String url = SpringUtils.getProperty(env, "http.url");
		boolean skip = SpringUtils.getBoolean(env, "http.wait.skip", false);

		// Setup the context
		HttpContext context = new HttpContext();
		context.setUrl(url);
		context.setOverallTimeoutMillis(overallTimeoutMillis.intValue());
		context.setRequestTimeoutMillis(requestTimeoutMillis.intValue());

		// Setup the executable
		HttpWaitExecutable executable = new HttpWaitExecutable();
		executable.setContext(context);
		executable.setSkip(skip);
		return executable;
	}

	protected MachineAgent getMachineAgent() {
		MachineAgent agent = new MachineAgent();
		agent.setStartupCommand(SpringUtils.getProperty(env, "appdynamics.ma.cmd"));
		agent.setBaseDir(SpringUtils.getProperty(env, "appdynamics.ma.base"));
		agent.setTmpDir(SpringUtils.getProperty(env, "appdynamics.ma.tmp"));
		agent.setLogsDir(SpringUtils.getProperty(env, "appdynamics.ma.logs"));
		agent.setController(getMachineAgentController());
		agent.setLogFile(SpringUtils.getProperty(env, "appdynamics.ma.logFile"));
		agent.setStartupToken(SpringUtils.getProperty(env, "appdynamics.ma.logFile.startupToken"));
		agent.setStartupTimeoutMillis(new Long(SpringUtils.getMillis(env, "appdynamics.ma.logFile.monitor.timeout", "5m")).intValue());
		agent.setLogFileIntervalMillis(new Long(SpringUtils.getMillis(env, "appdynamics.ma.logFile.monitor.interval", "500ms")).intValue());
		return agent;
	}

	protected ServerAgent getServerAgent() {
		ServerAgent agent = new ServerAgent();
		agent.setAppServerStartupOptions(SpringUtils.getProperty(env, "appdynamics.sa.tomcat.java.options"));
		agent.setBaseDir(SpringUtils.getProperty(env, "appdynamics.sa.base"));
		agent.setLogsDir(SpringUtils.getProperty(env, "appdynamics.sa.logs"));
		agent.setController(getServerAgentController());
		return agent;
	}

	protected Monitoring getMonitoring() {
		boolean enabled = SpringUtils.getBoolean(env, "monitoring.enabled", false);
		AppDynamicsMonitoring adm = new AppDynamicsMonitoring();
		adm.setUser(SpringUtils.getProperty(env, "tomcat.user"));
		adm.setGroup(SpringUtils.getProperty(env, "tomcat.group"));
		adm.setChannel(kdoSecureChannel());
		adm.setMachineAgent(getMachineAgent());
		adm.setServerAgent(getServerAgent());
		adm.setEnabled(enabled);
		adm.setFilterProperties(kdoFilterProperties());
		return adm;
	}

	protected SysAdminExecutable getSysAdminExecutable() {
		boolean skip = SpringUtils.getBoolean(env, "sysadmin.skip", false);
		String hostname = SpringUtils.getProperty(env, "dns.hostname");
		UnixCmds cmds = new UnixCmds();
		List<String> commands = Arrays.asList(cmds.hostname(hostname));
		SysAdminExecutable sysadmin = new SysAdminExecutable();
		sysadmin.setChannel(kdoSecureChannel());
		sysadmin.setCommands(commands);
		sysadmin.setSkip(skip);
		return sysadmin;
	}

	@Bean(initMethod = "deploy")
	public DeployService kdoDeployService() {
		DefaultDeployService dds = new DefaultDeployService();
		dds.setChannel(kdoSecureChannel());
		dds.setMonitoring(getMonitoring());
		dds.setAppServer(getApplicationServer());
		dds.setDatabaseResetExecutable(sqlController.sqlExecutable());
		dds.setContext(getDeployContext());
		dds.setSysAdminExecutable(getSysAdminExecutable());
		return dds;
	}

}
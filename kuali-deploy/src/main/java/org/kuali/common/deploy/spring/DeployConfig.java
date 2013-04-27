package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.deploy.AppDynamicsMonitoring;
import org.kuali.common.deploy.ApplicationServer;
import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DefaultFileSystem;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.deploy.FileSystem;
import org.kuali.common.deploy.Monitoring;
import org.kuali.common.deploy.NoOpMonitoring;
import org.kuali.common.deploy.TomcatApplicationServer;
import org.kuali.common.http.HttpContext;
import org.kuali.common.http.HttpWaitExecutable;
import org.kuali.common.impex.spring.MpxSupplierConfig;
import org.kuali.common.util.Artifact;
import org.kuali.common.util.execute.Executable;
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

	@Bean
	public Artifact kdoJdbcDriverArtifact() {
		Artifact a = new Artifact();
		a.setGroupId(SpringUtils.getProperty(env, "jdbc.driver.groupId"));
		a.setArtifactId(SpringUtils.getProperty(env, "jdbc.driver.artifactId"));
		a.setVersion(SpringUtils.getProperty(env, "jdbc.driver.version"));
		a.setType("jar");
		return a;
	}

	@Bean
	public Artifact kdoApplicationArtifact() {
		Artifact a = new Artifact();
		a.setGroupId(SpringUtils.getProperty(env, "project.groupId"));
		a.setArtifactId(SpringUtils.getProperty(env, "project.artifactId"));
		a.setVersion(SpringUtils.getProperty(env, "project.version"));
		a.setClassifier(SpringUtils.getProperty(env, "project.classifier", "NONE"));
		a.setType("war");
		return a;
	}

	@Bean
	public DeployContext kdoContext() {
		DeployContext ctx = new DeployContext();
		ctx.setEnvironment(SpringUtils.getProperty(env, "deploy.env"));
		ctx.setHostname(SpringUtils.getProperty(env, "kdo.channel.hostname"));
		ctx.setUsername(SpringUtils.getProperty(env, "kdo.channel.username"));
		ctx.setJdbcDriver(kdoJdbcDriverArtifact());
		ctx.setApplication(kdoApplicationArtifact());
		ctx.setConfig(kdoConfig());
		return ctx;
	}

	@Bean
	public SecureChannel kdoSecureChannel() {
		DeployContext ctx = kdoContext();
		DefaultSecureChannel dsc = new DefaultSecureChannel();
		dsc.setUsername(ctx.getUsername());
		dsc.setHostname(ctx.getHostname());
		dsc.setStrictHostKeyChecking(SpringUtils.getBoolean(env, "kdo.channel.strictHostKeyChecking", false));
		dsc.setUseConfigFile(SpringUtils.getBoolean(env, "kdo.channel.useConfigFile", false));
		dsc.setIncludeDefaultPrivateKeyLocations(SpringUtils.getBoolean(env, "kdo.channel.includeDefaultPrivateKeyLocations", false));
		dsc.setPrivateKeyStrings(Arrays.asList(SpringUtils.getProperty(env, "kdo.channel.privateKey")));
		return dsc;
	}

	@Bean
	public ApplicationServer kdoApplicationServer() {
		TomcatApplicationServer dtc = new TomcatApplicationServer();
		dtc.setChannel(kdoSecureChannel());
		dtc.setUsername(SpringUtils.getProperty(env, "tomcat.user"));
		dtc.setShutdown(SpringUtils.getProperty(env, "tomcat.shutdown"));
		dtc.setStartup(SpringUtils.getProperty(env, "tomcat.startup"));
		return dtc;
	}

	@Bean
	public List<String> kdoFilesToDelete() {
		String lib = SpringUtils.getProperty(env, "tomcat.lib");
		List<String> list = new ArrayList<String>();
		list.add(lib + "/mysql*.jar");
		list.add(lib + "/ojdbc*.jar");
		list.add(lib + "/classes12*.jar");
		list.add(lib + "/orai18n*.jar");
		list.add(SpringUtils.getProperty(env, "tomcat.setenv"));
		list.add(SpringUtils.getProperty(env, "appdynamics.sa.controller"));
		list.add(SpringUtils.getProperty(env, "appdynamics.ma.controller"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.org"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.org.alt"));
		return list;
	}

	@Bean
	public List<String> kdoDirectoriesToDelete() {
		List<String> list = new ArrayList<String>();
		list.add(SpringUtils.getProperty(env, "tomcat.logs"));
		list.add(SpringUtils.getProperty(env, "tomcat.webapps"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.org"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.org.alt"));
		list.add(SpringUtils.getProperty(env, "tomcat.work"));
		list.add(SpringUtils.getProperty(env, "tomcat.conf.catalina"));
		list.add(SpringUtils.getProperty(env, "appdynamics.ma.tmp"));
		list.add(SpringUtils.getProperty(env, "appdynamics.ma.logs"));
		return list;
	}

	@Bean
	public List<String> kdoDirectoriesToCreate() {
		List<String> list = new ArrayList<String>();
		list.add(SpringUtils.getProperty(env, "tomcat.logs"));
		list.add(SpringUtils.getProperty(env, "tomcat.webapps"));
		list.add(SpringUtils.getProperty(env, "tomcat.conf.catalina"));
		list.add(SpringUtils.getProperty(env, "appdynamics.ma.tmp"));
		list.add(SpringUtils.getProperty(env, "appdynamics.ma.logs"));
		return list;
	}

	@Bean
	public List<String> kdoDirectoriesToChown() {
		List<String> list = new ArrayList<String>();
		list.add(SpringUtils.getProperty(env, "tomcat.base"));
		list.add(SpringUtils.getProperty(env, "tomcat.home"));
		list.add(SpringUtils.getProperty(env, "appdynamics.sa.base"));
		list.add(SpringUtils.getProperty(env, "appdynamics.ma.base"));
		return list;
	}

	@Bean
	public Deployable kdoSetEnv() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "tomcat.setenv"));
		d.setLocal(SpringUtils.getProperty(env, "tomcat.setenv.local"));
		d.setFilter(true);
		d.setPermissions("755");
		return d;
	}

	@Bean
	public Deployable kdoAppDynamicsServerAgent() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "appdynamics.sa.controller"));
		d.setLocal(SpringUtils.getProperty(env, "appdynamics.sa.controller.local"));
		d.setFilter(true);
		return d;
	}

	@Bean
	public Deployable kdoAppDynamicsMachineAgent() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "appdynamics.ma.controller"));
		d.setLocal(SpringUtils.getProperty(env, "appdynamics.ma.controller.local"));
		d.setFilter(true);
		return d;
	}

	@Bean
	public Deployable kdoJspEnv() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "tomcat.jsp.env"));
		d.setLocal(SpringUtils.getProperty(env, "tomcat.jsp.env.local"));
		return d;
	}

	@Bean
	public Deployable kdoJspTail() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "tomcat.jsp.tail"));
		d.setLocal(SpringUtils.getProperty(env, "tomcat.jsp.tail.local"));
		return d;
	}

	@Bean
	public String kdoJdbcDriverFilename() {
		Artifact jdbcDriver = kdoJdbcDriverArtifact();

		ArtifactFilenameFactoryBean factory = new ArtifactFilenameFactoryBean();
		factory.setGroupId(jdbcDriver.getGroupId());
		factory.setArtifactId(jdbcDriver.getArtifactId());
		factory.setVersion(jdbcDriver.getVersion());
		factory.setType(jdbcDriver.getType());
		return factory.getObject();
	}

	@Bean
	public String kdoJdbcDriverPath() {
		Artifact jdbcDriver = kdoJdbcDriverArtifact();
		ArtifactPathFactoryBean factory = new ArtifactPathFactoryBean();
		factory.setGroupId(jdbcDriver.getGroupId());
		factory.setArtifactId(jdbcDriver.getArtifactId());
		factory.setVersion(jdbcDriver.getVersion());
		factory.setType(jdbcDriver.getType());
		return factory.getObject();
	}

	@Bean
	public String kdoApplicationPath() {
		Artifact app = kdoApplicationArtifact();
		ArtifactPathFactoryBean factory = new ArtifactPathFactoryBean();
		factory.setGroupId(app.getGroupId());
		factory.setArtifactId(app.getArtifactId());
		factory.setVersion(app.getVersion());
		factory.setType(app.getType());
		factory.setMustExist(true);
		return factory.getObject();
	}

	@Bean
	public Deployable kdoJdbcDriver() {
		String lib = SpringUtils.getProperty(env, "tomcat.lib");
		Deployable d = new Deployable();
		d.setRemote(lib + "/" + kdoJdbcDriverFilename());
		d.setLocal(kdoJdbcDriverPath());
		return d;
	}

	@Bean
	public Deployable kdoApplication() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "tomcat.root.war"));
		d.setLocal(kdoApplicationPath());
		return d;
	}

	@Bean
	public Deployable kdoConfig() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "kdo.config"));
		d.setLocal(SpringUtils.getProperty(env, "kdo.config.local"));
		d.setFilter(SpringUtils.getBoolean(env, "kdo.config.filter", true));
		return d;
	}

	@Bean
	public List<Deployable> kdoDeployables() {
		List<Deployable> list = new ArrayList<Deployable>();
		list.add(kdoSetEnv());
		list.add(kdoAppDynamicsServerAgent());
		list.add(kdoAppDynamicsMachineAgent());
		list.add(kdoJspEnv());
		list.add(kdoJspTail());
		list.add(kdoConfig());
		list.add(kdoJdbcDriver());
		list.add(kdoApplication());
		return list;
	}

	@Bean
	public FileSystem kdoFileSystemHandler() {
		DefaultFileSystem h = new DefaultFileSystem();
		h.setChannel(kdoSecureChannel());
		h.setFilesToDelete(kdoFilesToDelete());
		h.setDirectoriesToDelete(kdoDirectoriesToDelete());
		h.setDirectoriesToCreate(kdoDirectoriesToCreate());
		h.setDirectoriesToChown(kdoDirectoriesToChown());
		h.setDeployables(kdoDeployables());
		h.setProperties(SpringUtils.getAllEnumerableProperties(env));
		h.setOwner(SpringUtils.getProperty(env, "tomcat.owner"));
		h.setGroup(SpringUtils.getProperty(env, "tomcat.group"));
		return h;
	}

	@Bean
	public Executable kdoHttpWaitExecutable() {
		// Extract properties from the environment
		Long overallTimeoutMillis = SpringUtils.getMillis(env, "http.timeout", "30m");
		Long requestTimeoutMillis = SpringUtils.getMillis(env, "http.requestTimeout", "15s");
		String url = SpringUtils.getProperty(env, "public.url");
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

	@Bean
	public Monitoring kdoMonitoring() {
		boolean enabled = SpringUtils.getBoolean(env, "monitoring.enabled", false);
		if (enabled) {
			AppDynamicsMonitoring adm = new AppDynamicsMonitoring();
			adm.setMachineAgentCommand(SpringUtils.getProperty(env, "appdynamics.ma.cmd"));
			adm.setUser(SpringUtils.getProperty(env, "tomcat.user"));
			adm.setChannel(kdoSecureChannel());
			return adm;
		} else {
			return new NoOpMonitoring();
		}
	}

	@Bean(initMethod = "deploy")
	public DeployService kdoDeployService() {
		DefaultDeployService dds = new DefaultDeployService();
		dds.setChannel(kdoSecureChannel());
		dds.setMonitoring(kdoMonitoring());
		dds.setAppServer(kdoApplicationServer());
		dds.setDatabaseResetExecutable(sqlController.sqlExecutable());
		dds.setFileSystem(kdoFileSystemHandler());
		dds.setContext(kdoContext());
		dds.setHttpWaitExecutable(kdoHttpWaitExecutable());
		return dds;
	}

}
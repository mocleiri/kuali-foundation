package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.deploy.AppServerController;
import org.kuali.common.deploy.DefaultDeployService;
import org.kuali.common.deploy.DefaultFileSystemHandler;
import org.kuali.common.deploy.DefaultTomcatController;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.DeployService;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.deploy.FileSystemHandler;
import org.kuali.common.deploy.SpringDatabaseHandler;
import org.kuali.common.util.Artifact;
import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.spring.ArtifactFilenameFactoryBean;
import org.kuali.common.util.spring.ArtifactPathFactoryBean;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class DeployConfig {

	@Autowired
	ConfigurableEnvironment env;

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
		a.setClassifier(SpringUtils.getProperty(env, "project.version", "NONE"));
		a.setType("war");
		return a;
	}

	@Bean
	public DeployContext kdoContext() {
		DeployContext ctx = new DeployContext();
		ctx.setEnvironment(SpringUtils.getProperty(env, "deploy.env"));
		ctx.setHostname(SpringUtils.getProperty(env, "deploy.hostname"));
		ctx.setUsername(SpringUtils.getProperty(env, "deploy.username"));
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
	public AppServerController kdoController() {
		DefaultTomcatController dtc = new DefaultTomcatController();
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
		list.add(SpringUtils.getProperty(env, "appdynamics.controller"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.kuali"));
		return list;
	}

	@Bean
	public List<String> kdoDirectoriesToDelete() {
		List<String> list = new ArrayList<String>();
		list.add(SpringUtils.getProperty(env, "tomcat.logs"));
		list.add(SpringUtils.getProperty(env, "tomcat.webapps"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.kuali"));
		list.add(SpringUtils.getProperty(env, "tomcat.work"));
		list.add(SpringUtils.getProperty(env, "tomcat.conf.catalina"));
		return list;
	}

	@Bean
	public List<String> kdoDirectoriesToCreate() {
		List<String> list = new ArrayList<String>();
		list.add(SpringUtils.getProperty(env, "tomcat.logs"));
		list.add(SpringUtils.getProperty(env, "tomcat.webapps"));
		list.add(SpringUtils.getProperty(env, "tomcat.home.kuali"));
		list.add(SpringUtils.getProperty(env, "tomcat.conf.catalina"));
		return list;
	}

	@Bean
	public List<String> kdoDirectoriesToChown() {
		List<String> list = new ArrayList<String>();
		list.add(SpringUtils.getProperty(env, "tomcat.base"));
		list.add(SpringUtils.getProperty(env, "tomcat.home"));
		list.add(SpringUtils.getProperty(env, "appdynamics.base"));
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
	public Deployable kdoAppDynamics() {
		Deployable d = new Deployable();
		d.setRemote(SpringUtils.getProperty(env, "appdynamics.controller"));
		d.setLocal(SpringUtils.getProperty(env, "appdynamics.controller.local"));
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
		list.add(kdoAppDynamics());
		list.add(kdoJspEnv());
		list.add(kdoJspTail());
		list.add(kdoConfig());
		list.add(kdoJdbcDriver());
		list.add(kdoApplication());
		return list;
	}

	@Bean
	public FileSystemHandler kdoHandler() {
		DefaultFileSystemHandler h = new DefaultFileSystemHandler();
		h.setChannel(kdoSecureChannel());
		h.setFilesToDelete(kdoFilesToDelete());
		h.setDirectoriesToDelete(kdoDirectoriesToDelete());
		h.setDirectoriesToCreate(kdoDirectoriesToCreate());
		h.setDirectoriesToChown(kdoDirectoriesToChown());
		h.setDeployables(kdoDeployables());

		// TODO set this correctly
		h.setProperties(SpringUtils.getAllEnumerableProperties(env));

		h.setOwner(SpringUtils.getProperty(env, "tomcat.owner"));
		h.setGroup(SpringUtils.getProperty(env, "tomcat.group"));

		return h;
	}

	@Bean
	public SpringDatabaseHandler kdoDatabaseHandler() {
		SpringDatabaseHandler h = new SpringDatabaseHandler();

		// TODO Set these correctly
		h.setContextLocation(null);
		h.setBeanNames(null);
		h.setBeanNames(null);

		h.setSkip(SpringUtils.getBoolean(env, "kdo.db.skip", true));
		return h;
	}

	@Bean(initMethod = "deploy")
	public DeployService kdoDeployService() {
		DefaultDeployService s = new DefaultDeployService();
		s.setChannel(kdoSecureChannel());
		s.setController(kdoController());
		s.setHandler(kdoHandler());
		s.setDatabaseHandler(kdoDatabaseHandler());
		s.setContext(kdoContext());
		return s;
	}

}
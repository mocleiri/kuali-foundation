package org.kuali.common.deploy.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.deploy.AppServerController;
import org.kuali.common.deploy.DefaultTomcatController;
import org.kuali.common.deploy.DeployContext;
import org.kuali.common.util.Artifact;
import org.kuali.common.util.secure.DefaultSecureChannel;
import org.kuali.common.util.secure.SecureChannel;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DeployConfig {

	@Autowired
	Environment env;

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

		// TODO Set this appropriately
		ctx.setConfig(null);

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
		dsc.setIncludeDefaultPrivateKeyLocations(SpringUtils.getBoolean(env, "kdo.channel.useConfigFile", false));
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

}

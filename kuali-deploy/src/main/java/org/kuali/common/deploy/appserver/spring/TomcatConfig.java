package org.kuali.common.deploy.appserver.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.deploy.DeployContext;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.deploy.appserver.ApplicationServer;
import org.kuali.common.deploy.appserver.TomcatApplicationServer;
import org.kuali.common.deploy.channel.spring.DefaultSecureChannelConfig;
import org.kuali.common.deploy.spring.DefaultDeployContextConfig;
import org.kuali.common.http.HttpContext;
import org.kuali.common.http.HttpWaitExecutable;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.maven.LocalRepositoryService;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.maven.spring.MavenServiceConfig;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ SpringServiceConfig.class, DefaultDeployContextConfig.class, MavenServiceConfig.class, DefaultSecureChannelConfig.class })
public class TomcatConfig implements ApplicationServerConfig {

	@Autowired
	EnvironmentService env;

	@Autowired
	ConfigurableEnvironment configurableEnvironment;

	@Autowired
	SecureChannel channel;

	@Autowired
	LocalRepositoryService localRepositoryService;

	@Autowired
	DeployContext context;

	@Override
	@Bean
	public ApplicationServer applicationServer() {
		return getApplicationServer();
	}

	protected Deployable getSetEnv() {
		String permissions = env.getString("tomcat.setenv.perms", "755");
		String local = env.getString("tomcat.setenv.local");
		String remote = env.getString("tomcat.setenv");
		return new Deployable.Builder(local, remote).permissions(permissions).build();
	}

	protected List<Deployable> getJsps() {
		String envLocal = env.getString("tomcat.jsp.env.local");
		String envRemote = env.getString("tomcat.jsp.env");
		Deployable envJsp = new Deployable.Builder(envLocal, envRemote).build();

		String tailLocal = env.getString("tomcat.jsp.tail.local");
		String tailRemote = env.getString("tomcat.jsp.tail");
		Deployable tailJsp = new Deployable.Builder(tailLocal, tailRemote).build();

		List<Deployable> jsps = new ArrayList<Deployable>();
		jsps.add(envJsp);
		jsps.add(tailJsp);
		return jsps;
	}

	protected List<String> getTomcatDeletes() {
		// /usr/local/tomcat/lib
		String lib = env.getString("tomcat.lib");

		// rm -rf gets invoked on all of these
		List<String> pathsToDelete = new ArrayList<String>();
		pathsToDelete.add(lib + "/mysql*.jar");
		pathsToDelete.add(lib + "/ojdbc*.jar");
		pathsToDelete.add(lib + "/classes12*.jar");
		pathsToDelete.add(lib + "/orai18n*.jar");
		pathsToDelete.add(lib + "/spring*.jar"); // Rice requires a Spring jar file in Tomcat lib now.
		pathsToDelete.add(env.getString("tomcat.setenv"));
		pathsToDelete.add(env.getString("tomcat.home.org")); // Shouldn't really be needed anymore, leftover from some OLE shenanigans
		pathsToDelete.add(env.getString("tomcat.home.org.alt")); // Shouldn't really be needed anymore, leftover from some OLE shenanigans
		pathsToDelete.add(env.getString("tomcat.conf.catalina"));
		pathsToDelete.add(env.getString("tomcat.logs"));
		pathsToDelete.add(env.getString("tomcat.webapps"));
		pathsToDelete.add(env.getString("tomcat.work"));
		pathsToDelete.add(env.getString("tomcat.temp"));
		return pathsToDelete;
	}

	protected List<String> getTomcatCreates() {
		// mkdir -p gets invoked on all of these
		List<String> dirsToCreate = new ArrayList<String>();
		dirsToCreate.add(env.getString("tomcat.logs"));
		dirsToCreate.add(env.getString("tomcat.webapps"));
		dirsToCreate.add(env.getString("tomcat.conf.catalina"));
		dirsToCreate.add(env.getString("tomcat.temp"));
		return dirsToCreate;
	}

	protected List<String> getTomcatChowns() {
		// chown -L -R gets invoked on all of these
		List<String> pathsToChown = new ArrayList<String>();
		pathsToChown.add(env.getString("tomcat.base")); // Default value is /usr/local/tomcat
		pathsToChown.add(env.getString("tomcat.home")); // Default value is /home/tomcat
		return pathsToChown;
	}

	protected String getJdbcDriverPath() {
		File file = localRepositoryService.getFile(context.getJdbcDriver());
		return new CanonicalFile(file).getPath();
	}

	protected Deployable getJdbcDriver() {
		String lib = env.getString("tomcat.lib");
		String local = getJdbcDriverPath();
		String remote = lib + "/" + RepositoryUtils.getFilename(context.getJdbcDriver());
		return new Deployable.Builder(local, remote).build();
	}

	protected List<Deployable> getTomcatDeployables() {
		// Tomcat related files that get deployed
		List<Deployable> deployables = new ArrayList<Deployable>();
		deployables.add(getSetEnv());
		deployables.addAll(getJsps());
		deployables.addAll(context.getConfigFiles());
		deployables.add(getJdbcDriver());
		return deployables;
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
		boolean skipWar = env.getBoolean("tomcat.war.skip", false);
		if (!skipWar) {
			deployables.add(getApplication());
		}

		// If true, skip transferring all tomcat related files to the remote machine
		boolean skipFiles = env.getBoolean("tomcat.files.skip", false);

		// Setup Tomcat with what it needs to stop/prepare/start correctly
		TomcatApplicationServer tomcat = new TomcatApplicationServer();
		tomcat.setChannel(channel);
		tomcat.setUsername(env.getString("tomcat.user"));
		tomcat.setGroup(env.getString("tomcat.group"));
		tomcat.setShutdown(env.getString("tomcat.shutdown"));
		tomcat.setStartup(env.getString("tomcat.startup"));
		tomcat.setPathsToDelete(pathsToDelete);
		tomcat.setDirsToCreate(dirsToCreate);
		tomcat.setDeployables(deployables);
		tomcat.setPathsToChown(pathsToChown);
		tomcat.setSkipFiles(skipFiles);
		tomcat.setFilterProperties(PropertySourceUtils.getAllEnumerableProperties(configurableEnvironment));
		tomcat.setHttpWait(getHttpWaitExecutable());
		return tomcat;
	}

	protected HttpWaitExecutable getHttpWaitExecutable() {
		// Extract properties from the environment
		Long overallTimeoutMillis = SpringUtils.getMillis(env, "http.overallTimeout", "30m");
		Long requestTimeoutMillis = SpringUtils.getMillis(env, "http.requestTimeout", "15s");
		String url = env.getString("http.url");
		boolean skip = env.getBoolean("http.wait.skip", false);

		// Setup the context
		HttpContext context = new HttpContext();
		context.setUrl(url);
		context.setOverallTimeoutMillis(overallTimeoutMillis.intValue());
		context.setRequestTimeoutMillis(requestTimeoutMillis.intValue());
		context.setLogMsgPrefix(env.getString("http.logMsgPrefix", "[tomcat:starting]"));

		// Setup the executable
		HttpWaitExecutable executable = new HttpWaitExecutable();
		executable.setContext(context);
		executable.setSkip(skip);
		return executable;
	}

	protected String getApplicationPath() {
		Artifact app = context.getApplication();
		File file = localRepositoryService.getFile(app);
		return new CanonicalFile(file).getAbsolutePath();
	}

	protected Deployable getApplication() {
		String local = getApplicationPath();
		String remote = env.getString("tomcat.root.war");
		return new Deployable.Builder(local, remote).build();
	}

}

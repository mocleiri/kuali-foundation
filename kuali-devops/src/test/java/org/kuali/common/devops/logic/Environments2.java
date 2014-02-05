package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.FormatUtils.getTime;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Scm;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Environments2 {

	private static final String DEPLOY_SERVER_PREFIX = "env";
	private static final Logger logger = Loggers.make();
	private static final File CACHE_DIR = new CanonicalFile("./target/envs/data");

	public static SortedMap<String, List<Environment>> getEnvironments(boolean refresh) {
		SortedMap<String, List<Environment.Builder>> builders = getBuilders(refresh);
		SortedMap<String, List<Environment>> map = Maps.newTreeMap();
		for (String group : builders.keySet()) {
			List<Environment.Builder> list = builders.get(group);
			List<Environment> envs = Lists.newArrayList();
			for (Environment.Builder builder : list) {
				envs.add(builder.build());
			}
			map.put(group, envs);
			store(group, envs);
		}
		return map;
	}

	protected static File getEnvCacheDir(String group, Environment env) {
		File groupDir = new CanonicalFile(CACHE_DIR, group);
		return new CanonicalFile(groupDir, env.getName());
	}

	protected static void store(String group, List<Environment> envs) {
		for (Environment env : envs) {
			store(env, getEnvCacheDir(group, env));
		}
	}

	protected static void store(Environment env, File dir) {
		PropertyUtils.store(convert(env), new CanonicalFile(dir, "environment.properties"));
		if (env.getApplication().isPresent()) {
			store(env.getApplication().get(), dir);
		}
	}

	protected static void store(Application app, File dir) {
		PropertyUtils.storeSilently(PropertyUtils.convert(app.getManifest()), new CanonicalFile(dir, "manifest.properties"));
		PropertyUtils.storeSilently(PropertyUtils.convert(app.getConfiguration()), new CanonicalFile(dir, "config.properties"));
		PropertyUtils.storeSilently(app.getProject().getProperties(), new CanonicalFile(dir, "project.properties"));
	}

	protected static Application load(File dir) {
		Properties manifest = PropertyUtils.loadOrCreateSilently(new CanonicalFile(dir, "manifest.properties").getPath());
		Properties config = PropertyUtils.loadOrCreateSilently(new CanonicalFile(dir, "config.properties").getPath());
		Properties project = PropertyUtils.loadOrCreateSilently(new CanonicalFile(dir, "project.properties").getPath());
		Project p = ProjectUtils.getProject(project);
		Optional<Database> database = Databases.getDatabase(p.getGroupId(), config);
		Optional<Scm> scm = Applications.getScm(project);
		return Application.create(p, manifest, config, database, scm);
	}

	protected static Properties convert(Environment env) {
		Properties props = new Properties();
		props.setProperty("env.name", env.getName());
		props.setProperty("env.fqdn", env.getFqdn());
		if (env.getJava().isPresent()) {
			props.setProperty("java.version", env.getJava().get());
		}
		if (env.getTomcat().isPresent()) {
			Tomcat tomcat = env.getTomcat().get();
			Optional<Long> startupTime = tomcat.getStartupTime();
			props.setProperty("tomcat.version", tomcat.getVersion());
			if (startupTime.isPresent()) {
				props.setProperty("tomcat.startupTime", startupTime.get() + "");
			}
		}
		return props;
	}

	protected static List<String> getTokens(Optional<Tomcat> optional) {
		if (optional.isPresent()) {
			Tomcat tomcat = optional.get();
			return ImmutableList.of(tomcat.getVersion(), tomcat.getStartupTime() + "");
		} else {
			List<String> list = Lists.newArrayList();
			list.add(null);
			list.add(null);
			return list;
		}
	}

	protected static SortedMap<String, List<Environment.Builder>> getBuilders(boolean refresh) {
		long start = System.currentTimeMillis();
		BiMap<String, String> aliases = DNS.getUnambiguousCNAMERecords(refresh);
		BiMap<String, String> cnames = aliases.inverse();
		Map<String, List<EC2Instance>> instances = Instances.getInstances(refresh);
		SortedMap<String, List<Environment.Builder>> map = Maps.newTreeMap();
		int count = 0;
		for (String group : instances.keySet()) {
			List<EC2Instance> servers = instances.get(group);
			List<Environment.Builder> builders = getBuilders(servers, cnames);
			fillIn(builders);
			count += builders.size();
			map.put(group, builders);
		}
		logger.info(format("located information on %s environments - %s", count, getTime(currentTimeMillis() - start)));
		return map;
	}

	protected static void fillIn(List<Environment.Builder> builders) {
		for (Environment.Builder builder : builders) {
			// long start = System.currentTimeMillis();
			// System.out.print(rightPad(format("examining -> [%s]", builder.getFqdn()), 45));
			builder.setJava(Examiner.getJavaVersion(builder.getFqdn()));
			builder.setTomcat(Tomcats.getTomcat(builder.getFqdn()));
			builder.setApplication(Applications.getApplication(builder.getFqdn()));
			// System.out.println(format(" - %s", FormatUtils.getTime(currentTimeMillis() - start)));
		}
	}

	protected static List<Environment.Builder> getBuilders(List<EC2Instance> instances, BiMap<String, String> cnames) {
		List<EC2Instance> servers = getDeployServers(instances);
		List<Environment.Builder> builders = Lists.newArrayList();
		for (EC2Instance server : servers) {
			Environment.Builder builder = getBuilder(server, cnames);
			builders.add(builder);
		}
		Collections.sort(builders);
		return builders;
	}

	protected static Environment.Builder getBuilder(EC2Instance server, BiMap<String, String> cnames) {
		String publicDnsName = server.getPublicDnsName().get();
		String fqdn = cnames.get(publicDnsName);
		checkState(!isBlank(fqdn), "no fqdn -> [%s]", publicDnsName);
		return Environment.builder().fqdn(fqdn).server(server).name(server.getName().get());
	}

	protected static List<EC2Instance> getDeployServers(List<EC2Instance> instances) {
		List<EC2Instance> list = Lists.newArrayList();
		for (EC2Instance instance : instances) {
			if (isDeployServer(instance)) {
				checkState(instance.getPublicDnsName().isPresent(), "no public dns name -> [%s]", instance.getId());
				list.add(instance);
			}
		}
		return list;
	}

	protected static boolean isDeployServer(EC2Instance instance) {
		return instance.getName().isPresent() && instance.getName().get().startsWith(DEPLOY_SERVER_PREFIX);
	}

}

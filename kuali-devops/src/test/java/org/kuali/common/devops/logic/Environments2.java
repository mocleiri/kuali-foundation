package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.kuali.common.util.FormatUtils.getTime;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;

import org.kuali.common.devops.metadata.logic.DefaultEnvironmentMetadataService;
import org.kuali.common.devops.metadata.logic.EnvironmentMetadataService;
import org.kuali.common.devops.metadata.model.EC2Instance;
import org.kuali.common.devops.metadata.model.EnvironmentMetadata;
import org.kuali.common.devops.metadata.model.MetadataUrl;
import org.kuali.common.devops.metadata.model.RemoteEnvironment;
import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Scm;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.collect.BiMap;
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
		}
		return map;
	}

	protected static File getEnvCacheDir(String group, String environment) {
		File groupDir = new CanonicalFile(CACHE_DIR, group);
		return new CanonicalFile(groupDir, environment);
	}

	protected static File getEnvironmentCacheFile(String group, String environment) {
		return new CanonicalFile(getEnvCacheDir(group, environment), "environment.properties");
	}

	protected static void fillIn(String group, Environment.Builder builder, File dir) {
		File cache = getEnvironmentCacheFile(group, builder.getName());
		Properties props = PropertyUtils.loadSilently(cache);
		Optional<Application> app = getApplication(dir);
		builder.setJava(Optional.fromNullable(props.getProperty("java.version")));
		builder.setTomcat(getTomcat(props));
		builder.setApplication(app);
	}

	protected static Optional<Tomcat> getTomcat(Properties props) {
		String version = props.getProperty("tomcat.version");
		String startupTime = props.getProperty("tomcat.startupTime");
		if (version == null) {
			return Optional.absent();
		}
		Tomcat.Builder builder = Tomcat.builder().version(version);
		if (startupTime != null) {
			builder.startupTime(Long.parseLong(startupTime.trim()));
		}
		return Optional.of(builder.build());

	}

	protected static Optional<Application> getApplication(File dir) {
		Properties manifest = PropertyUtils.loadOrCreateSilently(new CanonicalFile(dir, "manifest.properties").getPath());
		Properties config = PropertyUtils.loadOrCreateSilently(new CanonicalFile(dir, "config.properties").getPath());
		Properties project = PropertyUtils.loadOrCreateSilently(new CanonicalFile(dir, "project.properties").getPath());
		if (project.isEmpty()) {
			return Optional.absent();
		}
		Project p = ProjectUtils.getProject(project);
		Optional<Database> database = Databases.getDatabase(p.getGroupId(), config);
		Optional<Scm> scm = Applications.getScm(project);
		return Optional.of(Application.create(p, manifest, config, database, scm));
	}

	public static SortedMap<String, List<Environment.Builder>> getBuilders(boolean refresh) {
		long start = System.currentTimeMillis();
		BiMap<String, String> cnames = DNS.getCanonicalMap(refresh);
		Map<String, List<EC2Instance>> instances = Instances.getInstances(refresh);
		SortedMap<String, List<Environment.Builder>> map = Maps.newTreeMap();
		EnvironmentMetadataService service = new DefaultEnvironmentMetadataService();
		int count = 0;
		for (String group : instances.keySet()) {
			List<EC2Instance> servers = instances.get(group);
			List<Environment.Builder> builders = getBuilders(group, servers, cnames);
			fillIn(group, builders, service, refresh);
			count += builders.size();
			map.put(group, builders);
		}
		logger.info(format("located information on %s environments - %s", count, getTime(currentTimeMillis() - start)));
		return map;
	}

	protected static void fillIn(String group, List<Environment.Builder> builders, EnvironmentMetadataService service, boolean refresh) {
		for (Environment.Builder builder : builders) {
			Stopwatch sw = Stopwatch.createStarted();
			EnvironmentMetadata metadata = service.getMetadata(builder.getFqdn());
			logger.info(format("examined -> %s - %s", builder.getFqdn(), FormatUtils.getTime(sw)));
			builder.tomcat(getTomcat(metadata));
			builder.java(getJava(metadata));
			builder.application(getApplication(metadata));
		}
	}

	protected static Optional<Application> getApplication(EnvironmentMetadata meta) {
		Properties manifest = getMetadata(meta.getManifest(), new Properties());
		Properties config = getMetadata(meta.getConfig(), new Properties());

		Optional<Project> optional = getMetadata(meta.getProject());
		if (!optional.isPresent()) {
			return Optional.<Application> absent();
		}
		Project parsedProject = optional.get();
		Project project = Projects.getProjectWithAccurateSCMInfo(parsedProject, manifest);

		Application.Builder builder = Application.builder();
		builder.setProject(project);
		builder.setManifest(manifest);
		builder.setConfiguration(config);

		Optional<Database> database = Databases.getDatabase(project.getGroupId(), builder.getConfiguration());
		Optional<Scm> scm = Applications.getScm(project.getProperties());

		builder.setDatabase(database);
		builder.setScm(scm);

		return Optional.of(builder.build());
	}

	protected static Optional<String> getJava(EnvironmentMetadata meta) {
		Optional<RemoteEnvironment> remoteEnvironment = getMetadata(meta.getRemoteEnvironment());
		if (!remoteEnvironment.isPresent()) {
			return Optional.<String> absent();
		}
		Properties systemProperties = remoteEnvironment.get().getSystem();
		return fromNullable(systemProperties.getProperty("java.version"));
	}

	protected static Optional<Tomcat> getTomcat(EnvironmentMetadata meta) {
		Optional<String> version = getOptionalMetadata(meta.getTomcatVersion());
		Optional<Long> startup = getOptionalMetadata(meta.getTomcatStartupTime());
		if (!version.isPresent()) {
			return Optional.<Tomcat> absent();
		}
		Tomcat.Builder builder = Tomcat.builder().version(version.get());
		if (startup.isPresent()) {
			builder.startupTime(startup.get());
		}
		return Optional.of(builder.build());
	}

	protected static List<Environment.Builder> getBuilders(String group, List<EC2Instance> instances, BiMap<String, String> cnames) {
		List<EC2Instance> servers = getDeployServers(group, instances, cnames);
		List<Environment.Builder> builders = Lists.newArrayList();
		for (EC2Instance server : servers) {
			Environment.Builder builder = getBuilder(group, server, cnames);
			builders.add(builder);
		}
		Collections.sort(builders);
		return builders;
	}

	protected static String getFqdnForDeployServer(String group, EC2Instance server, BiMap<String, String> cnames) {
		String publicDnsName = server.getPublicDnsName().get();
		Optional<String> alias = fromNullable(cnames.get(publicDnsName));
		if (alias.isPresent()) {
			return alias.get();
		} else {
			logger.warn(format("no cname alias -> [%s::%s::%s::%s]", group, server.getName().get(), server.getId(), server.getPublicDnsName().get()));
			return publicDnsName;
		}
	}

	protected static Environment.Builder getBuilder(String group, EC2Instance server, BiMap<String, String> cnames) {
		String fqdn = getFqdnForDeployServer(group, server, cnames);
		return Environment.builder().fqdn(fqdn).server(server).name(server.getName().get());
	}

	protected static List<EC2Instance> getDeployServers(String group, List<EC2Instance> instances, BiMap<String, String> cnames) {
		List<EC2Instance> list = Lists.newArrayList();
		for (EC2Instance instance : instances) {
			if (isActiveDeployServer(instance)) {
				list.add(instance);
			}
		}
		return list;
	}

	/**
	 * Returns true if the instance has been tagged with [Name=env] AND it has a public DNS name
	 */
	protected static boolean isActiveDeployServer(EC2Instance instance) {
		return instance.getName().isPresent() && instance.getName().get().startsWith(DEPLOY_SERVER_PREFIX) && instance.getPublicDnsName().isPresent();
	}

	protected static <T> Optional<T> getMetadata(Optional<MetadataUrl<T>> optional) {
		if (!optional.isPresent()) {
			return Optional.<T> absent();
		} else {
			MetadataUrl<T> url = optional.get();
			if (url.getMetadata().isPresent()) {
				return url.getMetadata();
			} else {
				return Optional.<T> absent();
			}
		}
	}

	protected static <T> T getMetadata(Optional<MetadataUrl<T>> optional, T provided) {
		if (!optional.isPresent()) {
			return provided;
		} else {
			MetadataUrl<T> url = optional.get();
			if (url.getMetadata().isPresent()) {
				Optional<T> parsed = url.getMetadata();
				if (parsed.isPresent()) {
					return parsed.get();
				} else {
					return provided;
				}
			} else {
				return provided;
			}
		}
	}

	protected static <T> T getOptionalMetadata(MetadataUrl<Optional<T>> url, T provided) {
		if (url.getMetadata().isPresent()) {
			Optional<T> parsed = url.getMetadata().get();
			if (parsed.isPresent()) {
				return parsed.get();
			}
		}
		return provided;
	}

	protected static <T> Optional<T> getOptionalMetadata(MetadataUrl<Optional<T>> url) {
		if (url.getMetadata().isPresent()) {
			return url.getMetadata().get();
		} else {
			return Optional.<T> absent();
		}
	}

	protected static <T> T getMetadata(MetadataUrl<T> url, T provided) {
		if (url.getMetadata().isPresent()) {
			return url.getMetadata().get();
		}
		return provided;
	}

	protected static <T> Optional<T> getMetadata(MetadataUrl<T> url) {
		if (url.getMetadata().isPresent()) {
			return url.getMetadata();
		}
		return Optional.<T> absent();
	}

}

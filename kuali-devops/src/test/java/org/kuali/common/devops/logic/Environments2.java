package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.kuali.common.util.FormatUtils.getTime;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
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
				Environment env = builder.build();
				envs.add(env);
			}
			map.put(group, envs);
		}
		return map;
	}

	protected static void store(String group, List<Environment> envs) {
		File csv = new CanonicalFile(CACHE_DIR, group + ".txt");

	}

	protected static List<String> getTokens(Environment env) {
		List<String> tokens = Lists.newArrayList();
		tokens.add(env.getName());
		tokens.add(env.getFqdn());
		tokens.add(env.getJava().orNull());
		tokens.addAll(getTokens(env.getTomcat()));
		return tokens;
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
			long start = System.currentTimeMillis();
			System.out.print(rightPad(format("examining -> [%s]", builder.getFqdn()), 45));
			builder.setJava(Examiner.getJavaVersion(builder.getFqdn()));
			builder.setTomcat(Tomcats.getTomcat(builder.getFqdn()));
			builder.setApplication(Applications.getApplication(builder.getFqdn()));
			System.out.println(format(" - %s", FormatUtils.getTime(currentTimeMillis() - start)));
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

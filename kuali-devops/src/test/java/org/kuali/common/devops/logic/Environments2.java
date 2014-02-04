package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.collect.BiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Environments2 {

	private static final String DEPLOY_SERVER_PREFIX = "env";
	private static final Logger logger = Loggers.make();

	public static SortedMap<String, List<Environment.Builder>> getBuilders(boolean refresh) {
		BiMap<String, String> aliases = DNS.getUnambiguousCNAMERecords(refresh);
		Map<String, List<EC2Instance>> instances = Instances.getInstances(refresh);
		SortedMap<String, List<Environment.Builder>> map = Maps.newTreeMap();
		for (String group : instances.keySet()) {
			List<EC2Instance> servers = instances.get(group);
			List<Environment.Builder> builders = getBuilders(servers, aliases);
			fillIn(builders);
			map.put(group, builders);
		}
		return map;
	}

	protected static void fillIn(List<Environment.Builder> builders) {
		for (Environment.Builder builder : builders) {
			logger.info(String.format("examining -> [%s]", builder.getFqdn()));
		}
	}

	protected static List<Environment.Builder> getBuilders(List<EC2Instance> instances, BiMap<String, String> aliases) {
		List<EC2Instance> servers = getDeployServers(instances);
		List<Environment.Builder> builders = Lists.newArrayList();
		for (EC2Instance server : servers) {
			Environment.Builder builder = getBuilder(server, aliases);
			builders.add(builder);
		}
		Collections.sort(builders);
		return builders;
	}

	protected static Environment.Builder getBuilder(EC2Instance server, BiMap<String, String> aliases) {
		Map<String, String> cnames = aliases.inverse();
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

package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.FormatUtils.getTime;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Environments2 {

	private static final String DEPLOY_SERVER_PREFIX = "env";
	private static final Logger logger = Loggers.make();

	public static SortedMap<String, List<Environment.Builder>> getBuilders(boolean refresh) {
		long start = System.currentTimeMillis();
		BiMap<String, String> aliases = DNS.getUnambiguousCNAMERecords(refresh);
		Map<String, List<EC2Instance>> instances = Instances.getInstances(refresh);
		SortedMap<String, List<Environment.Builder>> map = Maps.newTreeMap();
		int count = 0;
		for (String group : instances.keySet()) {
			List<EC2Instance> servers = instances.get(group);
			List<Environment.Builder> builders = getBuilders(servers, aliases);
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
			System.out.print(StringUtils.rightPad(String.format("examining -> [%s]", builder.getFqdn()), 45));
			if (!builder.getFqdn().equals("env5.rice.kuali.org")) {
				builder.setJava(Examiner.getJavaVersion(builder.getFqdn()));
				builder.setTomcat(Tomcats.getTomcat(builder.getFqdn()));
				Optional<Project> project = Projects.getProject(builder.getFqdn());
			}
			System.out.println(String.format(" - %s", FormatUtils.getTime(System.currentTimeMillis() - start)));
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

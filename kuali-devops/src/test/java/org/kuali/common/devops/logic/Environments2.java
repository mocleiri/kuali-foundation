package org.kuali.common.devops.logic;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.kuali.common.devops.model.EC2Instance;
import org.kuali.common.devops.model.Environment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Environments2 {

	public static SortedMap<String, List<Environment.Builder>> getBuilders(boolean refresh) {
		Map<String, String> dns = DNS.getUnambiguousCNAMERecords(refresh);
		Map<String, List<EC2Instance>> instances = Instances.getInstances(refresh);
		SortedMap<String, List<Environment.Builder>> map = Maps.newTreeMap();
		for (String group : instances.keySet()) {
			List<EC2Instance> servers = instances.get(group);
			List<Environment.Builder> builders = getBuilders(servers, dns);
			map.put(group, builders);
		}
		return map;
	}

	protected static List<Environment.Builder> getBuilders(List<EC2Instance> instances, Map<String, String> dns) {
		List<Environment.Builder> builders = Lists.newArrayList();
		return builders;
	}

}

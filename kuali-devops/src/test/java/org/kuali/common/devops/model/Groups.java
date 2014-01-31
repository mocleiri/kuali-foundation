package org.kuali.common.devops.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Groups {

	public static List<Group> getGroups(List<Environment> envs) {
		Map<String, List<Environment>> map = Maps.newTreeMap();
		for (Environment env : envs) {
			List<Environment> groupEnvs = map.get(env.getProject());
			if (groupEnvs == null) {
				groupEnvs = Lists.newArrayList();
				map.put(env.getProject(), groupEnvs);
			}
			groupEnvs.add(env);
		}
		List<Group> groups = Lists.newArrayList();
		for (String key : map.keySet()) {
			List<Environment> groupEnvs = map.get(key);
			Collections.sort(groupEnvs);
			Group group = new Group();
			group.setName(key);
			group.setEnvironments(groupEnvs);
			groups.add(group);
		}
		return groups;
	}
}

package org.kuali.common.devops.util;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Comparator;

public class EnvStringComparator implements Comparator<String> {

	@Override
	public int compare(String env1, String env2) {
		checkArgument(env1.startsWith("env"), "[%s] must start with [env]", env1);
		checkArgument(env2.startsWith("env"), "[%s] must start with [env]", env2);
		Integer i1 = Integer.parseInt(env1.substring(3));
		Integer i2 = Integer.parseInt(env2.substring(3));
		return Double.compare(i1, i2);
	}

}

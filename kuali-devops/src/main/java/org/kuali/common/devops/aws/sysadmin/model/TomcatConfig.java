package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class TomcatConfig {

	private final List<String> conf = ImmutableList.of("server.xml", "web.xml");
	private final List<String> bin = ImmutableList.of("forced-shutdown.sh", "cleanup.sh");
	private final List<String> jsps = ImmutableList.of("env.jsp", "tail.jsp");

	public List<String> getJsps() {
		return jsps;
	}

	public List<String> getBin() {
		return bin;
	}

	public List<String> getConf() {
		return conf;
	}

}

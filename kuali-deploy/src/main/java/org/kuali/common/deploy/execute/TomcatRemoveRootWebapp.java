package org.kuali.common.deploy.execute;

import java.util.Arrays;

public class TomcatRemoveRootWebapp extends RemoteRm {

	public TomcatRemoveRootWebapp() {
		this(null);
	}

	public TomcatRemoveRootWebapp(String hostname) {
		super();
		this.hostname = hostname;
		this.files = Arrays.asList(Constants.TOMCAT_ROOT_DIR, Constants.TOMCAT_ROOT_WAR);
	}
}

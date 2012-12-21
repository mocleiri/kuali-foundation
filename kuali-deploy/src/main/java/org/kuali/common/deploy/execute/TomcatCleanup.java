package org.kuali.common.deploy.execute;

public class TomcatCleanup extends TomcatBase {

	String script = "/usr/local/tomcat/bin/cleanup.sh";

	@Override
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}

package org.kuali.common.deploy.execute;

public class TomcatStart extends TomcatBase {

	String script = "/usr/local/tomcat/bin/startup.sh";

	@Override
    public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}

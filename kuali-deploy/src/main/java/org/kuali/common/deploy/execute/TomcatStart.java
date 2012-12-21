package org.kuali.common.deploy.execute;

public class TomcatStart extends TomcatBase {

	String script = "/usr/local/tomcat/bin/startup.sh";

	public TomcatStart() {
		this(null);
	}

	public TomcatStart(String hostname) {
		super();
		this.hostname = hostname;
	}

	@Override
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}

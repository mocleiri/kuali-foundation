package org.kuali.maven.plugins.jenkins.helper;

public class SshHelper extends ExecutableHelper {

    public static final String SSH = "ssh";
	
	@Override
	public String getExecutable() {
		return SSH;
	}
}

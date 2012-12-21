package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class TomcatCopyJdbcDriver extends SecureBase implements Executable {

	File jar;
	String tomcatLibDirectory = "/usr/local/tomcat/lib";

	@Override
	public void execute() {
		String file = tomcatLibDirectory + File.separator + jar.getName();
		String destination = UnixUtils.getLocation(user, hostname, file);
		int exitValue = UnixUtils.scp(jar, destination);
		UnixUtils.validate(exitValue, "Error copying JDBC Driver", nonZeroExitValueMode);
	}

	public File getJar() {
		return jar;
	}

	public void setJar(File jar) {
		this.jar = jar;
	}

	public String getTomcatLibDirectory() {
		return tomcatLibDirectory;
	}

	public void setTomcatLibDirectory(String tomcatLibDirectory) {
		this.tomcatLibDirectory = tomcatLibDirectory;
	}

}

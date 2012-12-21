package org.kuali.common.deploy.execute;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Mode;
import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class TomcatDeleteExistingJdbcDrivers implements Executable {

	Mode nonZeroExitValueMode = Mode.ERROR;
	List<String> patterns = Arrays.asList("mysql*.jar", "ojdbc*.jar");
	String tomcatLibDirectory = "/usr/local/tomcat/lib";
	String user;
	String hostname;

	@Override
	public void execute() {
		String fileArgs = UnixUtils.getArguments(patterns);
		int exitValue = UnixUtils.sshrm(user, hostname, fileArgs);
		UnixUtils.validate(exitValue, "Error deleting existing JDBC Drivers", nonZeroExitValueMode);
	}

}

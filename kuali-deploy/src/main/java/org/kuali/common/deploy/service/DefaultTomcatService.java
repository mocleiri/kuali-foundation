package org.kuali.common.deploy.service;

import org.kuali.common.deploy.context.SecureContext;
import org.kuali.common.deploy.context.TomcatContext;
import org.kuali.common.util.Mode;
import org.kuali.common.util.UnixUtils;

public class DefaultTomcatService implements TomcatService {

	@Override
	public void shutdown(SecureContext security, TomcatContext tomcat) {
		int exitValue = UnixUtils.sshsu(security.getUser(), security.getHostname(), tomcat.getUser(), tomcat.getShutdown());
		UnixUtils.validate(exitValue, "Error shutting down tomcat", Mode.WARN);
	}

	@Override
	public void cleanup(SecureContext security, TomcatContext tomcat) {
		int exitValue = UnixUtils.sshsu(security.getUser(), security.getHostname(), tomcat.getUser(), tomcat.getCleanup());
		UnixUtils.validate(exitValue, "Error cleaning up tomcat");
	}

	@Override
	public void startup(SecureContext security, TomcatContext tomcat) {
		int exitValue = UnixUtils.sshsu(security.getUser(), security.getHostname(), tomcat.getUser(), tomcat.getCleanup());
		UnixUtils.validate(exitValue, "Error starting tomcat");
	}

}

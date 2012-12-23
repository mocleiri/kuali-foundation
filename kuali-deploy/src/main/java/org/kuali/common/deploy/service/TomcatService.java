package org.kuali.common.deploy.service;

import org.kuali.common.deploy.context.SecureContext;
import org.kuali.common.deploy.context.TomcatContext;

public interface TomcatService {

	void shutdown(SecureContext security, TomcatContext tomcat);

	void cleanup(SecureContext security, TomcatContext tomcat);

	void startup(SecureContext security, TomcatContext tomcat);

}

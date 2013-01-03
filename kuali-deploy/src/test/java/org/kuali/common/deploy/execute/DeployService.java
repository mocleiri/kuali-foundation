package org.kuali.common.deploy.execute;

public interface DeployService {

	void shutdown();

	void cleanup();

	void prepare();

	void startup();

}

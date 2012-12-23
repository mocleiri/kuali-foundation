package org.kuali.common.deploy.context;

public class DeployContext {

	SecureContext security;
	ApplicationContext application;
	AppDynamicsContext appDynamics;
	TomcatContext tomcat;

	public SecureContext getSecurity() {
		return security;
	}

	public void setSecurity(SecureContext security) {
		this.security = security;
	}

	public ApplicationContext getApplication() {
		return application;
	}

	public void setApplication(ApplicationContext application) {
		this.application = application;
	}

	public AppDynamicsContext getAppDynamics() {
		return appDynamics;
	}

	public void setAppDynamics(AppDynamicsContext appDynamics) {
		this.appDynamics = appDynamics;
	}

	public TomcatContext getTomcat() {
		return tomcat;
	}

	public void setTomcat(TomcatContext tomcat) {
		this.tomcat = tomcat;
	}

}

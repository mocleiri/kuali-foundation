package org.kuali.maven.plugins.spring;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.MethodInvoker;

public class MojoExecutable implements Executable {

	private static final String SERVICE_CALLBACK_METHOD_NAME = "mojoCallback";

	MethodInvoker invoker = new MethodInvoker();
	String serviceMethod = SERVICE_CALLBACK_METHOD_NAME;
	AbstractSpringMojo mojo;
	SpringMojoService service;

	@Override
	public void execute() {
		invoker.setTargetObject(service);
		invoker.setTargetMethod(serviceMethod);
		invoker.setArguments(new Object[] { mojo });
		ReflectionUtils.invoke(invoker);
	}

	public MethodInvoker getInvoker() {
		return invoker;
	}

	public void setInvoker(MethodInvoker invoker) {
		this.invoker = invoker;
	}

	public String getServiceMethod() {
		return serviceMethod;
	}

	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}

	public AbstractSpringMojo getMojo() {
		return mojo;
	}

	public void setMojo(AbstractSpringMojo mojo) {
		this.mojo = mojo;
	}

	public SpringMojoService getService() {
		return service;
	}

	public void setService(SpringMojoService service) {
		this.service = service;
	}

}

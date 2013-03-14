package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

@Component
public class MojoExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(MojoExecutable.class);

	MethodInvoker invoker = new MethodInvoker();
	String targetMethod = "execute";
	AbstractSpringMojo mojo;
	SpringMojoService service;

	@Override
	public void execute() {
		Object[] args = { service.getClass().getSimpleName(), targetMethod, mojo.getClass().getSimpleName() };
		logger.info("Invoking  - [{}.{}({})]", args);
		invoker.setTargetObject(service);
		invoker.setTargetMethod(targetMethod);
		invoker.setArguments(new Object[] { mojo });
		try {
			invoker.prepare();
			invoker.invoke();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		logger.info("Completed - [{}.{}({})]", args);
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

	public MethodInvoker getInvoker() {
		return invoker;
	}

	public void setInvoker(MethodInvoker invoker) {
		this.invoker = invoker;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

}

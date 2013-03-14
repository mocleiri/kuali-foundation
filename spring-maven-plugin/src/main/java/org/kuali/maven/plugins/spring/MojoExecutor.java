package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

@Component
public class MojoExecutor implements Executable {

	MethodInvoker invoker = new MethodInvoker();
	AbstractSpringMojo mojo;
	SpringMojoService service;

	@Override
	public void execute() {
		invoker.setTargetObject(service);
		invoker.setTargetMethod("execute");
		invoker.setArguments(new Object[] { mojo });
		try {
			invoker.invoke();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
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

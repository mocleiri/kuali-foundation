package org.kuali.maven.plugins.spring;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.MethodInvoker;

public class MojoExecutable implements Executable {

	private static final String SERVICE_CALLBACK_METHOD_NAME = "callback";

	private final AbstractSpringMojo mojo;
	private final SpringMojoService service;

	public MojoExecutable(AbstractSpringMojo mojo, SpringMojoService service) {
		Assert.noNulls(mojo, service);
		this.mojo = mojo;
		this.service = service;
	}

	@Override
	public void execute() {
		MethodInvoker invoker = new MethodInvoker();
		invoker.setTargetObject(service);
		invoker.setTargetMethod(SERVICE_CALLBACK_METHOD_NAME);
		invoker.setArguments(new Object[] { mojo });
		ReflectionUtils.invoke(invoker);
	}

	public AbstractSpringMojo getMojo() {
		return mojo;
	}

	public SpringMojoService getService() {
		return service;
	}

}

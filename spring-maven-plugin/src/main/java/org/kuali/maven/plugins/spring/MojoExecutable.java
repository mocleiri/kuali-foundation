package org.kuali.maven.plugins.spring;

import java.lang.reflect.InvocationTargetException;

import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

@Component
public class MojoExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(MojoExecutable.class);

	MethodInvoker invoker = new MethodInvoker();
	String serviceMethod = "mojoCallback";
	AbstractSpringMojo mojo;
	SpringMojoService service;

	@Override
	public void execute() {
		Object[] args = { service.getClass().getName(), serviceMethod, mojo.getClass().getSimpleName() };
		logger.debug("Invoking  - [{}.{}({})]", args);
		invoker.setTargetObject(service);
		invoker.setTargetMethod(serviceMethod);
		invoker.setArguments(new Object[] { mojo });
		invoke(invoker);
		logger.debug("Completed - [{}.{}({})]", args);
	}

	protected void invoke(MethodInvoker invoker) {
		try {
			invoker.prepare();
			invoker.invoke();
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
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

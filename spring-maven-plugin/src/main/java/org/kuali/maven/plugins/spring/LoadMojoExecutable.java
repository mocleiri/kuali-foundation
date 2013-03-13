package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoadMojoExecutable implements Executable {

	@Autowired
	@Qualifier(AbstractSpringMojo.AUTOWIRED_MOJO_QUALIFIER)
	LoadMojo mojo;

	SpringMojoService service;

	@Override
	public void execute() {
		service.execute(mojo);
	}

	public LoadMojo getMojo() {
		return mojo;
	}

	public void setMojo(LoadMojo mojo) {
		this.mojo = mojo;
	}

	public SpringMojoService getService() {
		return service;
	}

	public void setService(SpringMojoService service) {
		this.service = service;
	}

}

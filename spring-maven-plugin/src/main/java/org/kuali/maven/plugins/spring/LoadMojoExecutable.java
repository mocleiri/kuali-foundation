package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.stereotype.Component;

@Component
public class LoadMojoExecutable implements Executable {

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

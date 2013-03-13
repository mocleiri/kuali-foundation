package org.kuali.maven.plugins.spring;

import javax.annotation.PostConstruct;

import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadMojoExecutable implements Executable {

	@Autowired
	LoadMojo mojo;
	LoadMojoService service;

	@Override
	@PostConstruct
	public void execute() {
		service.execute(mojo);
	}

	public LoadMojo getMojo() {
		return mojo;
	}

	public void setMojo(LoadMojo mojo) {
		this.mojo = mojo;
	}

	public LoadMojoService getService() {
		return service;
	}

	public void setService(LoadMojoService service) {
		this.service = service;
	}

}

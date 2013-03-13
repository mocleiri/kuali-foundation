package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;

public class LoadMojoExecutable implements Executable {

	LoadMojo mojo;
	LoadMojoService service;

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

	public LoadMojoService getService() {
		return service;
	}

	public void setService(LoadMojoService service) {
		this.service = service;
	}

}

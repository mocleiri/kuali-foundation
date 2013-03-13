package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.stereotype.Component;

@Component
public class XmlLoadMojoExecutable implements Executable {

	XmlLoadMojo mojo;
	SpringMojoService service;

	@Override
	public void execute() {
		service.execute(mojo);
	}

	public XmlLoadMojo getMojo() {
		return mojo;
	}

	public void setMojo(XmlLoadMojo mojo) {
		this.mojo = mojo;
	}

	public SpringMojoService getService() {
		return service;
	}

	public void setService(SpringMojoService service) {
		this.service = service;
	}

}

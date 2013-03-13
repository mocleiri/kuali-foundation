package org.kuali.maven.plugins.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoadMojoExecutable implements Executable {

	@Autowired
	@Qualifier(AbstractSpringMojo.AUTOWIRED_MOJO_QUALIFIER)
	XmlLoadMojo mojo;

	LoadMojoService service;

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

	public LoadMojoService getService() {
		return service;
	}

	public void setService(LoadMojoService service) {
		this.service = service;
	}

}

package org.kuali.maven.plugins.spring.config;

import org.apache.maven.model.CiManagement;
import org.apache.maven.model.IssueManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadMojoServiceTestConfig {

	@Bean
	public CiManagement ci() {
		CiManagement ci = new CiManagement();
		ci.setUrl("http://ci.rice.kuali.org");
		ci.setSystem("jenkins");
		return ci;
	}

	@Bean
	public IssueManagement im() {
		IssueManagement im = new IssueManagement();
		im.setSystem("jira");
		im.setUrl("http://jira.kuali.org");
		return im;
	}

}

package org.kuali.common.util.spring;

import org.kuali.common.util.ScmContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ScmConfig {

	private static final String VENDOR_KEY = "scm.vendor";
	private static final String URL_KEY = "scm.url";
	private static final String SERVICE_KEY = "scm.service";

	@Autowired
	Environment env;

	@Bean
	public ScmContext scmContext() {
		ScmContext context = new ScmContext();
		context.setVendor(SpringUtils.getProperty(env, VENDOR_KEY));
		context.setUrl(SpringUtils.getProperty(env, URL_KEY));
		context.setService(SpringUtils.getProperty(env, SERVICE_KEY));
		return context;
	}

}

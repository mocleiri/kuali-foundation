package org.kuali.common.impex.spring;

import java.util.Properties;

import org.kuali.common.impex.ImpexContextFactoryBean;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ImpexDumpConfig {

	@Autowired
	Environment env;

	@Autowired
	Properties mavenProperties;

	@Bean
	public ImpexContext impexSourceContext() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(SpringUtils.getProperty(env, "impex.driver"));
		dmds.setUrl(SpringUtils.getProperty(env, "impex.url"));
		dmds.setUsername(SpringUtils.getProperty(env, "impex.username"));
		dmds.setPassword(SpringUtils.getProperty(env, "impex.password"));

		ImpexContextFactoryBean factory = new ImpexContextFactoryBean();
		factory.setDataSource(dmds);
		factory.setProperties(mavenProperties);
		return factory.getObject();
	}
}

package org.kuali.common.impex.spring;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.DumpExecutable;
import org.kuali.common.impex.ImpexContextFactoryBean;
import org.kuali.common.impex.service.DefaultImpexGeneratorService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ImpexDumpConfig {

	public static final String DUMP_CONTEXTS_QUALIFIER = "dumpContexts";

	@Autowired
	Environment env;

	@Autowired
	Properties mavenProperties;

	@Autowired
	@Qualifier(DUMP_CONTEXTS_QUALIFIER)
	List<ImpexContext> dumpContexts;

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

	@Bean(initMethod = "execute")
	public DumpExecutable dumpExecutable() {
		Assert.notNull(dumpContexts, "dumpContexts is null");
		DumpExecutable executable = new DumpExecutable();
		executable.setSourceContext(impexSourceContext());
		executable.setDatabaseVendors(Arrays.asList("oracle", "mysql"));
		executable.setService(new DefaultImpexGeneratorService());
		executable.setSkip(SpringUtils.getBoolean(env, "impex.dump.skip", false));
		executable.setContexts(dumpContexts);
		return executable;
	}

	@Bean
	public Properties dumpProperties() {
		return mavenProperties;
	}
}

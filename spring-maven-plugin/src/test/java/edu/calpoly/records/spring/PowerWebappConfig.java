package edu.calpoly.records.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.maven.plugins.spring.MavenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(PrintMessageConfig.class)
public class PowerWebappConfig {

	@Autowired
	PrintMessageConfig pmc;

	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public Executable showProperties() {
		ShowPropertiesExecutable spe = new ShowPropertiesExecutable();
		spe.setProperties(mavenProperties);
		return spe;
	}

	@Bean(initMethod = "execute")
	public Executable executablesExecutable() {
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(pmc.printMessageExecutable());
		executables.add(showProperties());

		ExecutablesExecutable ee = new ExecutablesExecutable();
		ee.setExecutables(executables);
		return ee;
	}
}

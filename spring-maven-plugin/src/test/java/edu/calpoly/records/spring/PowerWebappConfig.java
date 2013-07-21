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
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ PrintMavenMessageConfig.class, PrintMessageConfig.class })
public class PowerWebappConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	@Qualifier(value = "printMessageConfig")
	PrintMessageConfig printMessageConfig;

	@Autowired
	@Qualifier(MavenConstants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean(initMethod = "execute")
	public Executable executablesExecutable() {
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(printMessageConfig.printMessageExecutable());
		// executables.add(new ShowPropertiesExecutable(mavenProperties));
		// executables.add(new ShowPropertySourcesExecutable(env));
		return new ExecutablesExecutable(executables);
	}
}

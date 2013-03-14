package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutableExecutable;
import org.kuali.common.util.spring.config.PrintMessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(PrintMessageConfig.class)
public class PowerWebappConfig {

	@Autowired
	PrintMessageConfig pmc;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(initMethod = "execute")
	public Executable executableExecutable() {
		ExecutableExecutable ee = new ExecutableExecutable();
		ee.setExecutable(pmc.printMessageExecutable());
		return ee;
	}

}

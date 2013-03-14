package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.config.PrintMessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PrintMessageConfig.class)
public class PowerWebappConfig {

	@Autowired
	PrintMessageConfig pmc;

	@Bean(initMethod = "execute")
	public Executable printMessageExecutable() {
		return pmc.printMessageExecutable();
	}

}

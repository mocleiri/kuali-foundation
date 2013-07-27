package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PrintMavenMessageConfig.class, PrintMessageConfig.class })
public class PowerWebappConfig {

	@Autowired
	PrintMessageConfig printMessageConfig;

	@Bean(initMethod = "execute")
	public Executable executablesExecutable() {
		return printMessageConfig.printMessageExecutable();
	}
}

package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PrintMavenMessageConfig.class, PrintNotMavenMessage.class })
public class PowerWebappConfig {

	@Autowired
	PrintMessageExecutable printMessageExecutable;

	@Bean(initMethod = "execute")
	public Executable executablesExecutable() {
		return printMessageExecutable;
	}
}

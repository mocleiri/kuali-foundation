package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerWebappConfig {

	@Bean(initMethod = "execute")
	public Executable printMessageExecutable() {
		PrintMessageExecutable pme = new PrintMessageExecutable();
		return pme;
	}

}

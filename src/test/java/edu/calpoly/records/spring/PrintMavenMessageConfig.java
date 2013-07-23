package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Maven
public class PrintMavenMessageConfig {

	@Bean(name = "printMessageConfig")
	public Executable printMessageExecutable() {
		PrintMessageExecutable pme = new PrintMessageExecutable();
		pme.setMessage("maven override message");
		return pme;
	}

}

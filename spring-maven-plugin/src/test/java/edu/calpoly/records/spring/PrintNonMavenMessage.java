package edu.calpoly.records.spring;

import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.config.annotation.NotMaven;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NotMaven
public class PrintNonMavenMessage {

	@Bean
	public PrintMessageExecutable printMessageExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage("maven sucks!!!");
		return exec;
	}

}

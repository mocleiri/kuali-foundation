package edu.calpoly.records.spring;

import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrintMessageConfig {

	@Bean
	public PrintMessageExecutable printMessageExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage("hello world");
		return exec;
	}

}

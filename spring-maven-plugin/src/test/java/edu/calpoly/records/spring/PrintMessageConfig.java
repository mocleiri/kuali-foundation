package edu.calpoly.records.spring;

import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.config.annotation.Default;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Default
public class PrintMessageConfig {

	@Bean
	public PrintMessageExecutable printMessageExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage("hello world");
		return exec;
	}

}

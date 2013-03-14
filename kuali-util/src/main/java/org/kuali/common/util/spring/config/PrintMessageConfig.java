package org.kuali.common.util.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PrintMessageConfig {

	@Autowired
	Environment env;

	@Value("${print.message:NONE}")
	String printMessage;

	@Bean
	public Executable printMessageExecutable() {
		PrintMessageExecutable pme = new PrintMessageExecutable();
		pme.setMessage(printMessage);
		return pme;
	}

}

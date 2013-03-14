package org.kuali.common.util.spring.config;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PrintMessageConfig {

	@Autowired
	Environment env;

	@Bean
	public Executable printMessageExecutable() {
		String key = env.getProperty("print.message.property", "print.message");
		String message = env.getProperty(key, "No message was configured");

		PrintMessageExecutable pme = new PrintMessageExecutable();
		pme.setMessage(message);
		return pme;
	}

}

package org.kuali.common.util.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrintMessageConfig {

	@Value("${print.message:NONE}")
	String message;

	@Bean
	public Executable printMessageExecutable() {
		PrintMessageExecutable pme = new PrintMessageExecutable();
		pme.setMessage(message);
		return pme;
	}

}

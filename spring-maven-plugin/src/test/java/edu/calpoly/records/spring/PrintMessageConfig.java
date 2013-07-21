package edu.calpoly.records.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PrintMessageConfig {

	@Autowired
	Environment env;

	@Bean(name = "printMessageConfig")
	public Executable printMessageExecutable() {
		String message = SpringUtils.getProperty(env, "print.message", "NONE");
		PrintMessageExecutable pme = new PrintMessageExecutable();
		pme.setMessage(message);
		return pme;
	}

}

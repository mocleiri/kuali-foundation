package edu.calpoly.records.spring;

import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.spring.config.annotation.Maven;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Maven
public class PrintMavenMessageConfig {

	@Bean
	public PrintMessageExecutable printMessageExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage(MavenConstants.SPRING_PROFILE_NAME);
		return exec;
	}

}

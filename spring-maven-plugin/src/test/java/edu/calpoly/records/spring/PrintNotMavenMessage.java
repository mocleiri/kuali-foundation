package edu.calpoly.records.spring;

import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.spring.config.annotation.NotSpringMavenPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NotSpringMavenPlugin
public class PrintNotMavenMessage {

	@Bean
	public PrintMessageExecutable printMessageExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage(MavenConstants.NEGATED_SPRING_PROFILE_NAME);
		return exec;
	}

}

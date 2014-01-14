package edu.calpoly.records.spring;

import org.kuali.common.util.execute.PrintMessageExecutable;
import org.kuali.common.util.maven.spring.AutowiredMavenProperties;
import org.kuali.common.util.maven.spring.MavenProfileConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutowiredMavenProperties
public class PrintMavenMessageConfig {

	@Bean
	public PrintMessageExecutable printMessageExecutable() {
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage(MavenProfileConstants.AUTOWIRED_MAVEN_PROPERTIES);
		return exec;
	}

}

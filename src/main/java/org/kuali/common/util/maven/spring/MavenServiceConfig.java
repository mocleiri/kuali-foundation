package org.kuali.common.util.maven.spring;

import java.io.File;

import org.kuali.common.util.maven.DefaultLocalRepositoryService;
import org.kuali.common.util.maven.LocalRepositoryService;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class MavenServiceConfig {

	private static final String LOCAL_REPO_KEY = "settings.localRepository";

	@Autowired
	EnvironmentService env;

	@Bean
	public LocalRepositoryService localRepositoryService() {
		File defaultLocalRepo = RepositoryUtils.getDefaultLocalRepository();
		File localRepository = env.getFile(LOCAL_REPO_KEY, defaultLocalRepo);
		return new DefaultLocalRepositoryService(localRepository);
	}

}

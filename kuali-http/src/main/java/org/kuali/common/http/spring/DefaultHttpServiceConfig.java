package org.kuali.common.http.spring;

import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultHttpServiceConfig {

	@Bean
	public HttpService httpService() {
		return new DefaultHttpService();
	}

}

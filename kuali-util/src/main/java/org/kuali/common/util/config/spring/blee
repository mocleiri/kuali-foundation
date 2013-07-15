package org.kuali.common.util.config.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.config.DefaultProjectConfigService;
import org.kuali.common.util.config.Location;
import org.kuali.common.util.config.ProjectConfigService;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ProjectConfigSpringConfig {

	private static final String SERVICE_KEY = "project.config.service";
	private static final String IDS_KEY = "project.config.ids";

	@Autowired
	Environment env;

	@Bean
	public ProjectConfigService utilProjectConfigService() {
		return SpringUtils.getInstance(env, SERVICE_KEY, DefaultProjectConfigService.class);
	}

	@Bean
	public List<Location> utilProjectConfigLocations() {
		ProjectConfigService service = utilProjectConfigService();

		List<String> ids = SpringUtils.getNoneSensitiveListFromCSV(env, IDS_KEY, Constants.NONE);

		List<Location> locations = new ArrayList<Location>();
		for (String id : ids) {
			List<Location> list = service.getLocations(id);
			locations.addAll(list);
		}
		return locations;
	}
}

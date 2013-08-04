package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class MetaInfCommonConfig {

	public static final String DATA_FILENAME = "data." + MetaInfUtils.RESOURCES;
	public static final String SCHEMA_FILENAME = "schema." + MetaInfUtils.RESOURCES;
	public static final String CONSTRAINTS_FILENAME = "constraints." + MetaInfUtils.RESOURCES;
	public static final String OTHER_FILENAME = "other." + MetaInfUtils.RESOURCES;

	@Autowired
	Project project;

}

package org.kuali.common.util.metainf.spring;

import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class })
public class MpxBuildConfig {

	@Autowired
	Project project;

}

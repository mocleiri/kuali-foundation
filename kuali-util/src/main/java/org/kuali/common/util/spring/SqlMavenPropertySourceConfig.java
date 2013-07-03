package org.kuali.common.util.spring;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.SqlProjectContext;
import org.kuali.common.util.property.ProjectProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlMavenPropertySourceConfig extends MavenPropertySourceConfig {

	@Override
	protected List<ProjectProperties> getOtherProjectProperties() {
		ProjectContext sql = new SqlProjectContext();
		return ConfigUtils.getProjectProperties(Arrays.asList(sql));
	}

}

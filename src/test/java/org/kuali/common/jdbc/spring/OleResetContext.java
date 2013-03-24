package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesLoaderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcPropertiesConfig.class, OlePropertiesConfig.class })
public class OleResetContext {

	@Autowired
	JdbcPropertiesConfig jdbcPropertiesConfig;

	@Autowired
	OlePropertiesConfig olePropertiesConfig;

	@Bean
	public PropertySource<?> springPropertySource() {
		String name = "springPropertySource";
		Properties source = oleResetProperties();
		return new PropertiesPropertySource(name, source);
	}

	@Bean
	public Properties oleResetProperties() {
		Properties properties = new Properties();
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcPropertiesConfig.jdbcProjectProperties());
		pps.add(olePropertiesConfig.oleProjectProperties());
		for (ProjectProperties pp : pps) {
			for (PropertiesLoaderContext ctx : pp.getLoaderContexts()) {
				Properties combined = PropertyUtils.combine(properties, ctx.getProperties());
				ctx.setProperties(combined);
				Properties loaded = PropertyUtils.load(ctx);
				properties.putAll(loaded);
			}
		}
		return properties;
	}

}

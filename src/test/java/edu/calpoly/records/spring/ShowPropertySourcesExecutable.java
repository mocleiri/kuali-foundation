package edu.calpoly.records.spring;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.springframework.core.env.ConfigurableEnvironment;

public class ShowPropertySourcesExecutable implements Executable {

	ConfigurableEnvironment environment;

	public ShowPropertySourcesExecutable(ConfigurableEnvironment environment) {
		super();
		this.environment = environment;
	}

	@Override
	public void execute() {
		Properties properties = PropertySourceUtils.getAllEnumerableProperties(environment);
		PropertyUtils.info(properties);
		// SpringUtils.showPropertySources(environment);
	}

	public ConfigurableEnvironment getEnvironment() {
		return environment;
	}

	public void setEnvironment(ConfigurableEnvironment environment) {
		this.environment = environment;
	}

}

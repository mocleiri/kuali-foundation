package org.kuali.common.util.create.spring;

import org.kuali.common.util.create.CreatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DefaultCreatorFactoryConfig implements CreatorFactoryConfig {

	@Autowired
	Environment env;

	@Override
	public CreatorFactory creatorFactory() {
		return null;
	}

}

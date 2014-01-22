package org.kuali.common.util.system.spring;

import javax.validation.Validator;

import org.kuali.common.util.bind.api.Binder;
import org.kuali.common.util.bind.impl.DefaultBinder;
import org.kuali.common.util.create.Creator;
import org.kuali.common.util.create.impl.DefaultCreator;
import org.kuali.common.util.spring.convert.DefaultConversionService;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.validate.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;

@Configuration
public class DefaultSystemConfig implements SystemConfig {

	@Autowired
	Environment env;

	@Override
	@Bean
	public SystemProperties systemProperties() {
		Creator creator = creator();
		return SystemProperties.builder().creator(creator).build();
	}

	@Bean
	public Creator creator() {
		ConversionService conversion = new DefaultConversionService();
		Binder binder = DefaultBinder.create(env, conversion);
		Validator validator = Validation.getDefaultValidator();
		return DefaultCreator.create(validator, binder);
	}
}

package org.kuali.common.util.spring.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRunnerConfig {

	@Autowired
	@Qualifier(MainUtils.ARGS_BEAN_NAME)
	String[] args;

	@Autowired
	@Qualifier(MainUtils.MAIN_CLASS_BEAN_NAME)
	Class<?> mainClass;

}

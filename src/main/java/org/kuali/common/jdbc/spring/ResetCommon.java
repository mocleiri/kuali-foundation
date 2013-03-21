package org.kuali.common.jdbc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ResetCommon {

	@Autowired
	Environment env;

}

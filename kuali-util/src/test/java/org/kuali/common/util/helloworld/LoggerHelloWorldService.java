package org.kuali.common.util.helloworld;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

@MetaInfServices
public class LoggerHelloWorldService implements HelloWorldService {

	private static final Logger logger = LoggerUtils.make();

	@Override
	public void sayHello() {
		logger.info("hello world");
	}

}

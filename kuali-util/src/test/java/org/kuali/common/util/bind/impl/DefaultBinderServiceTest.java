package org.kuali.common.util.bind.impl;

import org.junit.Test;
import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.model.User;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class DefaultBinderServiceTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			BinderService service = new DefaultBinderService();
			User user = new User();
			service.bind(user);
			logger.info(String.format("name=%s", user.getName()));
			logger.info(String.format("home=%s", user.getHome()));
			logger.info(String.format("timeZone=%s", user.getTimezone().getDisplayName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package org.kuali.common.util.bind.impl;

import org.codehaus.plexus.util.StringUtils;
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
			System.setProperty("user.directory", "/tmp");
			BinderService service = new DefaultBinderService();
			User user = new User();
			service.bind(user);
			String prefix = StringUtils.uncapitalise(User.class.getSimpleName());
			logger.info(String.format("%s.name=%s", prefix, user.getName()));
			logger.info(String.format("%s.home=%s", prefix, user.getHome()));
			logger.info(String.format("%s.dir=%s", prefix, user.getDir()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

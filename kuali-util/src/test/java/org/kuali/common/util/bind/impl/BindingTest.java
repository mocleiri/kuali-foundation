package org.kuali.common.util.bind.impl;

import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.system.User;
import org.kuali.common.util.system.VirtualMachine;
import org.slf4j.Logger;

public class BindingTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			VirtualMachine vm = VirtualMachine.build();
			User user = vm.getUser();
			String prefix = StringUtils.uncapitalise(User.class.getSimpleName());
			logger.info(String.format("%s.name=[%s]", prefix, user.getName()));
			logger.info(String.format("%s.home=[%s]", prefix, user.getHome()));
			logger.info(String.format("%s.dir=[%s]", prefix, user.getDir()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

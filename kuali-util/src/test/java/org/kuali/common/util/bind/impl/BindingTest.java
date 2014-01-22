package org.kuali.common.util.bind.impl;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.vm.User;
import org.kuali.common.util.vm.VirtualMachine;
import org.slf4j.Logger;

public class BindingTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			System.setProperty("user.dir", "/tmp");
			VirtualMachine vm = VirtualMachine.build();
			User user = vm.getUser();
			logger.info(String.format("user.name=[%s]", user.getName()));
			logger.info(String.format("user.home=[%s]", user.getHome()));
			logger.info(String.format("user.dir=[%s]", user.getDir()));
			logger.info(String.format("user.dir=[%s]", user.getDir()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

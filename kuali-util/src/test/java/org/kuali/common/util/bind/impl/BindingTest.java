package org.kuali.common.util.bind.impl;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.vm.OperatingSystem;
import org.kuali.common.util.vm.User;
import org.kuali.common.util.vm.VirtualMachine;
import org.slf4j.Logger;

public class BindingTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			VirtualMachine vm = VirtualMachine.build();
			User user = vm.getUser();
			OperatingSystem os = vm.getOperatingSystem();
			logger.info(String.format("user.name=[%s]", user.getName()));
			logger.info(String.format("user.home=[%s]", user.getHome()));
			logger.info(String.format("user.dir=[%s]", user.getDir()));
			logger.info(String.format("os.name=[%s]", os.getName()));
			logger.info(String.format("os.arch=[%s]", os.getArchitecture()));
			logger.info(String.format("os.version=[%s]", os.getVersion()));
			logger.info(String.format("java.io.tmpdir=[%s]", vm.getTempDirectory()));
			logger.info(String.format("java.home=[%s]", vm.getHome()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

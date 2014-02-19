package org.kuali.common.util.bind.test;

import static java.lang.String.format;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.system.OperatingSystem;
import org.kuali.common.util.system.JVM;
import org.kuali.common.util.system.User;
import org.slf4j.Logger;

public class BindingTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			JVM vm = JVM.builder().build();
			User user = vm.getUser();
			OperatingSystem os = vm.getOperatingSystem();
			logger.info(format("user.name=[%s]", user.getName()));
			logger.info(format("user.home=[%s]", user.getHome()));
			logger.info(format("user.dir=[%s]", user.getDir()));
			logger.info(format("os.name=[%s]", os.getName()));
			logger.info(format("os.arch=[%s]", os.getArchitecture()));
			logger.info(format("os.version=[%s]", os.getVersion()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

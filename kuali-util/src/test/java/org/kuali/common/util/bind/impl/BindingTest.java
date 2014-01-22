package org.kuali.common.util.bind.impl;

import static java.lang.String.format;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.system.OperatingSystem;
import org.kuali.common.util.system.Specification;
import org.kuali.common.util.system.SystemProperties;
import org.kuali.common.util.system.User;
import org.slf4j.Logger;


public class BindingTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			SystemProperties vm = SystemProperties.build();
			User user = vm.getUser();
			OperatingSystem os = vm.getOperatingSystem();
			Specification jre = vm.getJreSpecification();
			logger.info(format("user.name=[%s]", user.getName()));
			logger.info(format("user.home=[%s]", user.getHome()));
			logger.info(format("user.dir=[%s]", user.getDir()));
			logger.info(format("os.name=[%s]", os.getName()));
			logger.info(format("os.arch=[%s]", os.getArchitecture()));
			logger.info(format("os.version=[%s]", os.getVersion()));
			logger.info(format("java.io.tmpdir=[%s]", vm.getTempDirectory()));
			logger.info(format("java.home=[%s]", vm.getHome()));
			logger.info(format("java.specification.vendor=[%s]", jre.getVendor()));
			logger.info(format("java.specification.version=[%s]", jre.getVersion()));
			logger.info(format("java.specification.name=[%s]", jre.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

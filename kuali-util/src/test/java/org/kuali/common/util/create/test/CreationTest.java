package org.kuali.common.util.create.test;

import org.junit.Test;
import org.kuali.common.util.create.Creation;
import org.kuali.common.util.create.Creator;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class CreationTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Creator creator = Creation.getDefaultCreator();
			logger.info(creator + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

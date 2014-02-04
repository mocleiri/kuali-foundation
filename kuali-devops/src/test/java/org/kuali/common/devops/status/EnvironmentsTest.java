package org.kuali.common.devops.status;

import java.util.List;
import java.util.SortedMap;

import org.junit.Test;
import org.kuali.common.devops.logic.Environments2;
import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.validate.Validation;
import org.slf4j.Logger;

public class EnvironmentsTest {

	private static final Logger logger = Loggers.make();

	@Test
	public void test() {
		try {
			Validation.getDefaultValidator();
			SortedMap<String, List<Environment>> maps = Environments2.getEnvironments(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

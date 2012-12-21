package org.kuali.common.deploy.execute;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TomcatController {

	private static final Logger logger = LoggerFactory.getLogger(TomcatController.class);

	@Test
	public void test() {
		try {
			logger.info("Hello world");
			String hostname = "env7.ole.kuali.org";
			List<Executable> executables = new ArrayList<Executable>();
			executables.add(new TomcatStop(hostname));
			executables.add(new TomcatCleanup(hostname));
			executables.add(new TomcatStart(hostname));
			for (Executable executable : executables) {
				executable.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

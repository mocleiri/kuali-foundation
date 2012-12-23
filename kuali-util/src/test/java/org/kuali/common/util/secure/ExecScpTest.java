package org.kuali.common.util.secure;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecScpTest {
	private static final Logger logger = LoggerFactory.getLogger(ExecScpTest.class);

	@Test
	public void test() {

		try {
			File source = new File("/tmp/foo/files.txt");
			File destination = new File("/tmp/bar/files.txt");

			Scp scp = new ExecScp();
			int exitValue = scp.copy(source, destination);
			logger.info("SCP exit value = " + exitValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

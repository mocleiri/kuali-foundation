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
			logger.info("SCP exit value = " + scp.copy(source, destination));

			ScpFile scpFile = new ScpFile();
			scpFile.setHostname("ci.rice.kuali.org");
			scpFile.setUsername("root");
			scpFile.setFilename("/root/files.txt");
			logger.info("SCP exit value = " + scp.copy(scpFile, destination));

			ScpFile scpFileDest = new ScpFile();
			scpFileDest.setHostname("ci.rice.kuali.org");
			scpFileDest.setUsername("root");
			scpFileDest.setFilename("/root/files.txt");
			logger.info("SCP exit value = " + scp.copy(source, scpFileDest));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

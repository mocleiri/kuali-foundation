/**
 * Copyright 2010-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.secure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecScpTest {
	private static final Logger logger = LoggerFactory.getLogger(ExecScpTest.class);

	protected List<ScpFile> getScpFiles() {
		List<ScpFile> files = new ArrayList<ScpFile>();
		files.add(ScpUtils.getScpFile("/tmp/scp/pdfs/1.pdf"));
		files.add(ScpUtils.getScpFile("/tmp/scp/pdfs/2.pdf"));
		files.add(ScpUtils.getScpFile("/tmp/scp/pdfs/3.pdf"));
		return files;
	}

	protected List<ScpFile> getComboScpFiles() {
		List<ScpFile> files = new ArrayList<ScpFile>();
		files.add(new ScpFile("root", "ci.rice.kuali.org", "/root/files/1.txt"));
		files.add(ScpUtils.getScpFile("/tmp/scp/pdfs/1.pdf"));
		files.add(new ScpFile("root", "ci.fn.kuali.org", "/root/files/3.txt"));
		return files;
	}

	@Test
	public void testBunchOfStuff() {
		try {
			SecureContext context = new SecureContext();
			context.setRecursive(true);
			context.setConfigFile(new File(System.getProperty("user.home") + "/.ssh/config.new"));
			context.setPrivateKey(new File(System.getProperty("user.home") + "/.ssh/kr-key.pem"));
			context.setPort(23);
			Properties options = new Properties();
			options.setProperty("StrictHostKeyChecking", "no");
			context.setOptions(options);
			ScpFile destination = ScpUtils.getScpFile("/tmp/scp/dest/x/y/z");
			Scp scp = new ExecScp();
			logger.info("SCP exit value - [{}]", scp.copy(context, getComboScpFiles(), destination));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testScpRecursive() {
		try {
			SecureContext context = new SecureContext();
			List<String> args = new ArrayList<String>();
			args.add("-r");
			context.setArgs(args);
			ScpFile source = ScpUtils.getScpFile("/tmp/scp/pdfs");
			ScpFile destination = ScpUtils.getScpFile("/tmp/scp/dest/x/y/z");
			Scp scp = new ExecScp();
			logger.info("SCP exit value - [{}]", scp.copy(context, source, destination));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testScpMulti() {
		try {
			SecureContext context = new SecureContext();
			ScpFile destination = ScpUtils.getScpFile("/tmp/scp/dest/x/y/z");
			Scp scp = new ExecScp();
			logger.info("SCP exit value - [{}]", scp.copy(context, getScpFiles(), destination));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testScpSingle() {
		try {
			SecureContext context = new SecureContext();
			ScpFile source = ScpUtils.getScpFile("/tmp/scp/pdfs/1.pdf");
			ScpFile destination = ScpUtils.getScpFile("/tmp/scp/dest/x/y/z");
			Scp scp = new ExecScp();
			logger.info("SCP exit value - [{}]", scp.copy(context, source, destination));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void test2() {

		try {
			ScpFile source = ScpUtils.getScpFile("/tmp/scp/pdfs/1.pdf");
			ScpFile destination = ScpUtils.getScpFile("/tmp/scp/dest");

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

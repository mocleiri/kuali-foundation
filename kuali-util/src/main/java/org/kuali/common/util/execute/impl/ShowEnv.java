package org.kuali.common.util.execute.impl;

import java.nio.charset.Charset;
import java.util.Locale;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowEnv implements Executable {

	public ShowEnv() {
		this(false);
	}

	public ShowEnv(boolean automatic) {
		this.automatic = automatic;
		if (automatic) {
			execute();
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(ShowEnv.class);

	private final boolean automatic;

	@Override
	public void execute() {
		Object[] java = { System.getProperty("java.runtime.version"), System.getProperty("java.vm.vendor"), System.getProperty("java.vm.name") };
		Object[] javaHome = { new CanonicalFile(System.getProperty("java.home")) };
		Object[] other = { Locale.getDefault().getDisplayName(), Charset.defaultCharset().displayName() };
		Object[] os = { System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch") };
		logger.info("Java version: {}, vendor: {}, name: {}", java);
		logger.info("Java home: {}", javaHome);
		logger.info("Default locale: {}, platform encoding: {}", other);
		logger.info("OS name: {}, version: {}, arch: {}", os);
	}

	public boolean isAutomatic() {
		return automatic;
	}

}

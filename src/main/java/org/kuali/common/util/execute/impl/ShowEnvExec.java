package org.kuali.common.util.execute.impl;

import java.nio.charset.Charset;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowEnvExec implements Executable {

	public ShowEnvExec() {
		this(false);
	}

	public ShowEnvExec(boolean automatic) {
		this.skip = automatic;
	}

	private static final Logger logger = LoggerFactory.getLogger(ShowEnvExec.class);

	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		Object[] java = { System.getProperty("java.runtime.version"), System.getProperty("java.vm.name"), System.getProperty("java.vm.vendor") };
		Object[] javaHome = { new CanonicalFile(System.getProperty("java.home")) };
		Object[] JAVA_HOME = getJavaHomeEnvironmentVariable();
		Object[] other = { Locale.getDefault().toString(), Charset.defaultCharset().displayName() };
		Object[] os = { System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch") };
		logger.info("Java version: {}, name: {}, vendor: {}", java);
		logger.info("Java home: {}", javaHome);
		logger.info("JAVA_HOME: {}", JAVA_HOME);
		logger.info("Default locale: {}, platform encoding: {}", other);
		logger.info("OS name: {}, version: {}, arch: {}", os);
	}

	public Object[] getJavaHomeEnvironmentVariable() {
		String javaHome = System.getenv("JAVA_HOME");
		if (StringUtils.isBlank(javaHome)) {
			return new Object[] { "-- Not set --" };
		} else {
			return new Object[] { new CanonicalFile(javaHome) };
		}
	}

	public boolean isSkip() {
		return skip;
	}

}

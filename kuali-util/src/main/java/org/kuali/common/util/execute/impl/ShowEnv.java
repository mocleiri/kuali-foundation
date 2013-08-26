package org.kuali.common.util.execute.impl;

import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowEnv implements Executable {

	public ShowEnv() {
		this(false);
	}

	public ShowEnv(boolean skip) {
		this.skip = skip;
	}

	private static final Logger logger = LoggerFactory.getLogger(ShowEnv.class);

	private final boolean skip;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Object[] jdk = { System.getProperty("java.runtime.version"), System.getProperty("java.vm.vendor"), System.getProperty("java.vm.name") };
		logger.info("Java version: {}, vendor: {}, name: {}", jdk);
	}

	public boolean isSkip() {
		return skip;
	}

}

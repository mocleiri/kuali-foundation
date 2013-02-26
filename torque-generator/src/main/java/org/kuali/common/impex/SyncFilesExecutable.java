package org.kuali.common.impex;

import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.SyncResult;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncFilesExecutable.class);

	List<ImpexContext> contexts;
	Properties properties;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping impex file sync");
			return;
		}
		List<SyncResult> results = ImpexUtils.syncFiles(contexts);
		System.out.println(results);
	}

	public List<ImpexContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<ImpexContext> contexts) {
		this.contexts = contexts;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}

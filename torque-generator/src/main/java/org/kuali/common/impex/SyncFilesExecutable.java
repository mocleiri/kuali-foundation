package org.kuali.common.impex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SyncResult;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.service.ScmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncFilesExecutable.class);

	List<ImpexContext> contexts;
	boolean skip;
	// Don't commit to SCM unless they specifically override this
	boolean skipScm = true;
	ScmService service;
	String message = "Automated Impex update";

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping impex file sync");
			return;
		}

		Assert.notNull(contexts);

		List<File> adds = new ArrayList<File>();
		List<File> deletes = new ArrayList<File>();

		List<SyncResult> results = ImpexUtils.syncFiles(contexts);
		for (SyncResult result : results) {
			adds.addAll(result.getAdds());
			deletes.addAll(result.getDeletes());
		}

		List<File> paths = new ArrayList<File>();
		for (ImpexContext context : contexts) {
			paths.add(context.getFinalDirectory());
		}
		File databasePropertiesFile = getDatabasePropertiesFile(contexts);
		if (databasePropertiesFile != null) {
			paths.add(databasePropertiesFile);
		}

		logger.info("---------- Sync results ----------");
		logger.info("Files added - {}", adds.size());
		logger.info("Files deleted - {}", deletes.size());
		logger.info("---------- Sync results ----------");

		if (skipScm) {
			logger.info("Skipping SCM commit");
		} else {
			service.add(adds);
			service.delete(deletes);
			service.commit(paths, message);
		}
	}

	protected File getDatabasePropertiesFile(List<ImpexContext> contexts) {
		for (ImpexContext context : contexts) {
			if (context.isStoreDatabaseTableProperties()) {
				String location = context.getDatabaseTablePropertiesLocation();
				if (LocationUtils.isExistingFile(location)) {
					return new File(location);
				}
			}
		}
		return null;
	}

	public List<ImpexContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<ImpexContext> contexts) {
		this.contexts = contexts;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public ScmService getService() {
		return service;
	}

	public void setService(ScmService service) {
		this.service = service;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSkipScm() {
		return skipScm;
	}

	public void setSkipScm(boolean skipScm) {
		this.skipScm = skipScm;
	}

}

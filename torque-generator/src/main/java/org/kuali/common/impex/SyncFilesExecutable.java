package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SyncResult;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncFilesExecutable.class);

	List<ImpexContext> contexts;
	Properties properties;
	boolean skip;
	String addsProperty = "impex.scm.adds";
	String updatesProperty = "impex.scm.updates";
	String deletesProperty = "impex.scm.deletes";

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping impex file sync");
			return;
		}

		Assert.notNull(contexts);
		Assert.notNull(properties);

		List<String> adds = new ArrayList<String>();
		List<String> updates = new ArrayList<String>();
		List<String> deletes = new ArrayList<String>();

		List<SyncResult> results = ImpexUtils.syncFiles(contexts);
		for (SyncResult result : results) {
			adds.addAll(LocationUtils.getCanonicalPaths(result.getAdds()));
			updates.addAll(LocationUtils.getCanonicalPaths(result.getUpdates()));
			deletes.addAll(LocationUtils.getCanonicalPaths(result.getDeletes()));
		}

		logger.info("---------- Sync results ----------");
		logger.info("Files added - {}", adds.size());
		logger.info("Files updated - {}", updates.size());
		logger.info("Files deleted - {}", deletes.size());
		logger.info("---------- Sync results ----------");

		String addsCSV = CollectionUtils.getCSV(adds);
		String updatesCSV = CollectionUtils.getCSV(updates);
		String deletesCSV = CollectionUtils.getCSV(deletes);

		properties.setProperty(addsProperty, addsCSV);
		properties.setProperty(updatesProperty, updatesCSV);
		properties.setProperty(deletesProperty, deletesCSV);
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

	public String getAddsProperty() {
		return addsProperty;
	}

	public void setAddsProperty(String addsProperty) {
		this.addsProperty = addsProperty;
	}

	public String getUpdatesProperty() {
		return updatesProperty;
	}

	public void setUpdatesProperty(String updatesProperty) {
		this.updatesProperty = updatesProperty;
	}

	public String getDeletesProperty() {
		return deletesProperty;
	}

	public void setDeletesProperty(String deletesProperty) {
		this.deletesProperty = deletesProperty;
	}

}

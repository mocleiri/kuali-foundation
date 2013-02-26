package org.kuali.common.impex;

import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.DefaultImpexGeneratorService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexGeneratorService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DumpExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(DumpExecutable.class);

	ImpexGeneratorService service = new DefaultImpexGeneratorService();
	ImpexContext sourceContext;
	List<ImpexContext> contexts;
	List<String> databaseVendors;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping db dump");
			return;
		}
		Assert.notNull(sourceContext);
		ImpexUtils.log(sourceContext);
		Assert.notNull(service);
		Assert.notNull(contexts);
		Assert.notNull(databaseVendors);
		long start = System.currentTimeMillis();
		try {
			DatabaseContext database = service.getDatabaseObjectLists(sourceContext);
			service.fillInMetaData(sourceContext, database);
			service.serializeSchemas(contexts, database);
			service.generateSchemaSql(contexts, databaseVendors);
			List<DumpTableResult> results = service.dumpTables(sourceContext, database);
			updateSourceDatabaseProperties(sourceContext, results);
			ImpexUtils.doStats(results);
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	protected void updateSourceDatabaseProperties(ImpexContext sourceContext, List<DumpTableResult> results) {
		if (sourceContext.isStoreDatabaseTableProperties()) {
			Properties properties = sourceContext.getDatabaseTableProperties();
			String location = sourceContext.getDatabaseTablePropertiesLocation();
			ImpexUtils.updateAndStoreDatabaseProperties(properties, location, results);
		}
	}

	public ImpexContext getSourceContext() {
		return sourceContext;
	}

	public void setSourceContext(ImpexContext sourceContext) {
		this.sourceContext = sourceContext;
	}

	public ImpexGeneratorService getService() {
		return service;
	}

	public void setService(ImpexGeneratorService service) {
		this.service = service;
	}

	public List<String> getDatabaseVendors() {
		return databaseVendors;
	}

	public void setDatabaseVendors(List<String> databaseVendors) {
		this.databaseVendors = databaseVendors;
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

}

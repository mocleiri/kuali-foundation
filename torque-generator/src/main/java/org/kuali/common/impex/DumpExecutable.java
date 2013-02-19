package org.kuali.common.impex;

import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.DefaultImpexService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DumpExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(DumpExecutable.class);

	ImpexContext sourceContext;
	List<ImpexContext> contexts;
	ImpexService service = new DefaultImpexService();
	List<String> databaseVendors;

	@Override
	public void execute() {
		Assert.notNull(sourceContext);
		Assert.notNull(contexts);
		Assert.notNull(service);
		Assert.notNull(databaseVendors);
		long start = System.currentTimeMillis();
		try {
			DatabaseContext database = service.getDatabaseObjectLists(sourceContext);
			// service.fillInMetaData(sourceContext, database);
			// service.serializeSchemas(contexts, database);
			// service.generateSchemaSql(contexts, databaseVendors);
			// List<DumpTableResult> results = service.dumpTables(sourceContext, database);
			// updateSourceDatabaseProperties(sourceContext, results);
			// ImpexUtils.doStats(results);
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

	public ImpexService getService() {
		return service;
	}

	public void setService(ImpexService service) {
		this.service = service;
	}

	public List<String> getDatabaseVendors() {
		return databaseVendors;
	}

	public void setDatabaseVendors(List<String> databaseVendors) {
		this.databaseVendors = databaseVendors;
	}

}

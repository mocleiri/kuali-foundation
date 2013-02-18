package org.kuali.common.impex;

import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.util.execute.Executable;

public class DumpExecutable implements Executable {

	ImpexContext sourceContext;
	List<ImpexContext> contexts;
	ImpexService service;
	List<String> databaseVendors;

	@Override
	public void execute() {
		try {
			DatabaseContext database = service.getDatabaseObjectLists(sourceContext);
			service.fillInMetaData(sourceContext, database);
			service.serializeSchemas(contexts, database);
			service.generateSchemaSql(contexts, databaseVendors);
			List<DumpTableResult> results = service.dumpTables(sourceContext, database);
			if (sourceContext.isStoreDatabaseTableProperties()) {
				Properties properties = sourceContext.getDatabaseTableProperties();
				String location = sourceContext.getDatabaseTablePropertiesLocation();
				ImpexUtils.updateAndStoreDatabaseProperties(properties, location, results);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
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

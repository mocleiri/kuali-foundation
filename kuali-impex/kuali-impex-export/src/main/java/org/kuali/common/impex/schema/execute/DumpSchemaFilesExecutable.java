package org.kuali.common.impex.schema.execute;

import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DumpSchemaFilesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(DumpSchemaFilesExecutable.class);

	DumpSchemaService service = DumpSchemaExecutable.DEFAULT_EXPORT_SCHEMA_SERVICE;
	List<DumpSchemaFileRequest> requests;
	boolean skip;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(requests, "requests is null");

		for (DumpSchemaFileRequest request : requests) {
			doRequest(request);
		}
	}

	protected void doRequest(DumpSchemaFileRequest request) {

		// Create a filter from the includes/excludes they supplied
		StringFilter filter = StringFilter.getInstance(request.getIncludes(), request.getExcludes());

		// Clone the schema so the act of dumping it to disk does not alter the original schema object they gave us in any way
		Schema clone = new Schema(request.getSchema());

		// Filter the schema and keep track of any schema objects that got filtered out
		Schema excludedSchemaObjects = ModelUtils.filter(clone, filter);

		// The full file system path can sometimes be annoyingly long
		String path = FileSystemUtils.getRelativePathQuietly(request.getRelativeDir(), request.getOutputFile());

		// Show what we are up to
		logger.info("Creating - [{}] - {}", path, LoggerUtils.getLogMsg(request.getIncludes(), request.getExcludes()));

		// Log the objects that got filtered out if they asked us to
		if (request.isLogExcludedSchemaObjects()) {
			ModelUtils.logTable(excludedSchemaObjects, "Excluded Schema Objects");
		}

		// Persist the schema to disk as XML
		service.dumpSchema(clone, request.getOutputFile());
	}

	public DumpSchemaService getService() {
		return service;
	}

	public void setService(DumpSchemaService service) {
		this.service = service;
	}

	public List<DumpSchemaFileRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<DumpSchemaFileRequest> requests) {
		this.requests = requests;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}

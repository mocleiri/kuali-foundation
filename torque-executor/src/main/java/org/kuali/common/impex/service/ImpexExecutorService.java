package org.kuali.common.impex.service;

import java.io.IOException;
import java.util.List;

import org.kuali.common.jdbc.context.ExecutionContext;

/**
 * This interface provides an API to execute sql from artifacts created by torque-generator
 * 
 * @author andrewlubbers
 */
public interface ImpexExecutorService {

	/**
	 * Import data from MPX into database defined in given context
	 * 
	 * @param context
	 *            contains the database connection context and other meta information
	 */
	List<MpxImportResult> importData(ImportContext context, ExecutionContext executionContext) throws IOException;

	MpxImportResult importDataLocation(MpxMetaData metaData, ImportContext context, ExecutionContext executionContext);

}

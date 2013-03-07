package org.kuali.common.impex.service;

import java.io.IOException;
import java.util.List;

import org.kuali.common.impex.ImportContext;
import org.kuali.common.impex.supplier.MpxExecuteMetaData;
import org.kuali.common.jdbc.context.JdbcContext;

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
	List<MpxImportResult> importData(ImportContext context, JdbcContext executionContext) throws IOException;

	MpxImportResult importDataLocation(MpxExecuteMetaData metaData, ImportContext context, JdbcContext executionContext);

}

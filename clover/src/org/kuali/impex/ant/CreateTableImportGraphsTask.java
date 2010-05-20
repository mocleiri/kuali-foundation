package org.kuali.impex.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.tools.ant.BuildException;
import org.kuali.impex.CreateImportGraph;
import org.kuali.impex.ETLHelper;


public class CreateTableImportGraphsTask extends EtlJdbcTask {

	private boolean createIfTableEmpty = true;
	private boolean truncateTable = false;
	

	public boolean isTruncateTable() {
		return truncateTable;
	}

	public void setTruncateTable(boolean truncateTable) {
		this.truncateTable = truncateTable;
	}

	@Override
	public void execute() throws BuildException {
		Connection con = getConnection();
		File graphDir = new File( getExportBaseDir(), getGraphDir() ); 
		graphDir.mkdirs();
		try {
			ResultSet tables = con.getMetaData().getTables(null, getSchemaName().toUpperCase(), getTableName(), new String[] { "TABLE" } );
			
			while ( tables.next() ) {
				String tableName = tables.getString( "TABLE_NAME" );
				// filter out oracle control tables 
				// and sequences when implemented as tables (as in MySQL)
				if ( !ETLHelper.isValidTableType(tableName) ) {
					continue;
				}
				if ( !isCreateIfTableEmpty() && ETLHelper.isTableEmpty(con, getSchemaName(), tableName) ) {
					continue;
				}
				String exportGraph = CreateImportGraph.getImportGraph(con, getSchemaName().toUpperCase(), tableName, getFormatDir(), getDataDir(), isIncludeDebugDumps(), isTruncateTable()).toString();
				
				File outFile = new File( graphDir, tableName.toLowerCase() + "_import.grf.xml" );
				log( "Writing to output file: "  + outFile.getAbsolutePath() );
				BufferedWriter out = new BufferedWriter( new FileWriter( outFile ) );
				out.write( exportGraph );
				out.close();			
			}
			tables.close();
		} catch ( Exception ex ) {
			throw new BuildException( ex );
		}
	}

	/**
	 * @return the createIfTableEmpty
	 */
	public boolean isCreateIfTableEmpty() {
		return createIfTableEmpty;
	}

	/**
	 * @param createIfTableEmpty the createIfTableEmpty to set
	 */
	public void setCreateIfTableEmpty(boolean createIfTableEmpty) {
		this.createIfTableEmpty = createIfTableEmpty;
	}
}

package org.kuali.impex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbMetadataToFormat {

	
	public static void main(String[] args) throws Exception {
		if ( args.length == 0 ) {
			System.err.println( "usage: db-to-format <database.cfg file> <table schema> <table name> <format file name>" );
		}
		
		Connection con = ETLHelper.connectToDatabase( args[0] );
		
		String tableFormat = extractTableMetadata( con, args[1], args[2]);

		con.close();
		
		System.out.println( tableFormat );
		File outFile = new File( args[3] );
		System.out.println( "Writing to output file: "  + outFile.getAbsolutePath() );
		BufferedWriter out = new BufferedWriter( new FileWriter( outFile ) );
		out.write( tableFormat );
		out.close();
	}
	
	public static String extractTableMetadata( Connection con, String schema, String tableName ) throws SQLException {
		System.out.println( "Dumping Database Table Format: " + schema +  "." + tableName );
		ResultSet cols = con.getMetaData().getColumns(null, schema, tableName, null);
		int colCount = 0;
		while ( cols.next() ) {
			colCount++;
		}
		cols.close();
		cols = con.getMetaData().getColumns(null, schema, tableName, null);
		StringBuffer sb = new StringBuffer( 2000 );		
//		sb.append( "<Record name=\"").append( tableName.toLowerCase() ).append( "\" type=\"delimited\" recordDelimiter=\"\\n\" fieldDelimiter=\"").append( ETLHelper.COLUMN_DELIMITER ).append( "\" >\n" );
		sb.append( "<Record name=\"").append( tableName.toLowerCase() ).append( "\" type=\"delimited\">\n" );
		while ( cols.next() ) {
			sb.append( "  " );
			//System.out.println( cols.getString( "COLUMN_NAME" ) + "-" + getCloverTypeFromJdbcType( cols.getInt( "DATA_TYPE" ) ) + "-" + cols.getBoolean( "NULLABLE" ) );
			sb.append( "<Field name=\"").append( cols.getString( "COLUMN_NAME" ).toLowerCase() ).append( "\"" );
			for ( int i = cols.getString( "COLUMN_NAME" ).length(); i < 31; i++ ) {
				sb.append( ' ' );
			}
			sb.append( " type=\"" ).append( ETLHelper.getCloverTypeFromJdbcType( cols.getInt( "DATA_TYPE" ) ) ).append( "\"" );
			for ( int i = ETLHelper.getCloverTypeFromJdbcType( cols.getInt( "DATA_TYPE" ) ).length(); i < 10; i++ ) {
				sb.append( ' ' );
			}
			sb.append( " nullable=\"true\" " );
			sb.append( " trim=\"false\"" );
			if ( cols.getRow() == colCount ) {	
				sb.append( " delimiter=\"|\\r\\n\"" );
			} else {
				sb.append( " delimiter=\"" ).append( ETLHelper.COLUMN_DELIMITER ).append( "\" " );				
			}
			if ( ETLHelper.getCloverTypeFromJdbcType( cols.getInt( "DATA_TYPE" ) ).equals( "date" ) ) {
				sb.append( " format=\"yyyyMMddHHmmss\"" );
			}
			
			sb.append( " />\n" );
		}
		cols.close();
		sb.append( "</Record>" );	
		return sb.toString();
	}
	
}

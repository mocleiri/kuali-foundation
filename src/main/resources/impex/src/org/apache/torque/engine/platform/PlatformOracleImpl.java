package org.apache.torque.engine.platform;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.torque.engine.database.model.Domain;
import org.apache.torque.engine.database.model.SchemaType;

/**
 * Oracle Platform implementation.
 *
 * @author <a href="mailto:mpoeschl@marmot.at">Martin Poeschl</a>
 * @version $Id: PlatformOracleImpl.java,v 1.1.6.1 2008-04-18 17:04:37 jkeller Exp $
 */
public class PlatformOracleImpl extends PlatformDefaultImpl
{
    /**
     * Default constructor.
     */
    public PlatformOracleImpl()
    {
        super();
        initialize();
    }

    /**
     * Initializes db specific domain mapping.
     */
    private void initialize()
    {
        setSchemaDomainMapping(new Domain(SchemaType.TINYINT, "NUMBER", "3", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.SMALLINT, "NUMBER", "5", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.INTEGER, "NUMBER", "10", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.BOOLEANINT, "NUMBER", "1", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.BIGINT, "NUMBER", "20", "0"));
        setSchemaDomainMapping(new Domain(SchemaType.REAL, "NUMBER"));
        setSchemaDomainMapping(new Domain(SchemaType.DOUBLE, "FLOAT"));
        setSchemaDomainMapping(new Domain(SchemaType.DECIMAL, "NUMBER"));
        setSchemaDomainMapping(new Domain(SchemaType.NUMERIC, "NUMBER"));
        setSchemaDomainMapping(new Domain(SchemaType.VARCHAR, "VARCHAR2"));
        setSchemaDomainMapping(new Domain(SchemaType.LONGVARCHAR, "VARCHAR2", "2000"));
        setSchemaDomainMapping(new Domain(SchemaType.TIME, "DATE"));
        setSchemaDomainMapping(new Domain(SchemaType.TIMESTAMP, "TIMESTAMP"));
        setSchemaDomainMapping(new Domain(SchemaType.BINARY, "LONG RAW"));
        setSchemaDomainMapping(new Domain(SchemaType.VARBINARY, "BLOB"));
        setSchemaDomainMapping(new Domain(SchemaType.LONGVARBINARY, "LONG RAW"));
    }

    /**
     * @see Platform#getMaxColumnNameLength()
     */
    public int getMaxColumnNameLength()
    {
        return 30;
    }

    /**
     * @see Platform#getNativeIdMethod()
     */
    public String getNativeIdMethod()
    {
        return Platform.SEQUENCE;
    }

    /**
     * @see Platform#getAutoIncrement()
     */
    public String getAutoIncrement()
    {
        return "";
    }

    protected boolean isSpecialTable( String tableName ) {
    	return tableName.contains( "$" );
    }
    
    @Override
    public List<String> getPrimaryKeys(DatabaseMetaData dbMeta, String dbSchema, String tableName) throws SQLException {
    	return super.getPrimaryKeys( dbMeta, dbSchema.toUpperCase(), tableName );
    }
    
    protected Map<String,String> getTableToCommentMap( DatabaseMetaData dbMeta, String dbSchema ) throws SQLException {
    	Map<String,String> commentsByTable = new HashMap<String, String>();
    	// pull the comments for the tables
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
	    	ps = dbMeta.getConnection().prepareStatement("select table_name, comments from all_tab_comments\n" + 
	    			"WHERE owner = ?");
	    	ps.setString(1,dbSchema);
	    	rs = ps.executeQuery();
	    	while ( rs.next() ) {
	    		commentsByTable.put( rs.getString(1), rs.getString(2));
	    	}
    	} finally {
    		if ( rs != null ) {
    			rs.close();
    		}
    		if ( ps != null ) {
    			ps.close();
    		}
    	}
    	return commentsByTable;
    }

    @Override
    public List<DatabaseObjectInformation> getTables(DatabaseMetaData dbMeta, String dbSchema) throws SQLException {
		List<DatabaseObjectInformation> tables = super.getTables(dbMeta, dbSchema);

    	Map<String,String> commentsByTable = getTableToCommentMap(dbMeta, dbSchema);
		
		// filter out special tables
		Iterator<DatabaseObjectInformation> tableIterator = tables.iterator();
		while ( tableIterator.hasNext() ) {
			DatabaseObjectInformation table = tableIterator.next();
			if ( isSpecialTable(table.getName()) ) { 
				tableIterator.remove();
			} else {
				// pull the comment for the table
				table.setComment( commentsByTable.get( table.getName() ) );
			}
		}
		return tables;
    }
    
    @Override
    public List<DatabaseObjectInformation> getViews(DatabaseMetaData dbMeta, String dbSchema) throws SQLException {
    	List<DatabaseObjectInformation> views = super.getViews(dbMeta, dbSchema);
    	Map<String,String> commentsByTable = getTableToCommentMap(dbMeta, dbSchema);
    	for ( DatabaseObjectInformation view : views ) {
    		view.setComment( commentsByTable.get( view.getName() ));
    	}
    	
    	return views;
    }
    
    @Override
    public List<DatabaseObjectInformation> getSequences(
    		DatabaseMetaData dbMeta, String dbSchema) throws SQLException {
    	List<DatabaseObjectInformation> sequences = super.getSequences(dbMeta, dbSchema);
    	Map<String,String> commentsByTable = getTableToCommentMap(dbMeta, dbSchema);
    	for ( DatabaseObjectInformation sequence : sequences ) {
    		sequence.setComment( commentsByTable.get( sequence.getName() ));
    	}
    	return sequences;
    }
    
    @Override
    public List<DatabaseColumnInformation> getColumns(DatabaseMetaData dbMeta, String dbSchema, String tableName) throws SQLException {
    	List<DatabaseColumnInformation> columns = super.getColumns(dbMeta, dbSchema, tableName);
    	// pull the comments for the columns
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
	    	ps = dbMeta.getConnection().prepareStatement("select column_name, comments from all_col_comments\n" + 
	    			"WHERE owner = ?\n" + 
	    			"  AND table_name = ?");
	    	ps.setString(1,dbSchema);
	    	ps.setString(2,tableName);
	    	Map<String,String> commentsByColumn = new HashMap<String, String>();
	    	rs = ps.executeQuery();
	    	while ( rs.next() ) {
	    		commentsByColumn.put( rs.getString(1), rs.getString(2));
	    	}
	    	for ( DatabaseColumnInformation col : columns ) {
	    		col.setComment( commentsByColumn.get(col.getName()));
	    	}
    	} finally {
    		if ( rs != null ) {
    			rs.close();
    		}
    		if ( ps != null ) {
    			ps.close();
    		}
    	}
    	return columns;
    }
    
	@Override
	public boolean isSpecialDefault( String defaultValue ) {
		defaultValue = defaultValue.toUpperCase();
		if ( defaultValue.contains( "SYS_GUID()" )
				|| defaultValue.contains( "SYSDATE" )
				|| defaultValue.contains( "USERENV(\'SESSIONID\')" ) ) {
			return true;
		}
		return false;
	}

	@Override
	public Long getSequenceNextVal(Connection con, String schema, String sequenceName) {
		try {
			PreparedStatement ps = con.prepareStatement( "SELECT last_number FROM all_sequences WHERE sequence_owner = ? AND sequence_name = ?" );
			Long nextVal = 0L;
			ps.setString( 1, schema.toUpperCase() );
			ps.setString( 2, sequenceName.toUpperCase() );
			ResultSet rs = ps.executeQuery();
			if ( rs.next() ) {
				nextVal = rs.getLong( 1 );
			}
			rs.close();
			ps.close();
			return nextVal;
		} catch ( SQLException ex ) {
			System.err.println( "Unable to extract sequence definition: " + schema + "." + sequenceName );
			ex.printStackTrace();
			return 0L;
		}
	}

	@Override
	public String getViewDefinition( Connection con, String schema, String viewName) {
		try {
			PreparedStatement ps = con.prepareStatement( "SELECT text FROM all_views WHERE owner = ? AND view_name = ?" );
			String definition = "";
			ps.setString( 1, schema.toUpperCase() );
			ps.setString( 2, viewName.toUpperCase() );
			ResultSet rs = ps.executeQuery();
			if ( rs.next() ) {
				definition = rs.getString( 1 );
			}
			rs.close();
			ps.close();
			return definition;
		} catch ( SQLException ex ) {
			System.err.println( "Unable to extract view definition: " + schema + "." + viewName );
			ex.printStackTrace();
			return "";
		}
	}

	@Override
	public int getMaxCommentLength() {
		return 4000;
	}
}

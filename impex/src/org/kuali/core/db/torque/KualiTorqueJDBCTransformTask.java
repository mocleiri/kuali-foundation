package org.kuali.core.db.torque;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.torque.engine.platform.DatabaseColumnInformation;
import org.apache.torque.engine.platform.DatabaseObjectInformation;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Element;

public class KualiTorqueJDBCTransformTask extends Task {
	
	// some flags for making it easier to debug
	private final boolean processTables = true;
	private final boolean processViews = true;
	private final boolean processSequences = true;
	
	
    /** Name of XML database schema produced. */
    protected String xmlSchema;

    /** JDBC URL. */
    protected String dbUrl;

    /** JDBC driver. */
    protected String dbDriver;

    /** JDBC user name. */
    protected String dbUser;

    /** JDBC password. */
    protected String dbPassword;

    /** DB schema to use. */
    protected String dbSchema;

    /** DOM document produced. */
    protected DocumentImpl doc;

    /** The document root element. */
    protected Element databaseNode;

    protected String dbType;
    
    /** Hashtable of columns that have primary keys. */
    protected HashMap<String,String> primaryKeys;

    private XMLSerializer xmlSerializer;

	private String tableNameRegex = ".*";
	private Pattern tableNameRegexPattern = Pattern.compile(tableNameRegex);
    
    public String getDbSchema()
    {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema)
    {
        this.dbSchema = dbSchema;
    }

    public void setDbUrl(String v)
    {
        dbUrl = v;
    }

    public void setDbDriver(String v)
    {
        dbDriver = v;
    }

    public void setDbUser(String v)
    {
        dbUser = v;
    }

    public void setDbPassword(String v)
    {
        dbPassword = v;
    }

    public void setOutputFile (String v)
    {
        xmlSchema = v;
    }
    
    public void setDbType(String dbType) {
		this.dbType = dbType;
	}

    /**
     * Default constructor.
     *
     * @throws BuildException
     */
    public void execute() throws BuildException {
        log("Torque - JDBCToXMLSchema starting");
        log("Your DB settings are:");
        log("driver : " + dbDriver);
        log("URL : " + dbUrl);
        log("user : " + dbUser);
        // log("password : " + dbPassword);
        log("schema : " + dbSchema);

        DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, "database.dtd" );
        doc = new DocumentImpl(docType);
        doc.appendChild(doc.createComment( " Autogenerated by KualiTorqueJDBCTransformTask! "));

        try {
            generateXML();
            log(xmlSchema);
            OutputFormat of = new OutputFormat(Method.XML, null, true);
            of.setLineWidth(120);
            xmlSerializer = new XMLSerializer( new PrintWriter( new FileOutputStream(xmlSchema) ), of);
            xmlSerializer.serialize(doc);
        } catch (Exception e) {
            throw new BuildException(e);
        }
        log("Torque - JDBCToXMLSchema finished");
    }

    protected List<String> upperCaseList( List<String> sourceList ) {
    	ArrayList<String> newList = new ArrayList<String>( sourceList.size() );
    	for ( String item : sourceList ) {
    		if ( item != null ) {
    			newList.add( item.toUpperCase() );
    		} else {
    			newList.add( null );
    		}
    	}
    	return newList;
    }
    
	/**
	 * Generates an XML database schema from JDBC metadata.
	 * 
	 * @throws Exception
	 *             a generic exception.
	 */
	public void generateXML() throws Exception {
		// Load the database Driver.
		Class.forName( dbDriver );
		log( "DB driver sucessfuly instantiated" );

		Connection con = null;
		try {
			// Attempt to connect to a database.
			Properties p = new Properties();
			p.setProperty("user", dbUser);
			p.setProperty("password", dbPassword);
			p.setProperty("oracle.jdbc.mapDateToTimestamp", "false"); // workaround for change in 11g JDBC driver
			con = DriverManager.getConnection( dbUrl, p );
			log( "DB connection established" );

			Platform platform = PlatformFactory.getPlatformFor( dbType );
			
			// Get the database Metadata.
			DatabaseMetaData dbMetaData = con.getMetaData();
			
			databaseNode = doc.createElement( "database" );
			databaseNode.setAttribute( "name", "kuali" );
			// JHK added naming method
			databaseNode.setAttribute( "defaultJavaNamingMethod", "nochange" );

			if ( processTables ) {
				List<DatabaseObjectInformation> tableList = platform.getTables( dbMetaData, dbSchema );
				// ensure sorting is consistent (not DB-dependent)
				Collections.sort(tableList);
				for ( DatabaseObjectInformation curTable : tableList ) {
					String tableName = curTable.getName();
					// ensure all are upper case before exporting
					tableName = tableName.toUpperCase();
					if ( !tableNameRegexPattern.matcher( tableName ).matches() ) {
						log( "Skipping table: " + tableName);
						continue;
					}
					log( "Processing table: " + tableName );
	
					Element table = doc.createElement( "table" );
					table.setAttribute( "name", tableName.toUpperCase() );
					if ( StringUtils.isNotBlank( curTable.getComment() ) ) {
						table.setAttribute( "zz_comment", curTable.getComment() );
					}
	
					// Add Columns.
					// TableMap tblMap = dbMap.getTable(curTable);
	
					List<DatabaseColumnInformation> columns = platform.getColumns( dbMetaData, dbSchema, tableName );
					List<String> primKeys = platform.getPrimaryKeys( dbMetaData, dbSchema, tableName );
					Map<String,Object[]> foreignKeys = getForeignKeys( dbMetaData, tableName );
	
					// Set the primary keys.
					primaryKeys = new HashMap<String,String>();
	
					for ( int k = 0; k < primKeys.size(); k++ ) {
						String curPrimaryKey = (String)primKeys.get( k );
						primaryKeys.put( curPrimaryKey, curPrimaryKey );
					}
	
					for ( DatabaseColumnInformation col : columns ) {	
						Element column = doc.createElement( "column" );
						column.setAttribute( "name", col.getName() );
						column.setAttribute( "type", col.getExportColumnType() );
						if ( StringUtils.isNotBlank( col.getExportSize() )) {
							column.setAttribute( "size", col.getExportSize() );
						}
						if ( StringUtils.isNotBlank( col.getExportScale() )) {
							column.setAttribute( "scale", col.getExportScale() );
						}
	
						if ( primaryKeys.containsKey( col.getName() ) ) {
							column.setAttribute( "primaryKey", "true" );
							// JHK: protect MySQL from excessively long column in the PK
							// (When using UTF-8, each character may take up to 3 bytes, hence the 765
							if ( StringUtils.isNotBlank( col.getExportSize() ) && col.getSize() > 765 ) {
								log( "updating column " + tableName + "." + col.getName() + " length from " + col.getSize() + " to 255" );
								column.setAttribute( "size", "255" );
							}
						} else {
							if ( col.isNotNull() ) {
								column.setAttribute( "required", "true" );
							}
						}
						
						if ( StringUtils.isNotEmpty( col.getExportDefaultValue() ) ) {
							column.setAttribute( "default", col.getExportDefaultValue() );
						}

						if ( StringUtils.isNotBlank( col.getComment() ) ) {
							column.setAttribute( "zz_comment", col.getComment() );
						}
						table.appendChild( column );
					}
	
					List<String> foreignKeyNames = new ArrayList<String>( foreignKeys.keySet() );
					Collections.sort( foreignKeyNames );
					// Foreign keys for this table.
					for ( String fkName : foreignKeyNames ) {
						Element fk = doc.createElement( "foreign-key" );
						fk.setAttribute(  "name", fkName.toUpperCase() );
						Object[] forKey = foreignKeys.get( fkName );
						String foreignKeyTable = (String)forKey[0];
						List refs = (List)forKey[1];
						fk.setAttribute( "foreignTable", foreignKeyTable.toUpperCase() );
						String onDelete = (String) forKey[2];
						// gmcgrego - just adding onDelete if it's cascade so as not to affect kfs behavior
						if (onDelete == "cascade") {
							fk.setAttribute("onDelete", onDelete);
						}
						for ( int m = 0; m < refs.size(); m++ ) {
							Element ref = doc.createElement( "reference" );
							String[] refData = (String[])refs.get( m );
							ref.setAttribute( "local", refData[0] );
							ref.setAttribute( "foreign", refData[1] );
							fk.appendChild( ref );
						}
						table.appendChild( fk );
					}
					
					List<TableIndex> indexes = getIndexes( dbMetaData, tableName );
					Collections.sort( indexes, new Comparator<TableIndex>() {
						public int compare(TableIndex o1, TableIndex o2) { return o1.name.compareTo(o2.name); }
					} );
					for ( TableIndex idx : indexes ) {
						if ( foreignKeyNames.contains( idx.name ) ) {
							log( idx.name + " is also a foreign key, skipping" );
							continue;
						}
						String tagName = idx.unique?"unique":"index";
						Element index = doc.createElement( tagName );
						index.setAttribute( "name", idx.name.toUpperCase() );
						for ( String colName : idx.columns ) {
							Element col = doc.createElement( tagName + "-column" );
							col.setAttribute( "name", colName );
							index.appendChild( col );
						}
						table.appendChild( index );
					}
					
					databaseNode.appendChild( table );
				}
			}
			if ( processViews ) {
				log( "Getting view list..." );
				List<DatabaseObjectInformation> views = platform.getViews( dbMetaData, dbSchema );
				log( "Found " + views.size() + " views." );
				Collections.sort( views );
				for ( DatabaseObjectInformation view : views ) {
					String viewName = view.getName().toUpperCase();
					if ( !tableNameRegexPattern.matcher( viewName ).matches() ) {
						log( "Skipping view: " + viewName);
						continue;
					}
					Element viewElement = doc.createElement( "view" );
					viewElement.setAttribute( "name", viewName.toUpperCase() );
					if ( StringUtils.isNotBlank( view.getComment() ) ) {
						viewElement.setAttribute( "zz_comment", view.getComment() );
					}
					/*
					 * <view name="" viewdefinition="" />
					 * 
					 */
					String definition = platform.getViewDefinition( dbMetaData.getConnection(), dbSchema, viewName );
					definition = definition.replaceAll( "\0", "" );
					viewElement.setAttribute( "viewdefinition", definition );
					databaseNode.appendChild( viewElement );
				}
			}
			
			if ( processSequences ) {
				log( "Getting sequence list..." );
				List<DatabaseObjectInformation> sequences = platform.getSequences( dbMetaData, dbSchema );
				log( "Found " + sequences.size() + " sequences." );
				Collections.sort( sequences );
				for ( DatabaseObjectInformation sequence : sequences ) {
					String sequenceName = sequence.getName().toUpperCase();
					if ( !tableNameRegexPattern.matcher( sequenceName ).matches() ) {
						log( "Skipping sequence: " + sequenceName);
						continue;
					}
					Element sequenceElement = doc.createElement( "sequence" );
					sequenceElement.setAttribute( "name", sequenceName.toUpperCase() );
					if ( StringUtils.isNotBlank( sequence.getComment() ) ) {
						sequenceElement.setAttribute( "zz_comment", sequence.getComment() );
					}
					/*
					 * <view name="" nextval="" />
					 * 
					 */
					Long nextVal = platform.getSequenceNextVal( dbMetaData.getConnection(), dbSchema, sequenceName );
					sequenceElement.setAttribute( "nextval", nextVal.toString() );
					
					databaseNode.appendChild( sequenceElement );
				}
				doc.appendChild( databaseNode );
			}
		} finally {
			if ( con != null ) {
				con.close();
				con = null;
			}
		}
	}

    /**
     * Retrieves a list of foreign key columns for a given table.
     *
     * @param dbMeta JDBC metadata.
     * @param tableName Table from which to retrieve FK information.
     * @return A list of foreign keys in <code>tableName</code>.
     * @throws SQLException
     */
    public Map<String,Object[]> getForeignKeys(DatabaseMetaData dbMeta, String tableName)
        throws SQLException
    {
        TreeMap<String,Object[]> fks = new TreeMap<String,Object[]>();
        ResultSet foreignKeys = null;
        try
        {
            foreignKeys = dbMeta.getImportedKeys(null, dbSchema, tableName);
            while (foreignKeys.next())
            {
                String refTableName = foreignKeys.getString(3);
                String fkName = foreignKeys.getString(12);
                int deleteRule = foreignKeys.getInt(11);
                String onDelete = "none";
                if (deleteRule == DatabaseMetaData.importedKeyCascade) {
                	onDelete = "cascade";
                } else if (deleteRule == DatabaseMetaData.importedKeyRestrict) {
                	onDelete = "restrict";
                } else if (deleteRule == DatabaseMetaData.importedKeySetNull) {
                	onDelete = "setnull";
                }
                // if FK has no name - make it up (use tablename instead)
                if (fkName == null)
                {
                    fkName = refTableName;
                }
                Object[] fk = (Object[]) fks.get(fkName);
                List<String[]> refs;
                if (fk == null)
                {
                    fk = new Object[3];
                    fk[0] = refTableName; //referenced table name
                    refs = new ArrayList<String[]>();
                    fk[1] = refs;
                    fks.put(fkName, fk);
                    fk[2] = onDelete;
                }
                else
                {
                    refs = (ArrayList<String[]>) fk[1];
                }
                String[] ref = new String[2];
                ref[0] = foreignKeys.getString(8); //local column
                ref[1] = foreignKeys.getString(4); //foreign column
                refs.add(ref);
            }
        }
        catch (SQLException e)
        {
            // this seems to be happening in some db drivers (sybase)
            // when retrieving foreign keys from views.
            log("WARN: Could not read foreign keys for Table "
                        + tableName
                        + " : "
                        + e.getMessage(),
                    Project.MSG_WARN);
        }
        finally
        {
            if (foreignKeys != null)
            {
                foreignKeys.close();
            }
        }
        return fks;
    }
    
    public List<TableIndex> getIndexes( DatabaseMetaData dbMeta, String tableName ) throws SQLException {
    	List<TableIndex> indexes = new ArrayList<TableIndex>();
    	ResultSet pkInfo = null;
    	String pkName = null;
    	//ArrayList<String> pkFields = new ArrayList<String>();
    	ResultSet indexInfo = null;
    	try {
    		indexInfo = dbMeta.getIndexInfo( null, dbSchema, tableName, false, true );
    		// need to ensure that the PK is not returned as an index
    		pkInfo = dbMeta.getPrimaryKeys( null, dbSchema, tableName );
    		if ( pkInfo.next() ) {
    			pkName = pkInfo.getString( "PK_NAME" );
    		}
    		//Map<Integer,String> tempPk = new HashMap<Integer,String>(); 
    		//while ( pkInfo.next() ) {
    		//	tempPk.put( pkInfo.getInt( "KEY_SEQ" ), pkInfo.getString( "COLUMN_NAME" ) );
    		//}
    		
    		TableIndex currIndex = null;
    		while ( indexInfo.next() ) {
    			if ( indexInfo.getString( "INDEX_NAME" ) == null ) continue;
    			//System.out.println( "Row: " + indexInfo.getString( "INDEX_NAME" ) + "/" + indexInfo.getString( "COLUMN_NAME" ) );
    			if ( currIndex == null || !indexInfo.getString( "INDEX_NAME" ).equals( currIndex.name ) ) {
    				currIndex = new TableIndex();
    				currIndex.name = indexInfo.getString( "INDEX_NAME" );
    				currIndex.unique = !indexInfo.getBoolean( "NON_UNIQUE" );
    				// if has the same name as the PK, skip adding it to the index list
    				if ( pkName == null || !pkName.equals( currIndex.name ) ) {
    					indexes.add( currIndex );
    					//System.out.println( "Added " + currIndex.name + " to index list");
    				} else {
    					//System.out.println( "Skipping PK: " + currIndex.name );
    				}
    			}
    			currIndex.columns.add( indexInfo.getString( "COLUMN_NAME" ) );
    		}
    		
    	} catch (SQLException e) {
            log("WARN: Could not read indexes for Table "
                    + tableName
                    + " : "
                    + e.getMessage(),
                Project.MSG_WARN);
		} finally {
			if ( indexInfo != null ) {
				indexInfo.close();
			}
			if ( pkInfo != null ) {
				pkInfo.close();
			}
		}
		return indexes;
    }
	
    private static class TableIndex {
    	public String name;
    	public boolean unique;
    	public List<String> columns = new ArrayList<String>( 10 );
    }

	public String getTableNameRegex() {
		return tableNameRegex;
	}

	public void setTableNameRegex(String tableNameRegex) {
		this.tableNameRegex = tableNameRegex;
		tableNameRegexPattern = Pattern.compile(tableNameRegex);
	}
}

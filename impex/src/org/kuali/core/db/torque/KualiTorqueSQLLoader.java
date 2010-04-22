package org.kuali.core.db.torque;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.Table;
import org.xml.sax.helpers.DefaultHandler;

public class KualiTorqueSQLLoader extends Task{
	
    /** Name of the SQL*Loader control file that is being made */
    private final String controlFile = "loader.ctl";
    
    /** Name of the SQL*Loader .dat file that is being made */
    private final String datFile = "infile.dat";
    
    /** Within the data file, all fields are surrounded by the ENCLOSING string */
    private final String ENCLOSING = "#$#";
    
    /** Within the data file, a row is terminated by the TERMINATOR string */
    private final String TERMINATOR = "7c0a";
    /** Only used if HEXADECIMAL is set to true, a usable string form of what the terminator is */
    private final String TERMINATOR_ASCII = "|\n";
    /** Set to true if the TERMINATOR is in hexadecimal format */
    private final boolean HEXADECIMAL = true;
	
	/** This tells SQL*Loader how much data to store before sending that data to the database.
	 * The higher this value, the greater amount of "rows" can be sent to the database at a time.
	 * The greater amount of "rows", the faster SQL*Loader runs. The improvements in run time
	 * begin to dwindle at about 100 rows.
	 * WARNING: The maximum BINSIZE is system-dependent. If SQL*Loader is giving an
	 * error regarding a maximum BINSIZE, this value must be changed.
	 */
	private final int BINDSIZE = 20000000;

	/** This tells SQL*Loader how much data to read in at a time from the .dat file
	 * WARNING: The maximum READSIZE is system-dependent. If SQL*Loader is giving an
	 * error regarding a maximum READSIZE, this value must be changed.
	 */
	private final int READSIZE = 20000000;
	
	/** Determines how many errors can occur while SQL*Loader is running before it terminates. */
	protected String numErrors = "999999";
	
	/** Determines whether or not SQL*Loader reports feedback */
	protected String silent = "false";

	/** Fileset of XML datafiles to be used to make the control file. */
    protected List filesets = new ArrayList();
    
    /** The target database(s) we are generating SQL for.
     * 	Only oracle is supported with SQLLoader. */
    protected String targetDatabase;
    
    /** Target Java package to place the generated files in. */
    protected String targetPackage;
    
    /** Target directory to place the generated files in. */
    protected String outputDirectory = "";
    
    /** XML schema file. */
    protected String xmlFile;
    
    /** Control if table truncated on data load */
    protected boolean truncateTable = true;
	
    /** 
     * Writes a control file and data file based on input XML data such that when SQL*Loader
     * is run given those files as input, it will import that data into a target database.
     */
	public void execute(){
		
		if(filesets.isEmpty())
			 throw new BuildException("You must specify a fileset of XML datafiles!");
		
		if(xmlFile == null)
			throw new BuildException("You must specify an XML schema!");
		
		try {
			
			//It's possible that a previous SQL*Loader run created a .bad file and a current one did not
			//In this case, it may confuse people to see a .bad file when in fact no lines were bad
			//Solution: delete any .bad file before the SQL*Loader run
			File badFile = new File(outputDirectory, "infile.bad");
			if(badFile.isFile())
				badFile.delete();
			
			FileWriter ctrlfile = new FileWriter(new File(outputDirectory, controlFile));
			FileWriter infile = new FileWriter(new File(outputDirectory, datFile));
			ctrlfile.write("OPTIONS (ROWS=8192,BINDSIZE="+BINDSIZE+",READSIZE="+READSIZE+",ERRORS="+numErrors+getSilentString()+")\n");
			ctrlfile.write("LOAD DATA\n");
            ctrlfile.write("INFILE '"+datFile+"' \"str "+getHexadecimalString()+"'"+TERMINATOR+"'\"\n");
            if(truncateTable) {
                ctrlfile.write("TRUNCATE\n");
            }
            else {
                ctrlfile.write("APPEND\n");
            }
            
            KualiXmlToAppData xmlParser = new KualiXmlToAppData(getTargetDatabase(), getTargetPackage());
            KualiDatabase ad = xmlParser.parseFile(xmlFile);
            KualiTorqueSQLLoaderWriter wr = new KualiTorqueSQLLoaderWriter(infile, ad, outputDirectory, ENCLOSING, getTerminatorString());
            
			for(int i = 0; i < filesets.size(); i++){
				FileSet fs = (FileSet) filesets.get(i);
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                File srcDir = fs.getDir(getProject());
                
                String[] dataModelFiles = ds.getIncludedFiles();
                
                for(int j = 0; j < dataModelFiles.length; j++){
                	log("Preparing " + dataModelFiles[j] + " for SQL*Loader!");
                	String tableName = dataModelFiles[j].replace(".xml", "");
            		List columns = ad.getTable(tableName).getColumns();
            		if(columns.size() == 0)
            			log("IGNORING: " + tableName + "... No columns in xml file");
            		else{
            			//If a line of data being interpreted passes the WHEN clause, that line of data will
            			//be inserted into the table with the name tableName
	                	ctrlfile.write("INTO TABLE " + tableName + "\n");
	                	//When the control file variable tablename (declared below) is equal to the java variable tableName, 
	                	//the line of data being interpreted passes the WHEN clause
	                	ctrlfile.write("WHEN tablename = '" + tableName + "'\n");
	                	//This means that within the .dat file, the input for a field will be determined by whatever is within ENCLOSING marks
	                	//and separated by a comma. For example, in #$#field1#$#,#$#field2#$# field1 and field2 will be placed into the database
	                	ctrlfile.write("FIELDS TERMINATED BY '"+ENCLOSING+"'\n");
	                	//This allows for the last field in a table to be null in the .dat file.
	                	//For example #$#field1#$#,#$#field2#$#,#$##$#, the last field is null
	                	ctrlfile.write("TRAILING NULLCOLS\n(\n");
	                	//This takes the first field in a line of data and stores it into the variable tablename
	                	ctrlfile.write("tablename FILLER POSITION(1)");
	                	
	            		String nameOfLobvar = "ext_fname";
	            		int lobVarIndex = 1;
	            		
	            		for(int k = 0; k < columns.size(); k++){
	            			Column c = ((Column)columns.get(k));
	            			//Tells SQL*Loader what format of Date should be used
	            			if(c.getDomain().getSqlType().equals("DATE") || c.getDomain().getSqlType().equals("TIMESTAMP"))
	            				ctrlfile.write(",\n" + c.getName() + " DATE 'YYYYMMDDHH24MISS'");
	            			//LOB data will be stored into a separate file and read by SQL*Loader
	            			else if(c.getDomain().getSqlType().contains("LOB")){ //CLOBs, BLOBs...
	            				String finalLobvar = nameOfLobvar + lobVarIndex;
	            				ctrlfile.write(",\n"+(finalLobvar)+" FILLER,\n" + c.getName() + " LOBFILE("+finalLobvar+") TERMINATED BY EOF");
	            				lobVarIndex++;
	            			}
	            			//SQL*Loader requires variables with size > 255 be declared
	            			//Declaring the size of all variable makes the loader run faster (since it defaults to 255 if not specified)
	            			else if(c.getSize() != null){ 
	            				if(c.getDomain().getSqlType().equals("VARCHAR2") || c.getDomain().getSqlType().equals("CHAR"))
	            					ctrlfile.write(",\n" + c.getName() + " CHAR(" + c.getSize() + ")");
	            				else if(c.getDomain().getSqlType().equals("NUMBER"))
	            					ctrlfile.write(",\n" + c.getName() + " CHAR(" + (c.getSize()+1) + ")"); //+1 in case of a decimal point, 12.34 is a number with size 4, but takes up 5 character spaces
	            				else
	            					ctrlfile.write(",\n" + c.getName());
	            			}else
	            				ctrlfile.write(",\n" + c.getName());
	            		}
	            		ctrlfile.write("\n)\n");

	                	File f = new File(srcDir, dataModelFiles[j]);
	                	wr.parseFile(f.getAbsolutePath());
            		}
                }
			}
            ctrlfile.close();
			infile.close();
			
		} catch (IOException e) {
			log("IOException occured while writing " + controlFile + " and " + datFile + " : " + e.getMessage());
			throw new BuildException(e);
		} catch (EngineException e) {
			throw new BuildException(e);
		}
		
	}
	
	/**
     * Determines whether or not SQL*Loader will report
     * Commit point reached - logical record count (#)
     *
     * @return A string that when added to the OPTIONS clause of the .ctl file, silences the feedback reports of SQL*Loader
     */
	private String getSilentString(){
		if(getSilent().equals("true"))
			return ",SILENT=(FEEDBACK)";
		else
			return "";
	}
	
	/**
	 * Hexadecimal strings require a different format within the
	 * .ctl file, this method helps create a string that follows that format.
	 * 
	 * @return A value that when placed before the stream string in the .ctl file, tells the form of the stream string (hexadecimal or normal)
	 */
	private String getHexadecimalString(){
		if(HEXADECIMAL)
			return "X";
		else
			return "";
	}
	
	private String getTerminatorString(){
		if(HEXADECIMAL){
			return TERMINATOR_ASCII;
		}else
			return TERMINATOR;
	}
	 
	 /**
	  * Get the number of errors SQL*Loader must encounter before it terminates.
	  *
	  * @return String Number of errors SQL*Loader must encounter before it terminates. Default is 999999.
	  */
	 public String getNumErrors()
	 {
		 return numErrors;
	 }

	 /**
	  * Set the number of errors SQL*Loader must encounter before it terminates.
	  *
	  * @param v Number of errors SQL*Loader must encounter before it terminates. Default is 999999.
	  */
	 public void setNumErrors(String v)
	 {
		 silent = v;
	 }
	 
	 /**
	  * Get whether or not SQL*Loader will report feedback or not
	  *
	  * @return String "true" to ignore SQL*Loader's feedback. Default is false.
	  */
	 public String getSilent()
	 {
		 return silent;
	 }

	 /**
	  * Set whether or not SQL*Loader will report feedback or not
	  *
	  * @param v "true" to ignore SQL*Loader's feedback. Default is false.
	  */
	 public void setSilent(String v)
	 {
		 silent = v;
	 }
	
	/**
     * Get the current target database.
     *
     * @return String target database(s)
     */
    public String getTargetDatabase()
    {
        return targetDatabase;
    }

    /**
     * Set the current target database. (e.g. mysql, oracle, ..)
     *
     * @param v target database(s)
     */
    public void setTargetDatabase(String v)
    {
        targetDatabase = v;
    }

	/**
     * Adds a set of xml datafiles (nested fileset attribute).
     *
     * @param set a Set of xml datafiles files
     */
    public void addFileset(FileSet set)
    {
        filesets.add(set);
    }
    
    /**
     * Set the directory where SQL*Loader data will be written.
     *
     * @param v directory where SQL*Loader data will be written.
     */
    public void setOutputDirectory(String v){
    	outputDirectory = v;
    }
    
    /**
     * Get the directory where SQL*Loader data will be written.
     *
     * @return return SQL*Loader data directory.
     */
    public String getOutputDirectory(){
    	return outputDirectory;
    }
    
    /**
     * Get the current target package.
     *
     * @return return target java package.
     */
    public String getTargetPackage()
    {
        return targetPackage;
    }

    /**
     * Set the current target package. This is where generated java classes will
     * live.
     *
     * @param v target java package.
     */
    public void setTargetPackage(String v)
    {
        targetPackage = v;
    }
    
    /**
     * Get the xml schema describing the application model.
     *
     * @return  String xml schema file.
     */
    public String getXmlFile()
    {
        return xmlFile;
    }

    /**
     * Set the xml schema describing the application model.
     *
     * @param xmlFile The new XmlFile value
     */
    public void setXmlFile(String xmlFile)
    {
        this.xmlFile = getProject().resolveFile(xmlFile).toString();
    }

    public boolean isTruncateTable() {
        return truncateTable;
    }

    /**
     * Determines if tables should be truncated before load or data should be appended.
     * 
     * @param truncateTable
     */
    public void setTruncateTable(boolean truncateTable) {
        this.truncateTable = truncateTable;
    }
    
    
}

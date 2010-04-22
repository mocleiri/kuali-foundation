package org.kuali.core.db.torque;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.Domain;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.database.transform.DTDResolver;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class KualiTorqueSQLLoaderWriter extends DefaultHandler{
	
	/** All LOB data will be stored in this directory */
	private final String LOB_DIRECTORY = "/lobfiles/";
	
	private FileWriter infile;
	private KualiDatabase ad;
	
	private Map<String,String> values;
	private Map<String,Boolean> isLob;
	private List<String> orderOfKeys;
	
	private String outputDirectory;
	private String enclosing;
	private String terminator;
	private boolean validTag;
	
	/** remember all files we have already parsed to detect looping. */
    private Vector alreadyReadFiles;
    
    private static SAXParserFactory saxFactory;
	
	public KualiTorqueSQLLoaderWriter(FileWriter infile, KualiDatabase ad, String outputDirectory, String enclosing, String terminator){
		this.infile = infile;
		this.ad = ad;
		this.outputDirectory = outputDirectory;
		this.enclosing = enclosing;
		this.terminator = terminator;
		
		values= new HashMap<String,String>();
		isLob = new HashMap<String,Boolean>();
		orderOfKeys = new ArrayList<String>();
		
		File lobDir = new File(outputDirectory + LOB_DIRECTORY);
		lobDir.mkdir();
	}
	
	static
    {
        saxFactory = SAXParserFactory.newInstance();
        saxFactory.setValidating(true);
    }

	/**
     * Parses a XML input file and returns if it was successful.
     *
     * @param xmlFile The input file to parse.
     * @return Whether or not the parse was successful.
     */
    public boolean parseFile(String xmlFile)
            throws EngineException
    {
        try
        {
            // check to see if we already have parsed the file
            if ((alreadyReadFiles != null)
                    && alreadyReadFiles.contains(xmlFile))
            {
                return false;
            }
            else if (alreadyReadFiles == null)
            {
                alreadyReadFiles = new Vector(3, 1);
            }

            // remember the file to avoid looping
            alreadyReadFiles.add(xmlFile);

            saxFactory.setValidating( false );
            SAXParser parser = saxFactory.newSAXParser();

            FileInputStream fileInputStream = null;
            try
            {
                fileInputStream = new FileInputStream(xmlFile);
            }
            catch (FileNotFoundException fnfe)
            {
                throw new FileNotFoundException
                    (new File(xmlFile).getAbsolutePath());
            }
            BufferedInputStream bufferedInputStream
                    = new BufferedInputStream(fileInputStream);
            try
            {
                InputSource is = new InputSource(bufferedInputStream);
                is.setSystemId(  xmlFile );
                parser.parse(is, this);
            }
            finally
            {
                bufferedInputStream.close();
            }
        }
        catch (SAXParseException e)
        {
            throw new EngineException("Sax error on line "
                        + e.getLineNumber()
                        + " column "
                        + e.getColumnNumber()
                        + " : "
                        + e.getMessage(),
                    e);
        }
        catch (Exception e)
        {
            throw new EngineException(e);
        }
        
        return true;
    }
    

    /**
     * EntityResolver implementation. Called by the XML parser
     *
     * @param publicId The public identifier of the external entity
     * @param systemId The system identifier of the external entity
     * @return an InputSource for the database.dtd file
     * @see DTDResolver#resolveEntity(String, String)
     */
    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException
    {
        try
        {
            return new DTDResolver().resolveEntity(publicId, systemId);
        }
        catch (Exception e)
        {
            throw new SAXException(e);
        }
    }
    

    /**
     * Handles opening elements of the xml file.
     *
     * @param uri
     * @param localName The local name (without prefix), or the empty string if
     *         Namespace processing is not being performed.
     * @param rawName The qualified name (with prefix), or the empty string if
     *         qualified names are not available.
     * @param attributes The specified or defaulted attributes
     */
    public void startElement(String uri, String localName, String rawName,
                             Attributes attributes)
            throws SAXException
    {
        try
        {
        	Table t = ad.getTable(rawName);
        	if(t != null){
        		validTag = true;
        		List columns = t.getColumns();
        		for(int k = 0; k < columns.size(); k++){
        			Column c = ((Column)columns.get(k));
        			values.put(c.getName(),"");
        			orderOfKeys.add(c.getName());
        			
        			if(c.getDomain().getSqlType().contains("LOB"))
        				isLob.put(c.getName(), Boolean.valueOf(true));
        		}
        		parseAttributes(attributes);
        	}
        }
        catch (Exception e)
        {
            throw new SAXException(e);
        }
    }
    
    public void parseAttributes(Attributes attributes){
    	int length = attributes.getLength();
    	for(int i = 0; i < length; i++)
    		values.put(attributes.getQName(i), attributes.getValue(i));
    }
    
    /**
     * Handles closing elements of the xml file.
     *
     * @param uri
     * @param localName The local name (without prefix), or the empty string if
     *         Namespace processing is not being performed.
     * @param rawName The qualified name (with prefix), or the empty string if
     *         qualified names are not available.
     */
    public void endElement(String uri, String localName, String rawName)
        throws SAXException
    {
        try
        {
        	if(validTag){
	        	int lobIndex = 1;
        		String infileLine = rawName;
	        	for(String key : orderOfKeys){
	        		String value = values.get(key);
	        		
	        		if(isLob.get(key) != null){
            			//This creates a file containing all the LOB data. It will then be read by SQL*Loader
            			String lobFileName = outputDirectory + LOB_DIRECTORY + key + lobIndex + ".txt";
            			infileLine += enclosing + lobFileName;
            			FileWriter lobWriter = new FileWriter(new File(lobFileName));
            			lobWriter.write(value);
            			lobWriter.close();
            			lobIndex++;
            		}else
            			infileLine += enclosing + value;	
	        	}
	        	
	        	//This writes a single .dat file line
            	//It contains all the information necessary to write a single INSERT statement
            	infile.write(infileLine+terminator);
	
	        	validTag = false;
	    		values.clear();
	    		isLob.clear();
	    		orderOfKeys.clear();
        	}
        }
        catch (Exception e)
        {
            throw new SAXException(e);
        }
    }
}

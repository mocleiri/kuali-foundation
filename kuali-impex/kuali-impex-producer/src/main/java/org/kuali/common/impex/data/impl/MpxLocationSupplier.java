/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.data.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.data.MpxHeaderData;
import org.kuali.common.impex.data.SqlProducer;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.supplier.AbstractSupplier;
import org.kuali.common.jdbc.supplier.LocationSupplier;
import org.kuali.common.jdbc.supplier.LocationSupplierUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.TextMetaData;

/**
 * This class provides an implementation of the SqlSupplier interface using an Mpx resource as the data source
 * 
 * @author andrewlubbers
 */
public class MpxLocationSupplier extends AbstractSupplier implements LocationSupplier {

	public static final String DEFAULT_MPX_EXTENSION = ".mpx";
	public static final String UTF8 = "UTF-8";

	protected BufferedReader reader;
	protected Table table;
    protected MpxHeaderData headerData;

	String extension = DEFAULT_MPX_EXTENSION;
	String encoding = UTF8;
	SqlProducer producer;
	String location;

    /**
     * Data model provider
     */
    Schema schema;

	@Override
	public void open() throws IOException {
		this.table = getTable();
		this.reader = LocationUtils.getBufferedReader(getResourceLocation(), encoding);
        this.headerData = getHeader(reader);
	}

    protected String getResourceLocation() {
        return LocationSupplierUtils.getLocationFromContextLocation(location);
    }

	@Override
	public List<String> getSql() throws IOException {
		return producer.getSql(table, headerData, reader);
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(reader);
		this.table = null;
	}

	protected Table getTable() {

        String resourceLocation = getResourceLocation();

		String filename = LocationUtils.getFilename(resourceLocation);
		if (!StringUtils.endsWithIgnoreCase(filename, extension)) {
			throw new IllegalArgumentException(resourceLocation + " does not end with " + extension);
		}
		int end = filename.length() - extension.length();
		String tableName = StringUtils.substring(filename, 0, end);

        for (Table t : schema.getTables()) {
            if(t.getName().equalsIgnoreCase(tableName)) {
                return t;
            }
        }

        throw new IllegalArgumentException("Unable to locate table [" + tableName + "]");
	}

    @Override
	public void fillInMetaData() {
		TextMetaData tmd = LocationUtils.getTextMetaData(getResourceLocation());
		this.metaData = new SqlMetaData(tmd.getLines() - 1, tmd.getSize());
	}

    /**
     * Parses the next line in the given reader and returns a MpxHeaderData built from header information
     *
     * This method assumes the given reader is at the beginning of an mpx file
     *
     * @param reader the mpx file reader
     * @return the next parsed header data
     * @throws IllegalArgumentException when the next non-blank line from the reader is not a header line
     *      IOException if a read error is thrown from the reader
     */
    protected MpxHeaderData getHeader(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        while(org.codehaus.plexus.util.StringUtils.isBlank(line)) {
            line = reader.readLine();
        }

        if (ParseUtils.isHeaderLine(line)) {
            MpxHeaderData header = new MpxHeaderData();
            header.getColumnNames().addAll(CollectionUtils.getTrimmedListFromCSV(line));

            return header;
        }
        else {
            throw new IllegalArgumentException("Could not parse mpx header, next non-blank in given reader is not a header line.  Reached line was: " + line);
        }
    }

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	public SqlProducer getProducer() {
		return producer;
	}

	public void setProducer(SqlProducer producer) {
		this.producer = producer;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }
}

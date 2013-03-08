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

package org.kuali.common.impex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.impex.KualiDatabase;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.supplier.AbstractSupplier;
import org.kuali.common.jdbc.supplier.LocationSupplier;
import org.kuali.common.util.LocationUtils;

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

	String extension = DEFAULT_MPX_EXTENSION;
	String encoding = UTF8;
	KualiDatabase database;
	SqlProducer producer;
	String location;

	@Override
	public void open() throws IOException {
		this.table = getTable(location, database, extension);
		this.reader = LocationUtils.getBufferedReader(location, encoding);
	}

	@Override
	public List<String> getSql() throws IOException {
		return producer.getSql(table, reader);
	}

	@Override
	public void close() {
		IOUtils.closeQuietly(reader);
		this.table = null;
	}

	protected Table getTable(String location, KualiDatabase database, String extension) {
		String filename = LocationUtils.getFilename(location);
		if (!StringUtils.endsWithIgnoreCase(filename, extension)) {
			throw new IllegalArgumentException(location + " does not end with " + extension);
		}
		int end = filename.length() - extension.length();
		String tableName = StringUtils.substring(filename, 0, end);
		return getTable(database, tableName);
	}

	protected Table getTable(KualiDatabase database, String tableName) {
		List<?> tables = database.getTables();
		for (Object element : tables) {
			Table table = (Table) element;
			if (StringUtils.equalsIgnoreCase(tableName, table.getName())) {
				return table;
			}
		}
		throw new IllegalArgumentException("Unable to locate table [" + tableName + "]");
	}

	@Override
	public void fillInMetaData() {
		long count = 0;
		long size = 0;
		BufferedReader in = null;
		try {
			Table table = getTable(location, database, extension);
			in = LocationUtils.getBufferedReader(location, encoding);
			List<String> sqls = producer.getSql(table, in);
			while (sqls != null) {
				count++;
                for(String s : sqls) {
                    size += s.length();
                }
				sqls = producer.getSql(table, in);
			}
			this.metaData = new SqlMetaData(count, size);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
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

	public KualiDatabase getDatabase() {
		return database;
	}

	public void setDatabase(KualiDatabase database) {
		this.database = database;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}

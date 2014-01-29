/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.dnsme.mojo;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.maven.plugins.dnsme.beans.Record;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Show records for the domain indicated. By default, all records are shown. Search criteria can be used to restrict the display
 * 
 * @author Jeff Caddel
 * @goal csv
 */
public class CSVMojo extends AbstractRecordsMojo {

	/**
	 * Where the CSV file gets written
	 * 
	 * @parameter expression="${dnsme.outputFile}" default-value="${project.build.directory}/dnsme/records.csv"
	 */
	File outputFile;

	/**
	 * By default DNSME appends a dot to the end of fully qualified CNAME record values. This plugin removes that dot by default.
	 * 
	 * @parameter expression="${dnsme.removeTrailingDot}" default-value="true"
	 */
	boolean removeTrailingDot;

	@Override
	protected void doRecords(List<Record> records) {
		List<String> lines = getLines(records);
		getLog().info(String.format("located %s records", lines.size() - 1));
		String path = writeFile(outputFile, lines);
		getLog().info(String.format("created -> %s", path));
	}

	protected List<String> getLines(List<Record> records) {
		Joiner joiner = Joiner.on(',');
		String header = "name,data,type,ttl";
		List<String> lines = Lists.newArrayList();
		for (Record record : records) {
			List<String> tokens = getTokens(record);
			String line = joiner.join(tokens.iterator());
			lines.add(line);
		}
		Collections.sort(lines);
		lines.add(0, header);
		return lines;
	}

	protected String writeFile(File file, List<String> lines) {
		try {
			String path = file.getCanonicalPath();
			FileUtils.writeLines(outputFile, lines);
			return path;
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	protected List<String> getTokens(Record record) {
		List<String> strings = Lists.newArrayList();
		strings.add(record.getName());
		String data = record.getData();
		if (this.removeTrailingDot && StringUtils.endsWith(data, ".")) {
			data = StringUtils.substring(data, 0, data.length() - 1);
		}
		strings.add(data);
		strings.add(record.getType().name());
		strings.add(record.getTtl() + "");
		return strings;
	}
}

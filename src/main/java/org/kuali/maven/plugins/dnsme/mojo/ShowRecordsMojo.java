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

import java.util.List;

import org.kuali.maven.plugins.dnsme.beans.Record;

/**
 * Show records for the domain indicated. By default, all records are shown. Search criteria can be used to restrict the display
 * 
 * @author Jeff Caddel
 * @goal showrecords
 */
public class ShowRecordsMojo extends AbstractRecordsMojo {

	@Override
	public void doRecords(List<Record> records) {
		getLog().info(String.format("located %s records", records.size()));
		for (Record record : records) {
			getLog().info(getCompactLog(record));
		}
	}

	protected String getCompactLog(Record record) {
		StringBuilder sb = new StringBuilder();
		sb.append(record.getName());
		sb.append("->" + record.getData());
		sb.append("," + record.getType());
		sb.append(",ttl=" + record.getTtl() + "s");
		return sb.toString();
	}

	protected String getLog(Record record) {
		StringBuilder sb = new StringBuilder();
		sb.append("Id:" + record.getId());
		sb.append(" Name:" + record.getName());
		sb.append(" Value:" + record.getData());
		sb.append(" Type:" + record.getType());
		sb.append(" TTL:" + record.getTtl());
		sb.append(" GTD:" + record.getGtdLocation());
		return sb.toString();
	}

}

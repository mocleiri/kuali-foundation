/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.common.util.pojo.Instance;

public class EC2Instances {
	
	// 15 * large = $2628.00
	// 4 * medium = $350.40
	// 1 * c1.medium = $105.5

	// Hours per day = 24
	// Hours per year = 24 * 365 = 8760
	// Hours per month = 8760 / 12 = 730
	
	// Small = $43.80
	// Medium = $87.60
	// Large = $175.20
	// X-Large = $350.40
	// c1.medium = $105.85

	@Test
	public void testRsync() {
		try {
			String filename = "/tmp/ks-instances.txt";
			List<String> lines = LocationUtils.readLines(filename);
			List<Instance> instances = new ArrayList<Instance>();
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				if (line.startsWith("RESERVATION")) {
					Instance instance = getInstance(lines, i);
					instances.add(instance);
				}
			}
			for (Instance i : instances) {
				Map<String, String> tags = i.getTags();
				String name = tags.get("Name");
				if (name != null) {
					i.setName(name);
				}
			}
			Collections.sort(instances);
			List<Object[]> rows = new ArrayList<Object[]>();
			int sequence = 1;
			for (Instance i : instances) {
				Object[] row = { sequence++, i.getName(), i.getId(), i.getSize(), i.getState() };
				rows.add(row);
			}
			List<String> columns = Arrays.asList("#", "name", "id", "size", "state");

			org.kuali.common.util.log.LoggerUtils.logTable("KS Instances", columns, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static Instance getInstance(List<String> lines, int index) {
		int endIndex = getEndIndex(lines, index);
		String res = lines.get(index);
		String ins = lines.get(index + 1);
		Assert.isTrue(res.startsWith("RESERVATION"));
		Assert.isTrue(ins.startsWith("INSTANCE"));
		Map<String, String> tags = getTags(lines, index, endIndex);
		String[] tokens = StringUtils.splitPreserveAllTokens(ins, "\t");
		String id = tokens[1];
		String state = tokens[5];
		String size = tokens[9];
		Instance instance = new Instance();
		instance.setId(id);
		instance.setSize(size);
		instance.setState(state);
		instance.setStartIndex(index);
		instance.setTags(tags);
		return instance;
	}

	protected static Map<String, String> getTags(List<String> lines, int start, int end) {
		Map<String, String> tags = new HashMap<String, String>();
		for (int i = start; i <= end; i++) {
			String line = lines.get(i);
			if (line.startsWith("TAG")) {
				String[] tokens = StringUtils.splitPreserveAllTokens(line, "\t");
				String key = tokens[3];
				String val = tokens[4];
				tags.put(key, val);
			}
		}
		return tags;
	}

	protected static int getEndIndex(List<String> lines, int index) {
		for (int i = index + 1; i < lines.size(); i++) {
			String line = lines.get(i);
			if (line.startsWith("RESERVATION")) {
				return i;
			}
			if (i == lines.size() - 1) {
				return i;
			}
		}
		throw new IllegalStateException("Unable to determine end index");
	}

}

package org.kuali.common.devops.status;

import java.util.Map;

import org.junit.Test;
import org.kuali.common.devops.logic.DNS;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

public class DNSTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		Map<String, String> records = DNS.getCanonicalNameRecords(false);
		logger.info(String.format("records -> %s", records.size()));
		Multiset<String> multi = TreeMultiset.create();
		for (String cname : records.values()) {
			multi.add(cname);
		}
		Map<String, Integer> duplicates = Maps.newTreeMap();
		for (String element : multi) {
			int count = multi.count(element);
			if (count > 1) {
				duplicates.put(element, multi.count(element));
			}
		}
		for (String duplicate : duplicates.keySet()) {
			System.out.println(duplicate + " -> " + duplicates.get(duplicate));
		}
	}
}
